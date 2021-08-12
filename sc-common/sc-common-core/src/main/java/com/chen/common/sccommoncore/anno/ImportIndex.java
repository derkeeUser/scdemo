package com.chen.common.sccommoncore.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: scdemo
 * @description: 导入时索引，从0开始
 * @author: chenxiaoming
 * @create: 2021-08-12 17:55:14
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ImportIndex {
    //索引
    int index() ;
    //设置set方法
    String useSetMethodName() default "";
}
