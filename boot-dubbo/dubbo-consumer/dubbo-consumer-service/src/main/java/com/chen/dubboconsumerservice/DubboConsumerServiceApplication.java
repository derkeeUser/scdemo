package com.chen.dubboconsumerservice;

import com.alibaba.fastjson.JSONObject;
import com.chen.dubboproviderapi.api.UserRpcService;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DubboConsumerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerServiceApplication.class, args);
    }
}
