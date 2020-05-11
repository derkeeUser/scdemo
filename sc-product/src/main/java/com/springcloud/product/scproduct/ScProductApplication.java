package com.springcloud.product.scproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <功能简述> 
 * <功能详细描述>  
 * @Author: xiaoming·Chen
 * @params: 服务提供方
 * @date: 2020/4/27 10:04
 * @return: 
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ScProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScProductApplication.class, args);
        System.out.println("服务提供方启动成功 √");
    }
}
