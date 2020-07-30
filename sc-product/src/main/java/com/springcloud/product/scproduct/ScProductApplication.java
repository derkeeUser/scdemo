package com.springcloud.product.scproduct;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

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

    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(
            @Value("${spring.application.name}") String applicationName) {
        return (registry) -> registry.config().commonTags("application", applicationName);
    }
}
