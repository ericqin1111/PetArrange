package com.example.petarrange.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Order(Ordered.HIGHEST_PRECEDENCE+1)
public @interface EncryptMethod {

    int[] value()default {1};

    boolean enabledDecrypt() default true;
}
