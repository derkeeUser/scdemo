package com.springcloud.gateway.scgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: scdemo
 * @description: 心跳
 * @author: xiaoming·Chen
 * @create: 2020-05-06 09:56:52
 */
@RestController
public class HealthController {

    @GetMapping("/health")
    public String health() {
        return "ok";
    }
}
