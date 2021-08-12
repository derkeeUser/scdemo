package com.chen.common.sccommoncore.anno;

import com.chen.common.sccommoncore.enums.ColorEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: scdemo
 * @description: 设置允许导出
 * @author: chenxiaoming
 * @create: 2021-08-12 17:48:51
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableExport {
    //设置文件名/标题
    String fileName();
    //设置背景颜色
    ColorEnum cellColor() default ColorEnum.BLUE;
}
