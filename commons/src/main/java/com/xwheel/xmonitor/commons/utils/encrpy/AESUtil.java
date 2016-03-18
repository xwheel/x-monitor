package com.xwheel.xmonitor.commons.utils.encrpy;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * @description: AES对称加密和解密工具类
 */
public class AESUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(AESUtil.class);

    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "AES";

    /**
     * 加密解密算法 工作模式 填充方式
     */
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    //加密的密钥
    private static final String SECURE_KEY = "!%4&M2M&#1234560";

    private static BouncyCastleProvider provider = new BouncyCastleProvider();

    /**
     * 生成密钥
     *
     * @return 二进制密钥
     * @throws Exception
     */
    private static byte[] initKey() {
        return SECURE_KEY.getBytes();
    }

    /**
     * 根据提供的密钥生成安全密钥 Key
     *
     * @return 二进制密钥
     * @throws Exception
     */
    private static byte[] initKey(String keyPassword) {
        return keyPassword.getBytes();
    }

    /**
     * 转换密钥，二进制密钥转换为密钥对象
     *
     * @param key 二级制密钥
     * @return Key 密钥
     * @throws Exception
     */
    private static Key toKey(byte[] key) {
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }

    /**
     * 加密
     *
     * @param data 需要加密的内容
     * @return
     */
    public static byte[] encrypt(byte[] data) {
        try {
            byte[] key = initKey();
            // 实例化，创建密码器
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            // 初始化  设置为加密模式
            cipher.init(Cipher.ENCRYPT_MODE, toKey(key));
            // 加密
            return cipher.doFinal(data);

        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("encrpy NoSuchAlgorithmException error " + e);
        } catch (NoSuchPaddingException e) {
            LOGGER.error("encrpy NoSuchPaddingException error " + e);
        } catch (InvalidKeyException e) {
            LOGGER.error("encrpy InvalidKeyException error " + e);
        } catch (IllegalBlockSizeException e) {
            LOGGER.error("encrpy IllegalBlockSizeException error " + e);
        } catch (BadPaddingException e) {
            LOGGER.error("encrpy BadPaddingException error " + e);
        }
        return new byte[0];
    }

    /**
     * 解密
     *
     * @param encrpyData 待解密内容
     * @return
     */
    public static String decrypt(String encrpyData) {
        try {
            byte[] key = initKey();
            byte[] data = parseHexStr2Byte(encrpyData);
            // 实例化，创建密码器
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            // 初始化  设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, toKey(key));
            byte[] decryptBytes = cipher.doFinal(data);
            return new String(decryptBytes);
        } catch (NoSuchAlgorithmException exp) {
            LOGGER.error("decrypt NoSuchAlgorithmException error " + exp);
        } catch (NoSuchPaddingException exp) {
            LOGGER.error("decrypt NoSuchPaddingException error " + exp);
        } catch (InvalidKeyException exp) {
            LOGGER.error("decrypt InvalidKeyException error " + exp);
        } catch (IllegalBlockSizeException exp) {
            LOGGER.error("decrypt IllegalBlockSizeException error " + exp);
        } catch (BadPaddingException exp) {
            LOGGER.error("decrypt BadPaddingException error " + exp);
        }
        return null;
    }

    /**
     * 根据安全提供商加密
     *
     * @param data 需要加密的内容
     */
    public static byte[] encrypt(byte[] data, String keyPassword) {
        try {
            byte[] keyValue = initKey(keyPassword);
            // 实例化，创建密码器
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, provider);
            // 初始化  设置为加密模式
            cipher.init(Cipher.ENCRYPT_MODE, toKey(keyValue));
            // 加密
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException exception) {
            LOGGER.error("encrpy NoSuchAlgorithmException error " + exception);
        } catch (NoSuchPaddingException exception) {
            LOGGER.error("encrpy NoSuchPaddingException error " + exception);
        } catch (InvalidKeyException exception) {
            LOGGER.error("encrpy InvalidKeyException error " + exception);
        } catch (IllegalBlockSizeException exception) {
            LOGGER.error("encrpy IllegalBlockSizeException error " + exception);
        } catch (BadPaddingException exception) {
            LOGGER.error("encrpy BadPaddingException error " + exception);
        }
        return new byte[0];
    }

    /**
     * 解密
     *
     * @param encrpyData 待解密内容
     * @return
     */
    public static String decrypt(String encrpyData, String keyPassword) {
        try {
            byte[] key = initKey(keyPassword);
            byte[] data = parseHexStr2Byte(encrpyData);
            // 实例化，创建密码器
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            // 初始化  设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, toKey(key));
            return new String(cipher.doFinal(data));
        } catch (NoSuchAlgorithmException ep) {
            LOGGER.error("encrpy NoSuchAlgorithmException error " + ep);
        } catch (NoSuchPaddingException ep) {
            LOGGER.error("encrpy NoSuchPaddingException error " + ep);
        } catch (InvalidKeyException ep) {
            LOGGER.error("encrpy InvalidKeyException error " + ep);
        } catch (IllegalBlockSizeException ep) {
            LOGGER.error("encrpy IllegalBlockSizeException error " + ep);
        } catch (BadPaddingException ep) {
            LOGGER.error("encrpy BadPaddingException error " + ep);
        }
        return null;
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return new byte[]{};
        }
        final int hex = 16;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), hex);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), hex);
            result[i] = (byte) (high * hex + low);
        }
        return result;
    }
}
