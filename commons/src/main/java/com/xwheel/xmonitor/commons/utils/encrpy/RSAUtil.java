package com.xwheel.xmonitor.commons.utils.encrpy;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.*;
import java.util.Locale;

/**
 * RSA对称加密和解密工具类
 */
public class RSAUtil {
    private static final String KEY_ALGORITHM = "RSA";

    private static final int KEY_SIZE = 1024;

    //默认的安全服务提供者
    private static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 解密密文
     *
     * @param keyPair
     * @param encryptText
     * @return
     */
    public String  decryptString(KeyPair keyPair, String encryptText) {
        if (StringUtils.isBlank(encryptText)) {
            return null;
        }
        logger.debug("encrypted password:{} will be decrypted", encryptText);
        try {
            byte[] encryptData = parseHexStr2Byte(encryptText);
            byte[] data = decrypt(keyPair.getPrivate(), encryptData);

            final String decryptStr = URLEncoder.encode(StringUtils.reverse(new String(data)), "UTF-8");
            return URLDecoder.decode(decryptStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("decryptString encryptText {}", encryptText, e);
        }
        return null;
    }

    /**
     * 加密
     *
     * @param publicKey 加密的密钥
     * @param data      待加密的明文数据
     * @return 加密后的密文
     */
    public String encryptString(PublicKey publicKey, String data) {
        byte[] enResult = encrypt(publicKey, data.getBytes());
        return bytesToHexString(enResult);
    }

    /**
     * 使用指定的私钥解密数据。
     *
     * @param publicKey 给定的私钥。
     * @param data      要解密的数据。
     * @return 原数据。
     */
    private byte[] encrypt(PublicKey publicKey, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM, DEFAULT_PROVIDER);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            int blockSize = cipher.getBlockSize();
            int outputSize = cipher.getOutputSize(data.length);
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
            byte[] raw = new byte[outputSize * blocksSize];
            int i = 0;
            while (data.length - i * blockSize > 0) {
                if (data.length - i * blockSize > blockSize) {
                    cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
                } else {
                    cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
                }
                i++;
            }
            return raw;
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException error:", e);
        } catch (NoSuchPaddingException e) {
            logger.error("NoSuchPaddingException error:", e);
        } catch (IllegalBlockSizeException e) {
            logger.error("IllegalBlockSizeException error:", e);
        } catch (BadPaddingException e) {
            logger.error("BadPaddingException error:", e);
        } catch (InvalidKeyException e) {
            logger.error("InvalidKeyException error:", e);
        } catch (ShortBufferException e) {
            logger.error("ShortBufferException error:", e);
        }
        return new byte[1];
    }

    /**
     * 生成密钥对
     *
     * @return
     */
    public KeyPair getKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM, DEFAULT_PROVIDER);
            keyPairGenerator
                    .initialize(KEY_SIZE, new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes()));
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            logger.error("init RSA key failed", e);
            return null;
        }
    }

    /**
     * 使用指定的私钥解密数据。
     *
     * @param privateKey 给定的私钥。
     * @param data       要解密的数据。
     * @return 原数据。
     */
    private byte[] decrypt(PrivateKey privateKey, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM, DEFAULT_PROVIDER);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            int blockSize = cipher.getBlockSize();

            ByteArrayOutputStream bout = new ByteArrayOutputStream(256);     //NOSONAR
            int j = 0;

            while (data.length - j * blockSize > 0) {
                if (data.length - j * blockSize > blockSize) {
                    bout.write(cipher.doFinal(data, j * blockSize, blockSize));
                } else {
                    bout.write(cipher.doFinal(data, j * blockSize, data.length - j * blockSize));
                }
                j++;
            }
            return bout.toByteArray();
        } catch (NoSuchAlgorithmException exception) {
            logger.error("NoSuchAlgorithmException error:", exception);
        } catch (NoSuchPaddingException exception) {
            logger.error("NoSuchPaddingException error:", exception);
        } catch (IllegalBlockSizeException exception) {
            logger.error("IllegalBlockSizeException error:", exception);
        } catch (BadPaddingException exception) {
            logger.error("BadPaddingException error:", exception);
        } catch (InvalidKeyException exception) {
            logger.error("InvalidKeyException error:", exception);
        } catch (IOException exception) {
            logger.error("IOException error:", exception);
        }
        return new byte[0];
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    private String bytesToHexString(byte[] buf) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (buf == null || buf.length <= 0) {
            return null;
        }
        for (byte b : buf) {
            int v = b & 0xFF;     //NOSONAR
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexString
     * @return
     */
    private byte[] parseHexStr2Byte(String hexString) {
        if (hexString == null || "".equals(hexString)) {
            return new byte[]{};
        }
        String uppCaseString = hexString.toUpperCase(Locale.getDefault());
        int length = uppCaseString.length() / 2;
        char[] hexChars = uppCaseString.toCharArray();
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            result[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));//NOSONAR
        }
        return result;
    }

    private byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
