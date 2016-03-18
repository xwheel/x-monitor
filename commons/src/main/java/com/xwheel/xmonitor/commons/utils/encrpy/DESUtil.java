package com.xwheel.xmonitor.commons.utils.encrpy;

import com.xwheel.xmonitor.commons.utils.ExtendStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;

/**
 * DES/3DES数据加/解密工具类
 */

public final class DESUtil {

    /**
     * 3DES加密方式
     */
    public static final String ALGORITHM_3DES = "DESede";

    /**
     * DES加密方式
     */
    public static final String ALGORITHM_DES = "DES";

    /**
     * 3DES密钥字符串
     */
    public static final String SECRET_KEY_STRING = "12345678abcdefghABCDEFGH";

    /**
     * 补位byte数组
     */
    private static final byte[] BYTE_ARRAY = {0, 0, 0, 0, 0, 0, 0, 0};

    /**
     * 3DES加密填充方式
     */
    private static final String ALGORITHM_3DESCBC = "/CBC/PKCS5Padding";

    private static final Logger LOGGER = LoggerFactory.getLogger(DESUtil.class);

    private DESUtil() {

    }

    /**
     * 根据密钥字符串生成密钥
     *
     * @param keyStr    密钥字符串
     * @param algorithm 加密方式
     * @return 密钥
     */
    public static SecretKey createKeyByNormalString(String keyStr, String algorithm) {
        return new SecretKeySpec(ExtendStringUtils.string2Bytes(keyStr), algorithm);
    }

    /**
     * 3des加密
     *
     * @param secretKey 密钥
     * @param msg       明文
     * @param algorithm 加密方式
     * @return 密文
     */
    public static byte[] encrypt(SecretKey secretKey, byte[] msg, String algorithm) {
        try {
            return doCipher(algorithm, secretKey, Cipher.ENCRYPT_MODE, msg);
        } catch (SecurityException e) {
            LOGGER.error(algorithm + " encrypt error", e);
        }
        return new byte[0];
    }

    /**
     * 3des加密
     *
     * @param secretKey 密钥
     * @param msg       明文
     * @param algorithm 加密方式
     * @return 密文
     */
    public static byte[] encrypt(SecretKey secretKey, String msg, String algorithm) {
        try {
            return doCipher(algorithm, secretKey, Cipher.ENCRYPT_MODE, ExtendStringUtils.string2Bytes(msg));
        } catch (SecurityException e) {
            LOGGER.error(algorithm + " encrypt error", e);
        }
        return new byte[0];
    }

    /**
     * 解码
     *
     * @param secretKey 密钥
     * @param msg       密文
     * @param algorithm 加密方式
     * @return 明文
     */
    public static byte[] decrypt(SecretKey secretKey, byte[] msg, String algorithm) {
        try {
            return doCipher(algorithm, secretKey, Cipher.DECRYPT_MODE, msg);
        } catch (SecurityException e) {
            LOGGER.error(algorithm + " encrypt error", e);
        }
        return new byte[0];
    }

    /**
     * 对字符串以指定密钥进行DES加密
     *
     * @param plainText 加密前字符串
     * @param desKey    密钥
     * @return 加密后byte数组
     */
    public static byte[] doDESEncrypt(String plainText, String desKey) {
        Cipher cipher = getDESCipher(desKey, Cipher.ENCRYPT_MODE);
        try {
            return cipher.doFinal(plainText.getBytes());
        } catch (GeneralSecurityException e) {
            LOGGER.error("DES encrypt error", e);
        }
        return new byte[0];
    }

    /**
     * 获取DES加解密的Cipher
     *
     * @param desKey     密钥
     * @param cipherMode 操纵模式
     * @return Cipher
     */
    private static Cipher getDESCipher(String desKey, int cipherMode) {
        SecureRandom secureRandom = new SecureRandom();
        try {
            DESKeySpec desKeySpec = new DESKeySpec(desKey.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM_DES);
            SecretKey key = keyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            cipher.init(cipherMode, key, secureRandom);
            return cipher;
        } catch (GeneralSecurityException e) {
            throw new SecurityException(e);
        }
    }

    private static byte[] doCipher(String algorithm, SecretKey secretKey, int cipherMode, byte[] msg)
            throws SecurityException {
        try {
            Cipher cipher = Cipher.getInstance(algorithm + ALGORITHM_3DESCBC);
            cipher.init(cipherMode, secretKey, new IvParameterSpec(BYTE_ARRAY), new SecureRandom());
            return cipher.doFinal(msg);
        } catch (GeneralSecurityException e) {
            throw new SecurityException(e);
        }
    }

    /**
     * 解码
     *
     * @param secretKey 密钥
     * @param msg       密文
     * @return 明文
     */
    public static byte[] decrypt(String secretKey, byte[] msg) {
        try {
            Cipher cipher = getDESCipher(secretKey, Cipher.DECRYPT_MODE);
            try {
                return cipher.doFinal(msg);
            } catch (IllegalBlockSizeException e) {
                LOGGER.info("decrypt IllegalBlockSizeException error ", e);
            } catch (BadPaddingException e) {
                LOGGER.info("decrypt BadPaddingException error ", e);
            }
        } catch (SecurityException e) {
            LOGGER.error("decrypt SecurityException error", e);
        }
        return new byte[0];
    }

}
