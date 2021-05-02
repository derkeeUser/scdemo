package com.chen.common.sccommoncore.jucdemo.function;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @program: scdemo
 * @description: 函数式接口/断定型接口/消费性接口/供给型接口
 * @author: chenxiaoming
 * @create: 2021-04-30 14:55:23
 */
public class FunctionDemo {
    public static void main(String[] args) {
        // 函数式接口：有输入参数和输出参数(输入输出参数可自定义类型)
        Function<String,String> function = s -> "hello function!!!" + s;
        System.out.println(function.apply("Mr.Chen"));

        // 断定型接口：有输入参数和输出参数(输入参数可自定义类型，返回参数固定为boolean类型)
        Predicate<String> predicate = String::isEmpty;
        System.out.println(predicate.test("predicate"));

        // 消费性接口：有输入参数，无输出参数
        Consumer<String> consumer = System.out::println;
        consumer.accept("hello consumer!!!");

        // 供给型接口：无输入参数，有输出参数
        Supplier<String> supplier = () -> "supplier server";
        supplier.get();
    }
}
