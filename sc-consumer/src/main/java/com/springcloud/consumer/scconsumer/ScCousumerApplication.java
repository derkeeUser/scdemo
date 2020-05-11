package com.springcloud.consumer.scconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class ScCousumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScCousumerApplication.class, args);
        System.out.println("服务消费方启动成功 √");
    }

}
