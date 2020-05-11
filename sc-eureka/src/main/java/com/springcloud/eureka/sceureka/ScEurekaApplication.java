package com.springcloud.eureka.sceureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <功能简述> 
 * <功能详细描述>  
 * @Author: xiaoming·Chen
 * @params: 注册中心
 * @date: 2020/4/27 9:40
 * @return: 
 */
@SpringBootApplication
@EnableEurekaServer
public class ScEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScEurekaApplication.class, args);
        System.out.println("注册中心启动成功:http://localhost:8090/eureka √");
    }

}
