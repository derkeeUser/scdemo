package com.springcloud.nacos.scnacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: scdemo
 * @description: 测试
 * @author: xiaoming·Chen
 * @create: 2020-05-07 14:29:12
 */
@RestController
@RefreshScope
public class ConfigController {

    @Value("${info.username:cxm}")
    private String username;
    @Value("${info.job:07412}")
    private String jobNumber;
    @Value("${info.company:nothing}")
    private String company;
    @Value("${info.sayhi:nothing}")
    private String sayHi;

    @GetMapping("/test")
    public String config() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append("\"jobNumber\":").append("\"");
        builder.append(jobNumber).append("\",");
        builder.append("\"username\":").append("\"");
        builder.append(username).append("\",");
        builder.append("\"company\":").append("\"");
        builder.append(company).append("\",");
        builder.append("\"sayHi\":").append("\"");
        builder.append(sayHi).append("\",");
        builder.append("}");
        return builder.toString();
    }
}
