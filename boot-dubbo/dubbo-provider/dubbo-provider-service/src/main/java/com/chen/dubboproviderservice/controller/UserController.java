package com.chen.dubboproviderservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.chen.dubboproviderapi.api.UserRpcService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: scdemo
 * @description: 测试
 * @author: chenxiaoming
 * @create: 2021-05-08 17:28:54
 */
@RestController
public class UserController {

    private final UserRpcService userRpcService;

    public UserController(UserRpcService userRpcService) {
        this.userRpcService = userRpcService;
    }

    @GetMapping("/health")
    public String sayHello() {
        return JSONObject.toJSONString(userRpcService.getUserList());
    }
}
