package com.chen.dubboconsumerservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.chen.dubboproviderapi.api.UserRpcService;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: scdemo
 * @description: 订单
 * @author: chenxiaoming
 * @create: 2021-05-08 18:13:39
 */
@RestController
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Reference(version = "${dubbo.consumer.UserRpcService.version}")
    private UserRpcService userRpcService;

    @GetMapping("/test")
    public String sayHello() {
        logger.info("访问成功=========");
        return JSONObject.toJSONString(userRpcService.getUserList());
    }
}
