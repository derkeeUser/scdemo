package com.springcloud.consumer.scconsumer.service;

import com.springcloud.consumer.scconsumer.service.hystrix.ProductServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: scdemo
 * @description: 产品业务
 * @author: xiaoming·Chen
 * @create: 2020-04-27 10:20:54
 */
@FeignClient(name = "product-server", fallback = ProductServiceHystrix.class)
public interface ProductService {

    /**
     * <功能简述> 获取列表数据
     * <功能详细描述> 获取提供方提供的数据：使用feign时需要在参数前添加@RequestParam注解，此处需注意！！！
     *
     * @Author: xiaoming·Chen
     * @params: id
     * @date: 2020/4/27 18:48
     * @return:
     */
    @GetMapping(value = "/product/selectOne")
    Object getProduct(@RequestParam("id") Integer id);
}
