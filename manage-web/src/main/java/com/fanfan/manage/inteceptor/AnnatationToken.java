package com.fanfan.manage.inteceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnatationToken {
    boolean SaveToken() default false;
    boolean RemoveToken() default false;
}
