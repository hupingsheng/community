package com.nowcoder.community.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)  //该注解作用于方法之上
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {


}
