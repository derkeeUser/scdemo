package com.chen.common.sccommoncore.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: scdemo
 * @description: 允许使用下拉列表
 * @author: chenxiaoming
 * @create: 2021-08-12 17:54:48
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableSelectList {
}
