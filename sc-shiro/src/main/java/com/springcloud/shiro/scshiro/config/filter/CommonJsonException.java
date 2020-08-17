package com.springcloud.shiro.scshiro.config.filter;

import com.alibaba.fastjson.JSONObject;

/**
 * @program: scdemo
 * @description: 通用异常类
 * @author: xiaoming.Chan
 * @create: 2020-08-14 13:47:55
 */
public class CommonJsonException extends RuntimeException {
    private JSONObject resultJson;

    /**
     * 调用时可以在任何代码处直接throws这个Exception,
     * 都会统一被拦截,并封装好json返回给前台
     */
    public CommonJsonException() {
    }

    public CommonJsonException(JSONObject resultJson) {
        this.resultJson = resultJson;
    }

    public JSONObject getResultJson() {
        return resultJson;
    }
}
