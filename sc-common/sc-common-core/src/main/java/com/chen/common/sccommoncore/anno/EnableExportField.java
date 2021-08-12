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
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableExportField {
    //宽度
    int colWidth() default  100;
    //标题名称
    String colName();
    //设置get方法
    String useGetMethod() default "";
    //设置背景颜色
    ColorEnum cellColor() default ColorEnum.BLUE;
}
