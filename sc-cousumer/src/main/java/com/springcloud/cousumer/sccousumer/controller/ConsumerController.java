package com.springcloud.cousumer.sccousumer.controller;

import com.springcloud.cousumer.sccousumer.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: scdemo
 * @description: 消费方接口
 * @author: xiaoming·Chen
 * @create: 2020-04-27 10:24:25
 */
@RestController
@Slf4j
@RequestMapping("/consumer")
public class ConsumerController {

    private final ProductService productService;

    public ConsumerController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get_product")
    public String getConsumer(Integer id) {
        try {
            return productService.getProduct(id);
        } catch (Exception e) {
            log.error("获取数据异常：{}", e.getMessage(), e);
            return e.getMessage();
        }
    }
}
