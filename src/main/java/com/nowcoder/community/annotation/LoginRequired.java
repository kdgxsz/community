package com.nowcoder.community.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author 尚郑
 */
// 注解可以写在方法之上,用来描述方法
@Target(ElementType.METHOD)
// 声明注解有效时长,程序运行
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {

}
