package com.fanfan.manage.inteceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminInfoToken {
	
	boolean superToken() default false;	
	boolean secondToken() default false;
	boolean noneToken() default false;
	boolean cprightAllToken() default false;
	boolean cprightAllLookToken() default false;
	boolean cprightSomeToken() default false;
	boolean cprightSomeLookToken() default false;
	
}
