package com.springcloud.consumer.scconsumer.service.hystrix;

import com.springcloud.consumer.scconsumer.service.ProductService;
import org.springframework.stereotype.Component;

/**
 * @program: scdemo
 * @description:
 * @author: xiaoming·Chen
 * @create: 2020-05-18 14:35:14
 */
@Component
public class ProductServiceHystrix implements ProductService {
    @Override
    public Object getProduct(Integer id) {
        return "服务断开，请稍后使用";
    }
}
