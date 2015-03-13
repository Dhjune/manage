package com.fanfan.manage.common;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


//@Aspect
//@Service
public class AdminOperateAspect {
	
	// 创建切点，会让在com.fanfan.manage包下所有的子包下的class都创建的代理对象（动态代理）
	//@Pointcut("execution(public * com.fanfan.manage.control..*.*(..))")
	public void control(){}
	
	//此前出现重复激活切点，因为拦截器的实现也是在这个包下，所以拦截器的方法也匹配了切点，进行的AOP操作。
	//@Before("control()")
	public void cBefore(JoinPoint point){
		Object [] args = point.getArgs();
		
		int i = 0;
		for (Object obj : point.getArgs()) {
			if(obj instanceof HttpServletRequest){
				
				System.out.println("find request");
			}
			i++;
			System.out.print(i);
			System.out.println(obj);
			
		}
//		System.out.print(i);
//		System.out.println(point.getTarget());
			
	}
}
