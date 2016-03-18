package com.xwheel.xmonitor.commons.utils;

import com.xwheel.xmonitor.commons.constants.CharsetEncodingConstants;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

/**
 * 扩展的String工具类
 */
public class ExtendStringUtils {
    /**
     * byte[]转换为String，默认为UTF-8
     *
     * @param bts
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String bytes2String(byte[] bts) {
        return bytes2String(bts, CharsetEncodingConstants.UTF_8);
    }

    /**
     * byte[]转换为String， 如果没有编码默认为UTF-8
     *
     * @param bts     - byte数组
     * @param charset - 编码
     * @return 转换后的字符串
     */
    public static String bytes2String(byte[] bts, String charset) {
        try {
            if (StringUtils.isNotEmpty(charset)) {
                return new String(bts, charset);
            }

            return new String(bts, CharsetEncodingConstants.UTF_8);
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * 将String转换成byte[]
     *
     * @param str 字符串
     * @return 转换后的byte数组
     */
    public static byte[] string2Bytes(String... str) {
        StringBuilder builder = new StringBuilder();

        if (null == str) {
            return new byte[0];
        }
        for (String s : str) {
            builder.append(s);
        }
        try {
            return builder.toString().getBytes(CharsetEncodingConstants.UTF_8);
        } catch (UnsupportedEncodingException e) {
            return new byte[0];
        }
    }

    /**
     * 转换为16进制字符串
     *
     * @param str
     * @return 16进制字符串表示
     */
    public static String parseStrToHex(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }

        byte[] buffer = str.getBytes();
        return parseBytesToHex(buffer);
    }

    /**
     * 将byte数组装换成16进制串
     *
     * @param bytes 待转换的数组
     * @return 16进制串
     */
    public static String parseBytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        int index = 0;
        while (index < bytes.length) {
            String hex = Integer.toHexString(bytes[index] & 0xFF); //NOSONAR
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase(Locale.getDefault()));
            index++;
        }
        return sb.toString();
    }

    /**
     * 将十六进制转化为字符串
     * sundemeng add
     *
     * @param hexStr
     * @return
     */
    public static String parseHexToStr(String hexStr) {
        if (StringUtils.isEmpty(hexStr)) {
            return "";
        }
        byte[] buffer = new byte[hexStr.length() / 2];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = (byte) (0xFF & Integer.parseInt(hexStr.substring(i * 2, i * 2 + 2), 16)); //NOSONAR
        }
        return bytes2String(buffer, CharsetEncodingConstants.UTF_8);
    }
}