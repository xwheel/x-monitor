package com.xwheel.xmonitor.commons.utils;

import java.util.UUID;

/**
 * @description: 全局标示符工具
 * 保证同一时空所有机器唯一，由当前时间、日期、时钟序列、全局唯一的IEEE机器识别号
 */
public class GenerateUUIDUtils {

    /**
     * 获取全局唯一标识符
     *
     * @return 32位长度唯一主键
     */
    public static String createUUID() {

        String uuidStr = UUID.randomUUID().toString();
        return uuidStr.replaceAll("-", "");
    }
}
