package com.demo.pay.util;

import java.util.UUID;

/**
 * @Author: sunYF
 * @Description:
 * @Date: Create in 10:11 2020/9/28
 */
public class UUIDUtil {
    /**
     * 带-的UUID
     *
     * @return 36位的字符串
     */
    public static String getUUID2() {
        return UUID.randomUUID().toString();
    }

    /**
     * 去掉-的UUID
     *
     * @return 32位的字符串
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
