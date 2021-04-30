package com.springcloud.common.sccommon.utils;

import java.util.UUID;

/**
 * @program: scdemo
 * @description: UUID生成
 * @author: Chenxiaoming
 * @create: 2020-07-31 16:10:19
 */
public class UUIDUtil {

    /**
     * @description: UUID生成
     * @Author: Chenxiaoming
     * @date: 2020/7/31 16:11
     * @return: java.lang.String
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
