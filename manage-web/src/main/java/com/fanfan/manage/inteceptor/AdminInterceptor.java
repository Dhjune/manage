package com.fanfan.manage.inteceptor;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fanfan.manage.modle.admin.AdminLoginInfo;

public class AdminInterceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;		 		 
        Method method = handlerMethod.getMethod();                  
        AdminInfoToken annotation = method.getAnnotation(AdminInfoToken.class);
        if (annotation != null) {
        	if(matchRole(annotation,request)){
        		return true;
        	}else{       		
        		String s = "/copyright";
        		Pattern  pattern=Pattern.compile(s); 
        		String complie =request.getRequestURI();
        		Matcher mt=pattern.matcher(complie); 
        		if(mt.find()){	
        			response.sendRedirect("/manage/admin/error/cr_permission");
        		}else{
        			response.sendRedirect("/manage/admin/error/permission");
        			}
        		return false;
        	}
        	
        }
		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub		
	}

	public boolean matchRole(AdminInfoToken annotation,HttpServletRequest request){
		
		HttpSession session = request.getSession();		
		AdminLoginInfo admin =  (AdminLoginInfo) session.getAttribute("admin");
		if(admin!=null){
			
			if(annotation.noneToken()){
			
				return true;
				
			}
			
			if(annotation.secondToken()){
				
				if(admin.getRole_name().equals("普通管理员")||admin.getRole_name().equals("超级管理员")){
					
					return true;
					
				}else{
					
					return false;
					
				}
			}
			
			if(annotation.cprightAllToken()){
				if(admin.getRole_name().equals("版权超级管理员A")){
					
					return true;
					
				}else{
					
					return false;
					
				}
			}
			if(annotation.cprightSomeToken()){
				if(admin.getRole_name().equals("版权普通管理员C")||admin.getRole_name().equals("版权超级管理员A")){
					
					return true;
					
				}else{
					
					return false;
					
				}
			}
			if(annotation.cprightSomeLookToken()){
				if(admin.getRole_name().equals("版权普通管理员D")||admin.getRole_name().equals("版权超级管理员B")||admin.getRole_name().equals("版权超级管理员A")||admin.getRole_name().equals("版权普通管理员C")){
					
					return true;
					
				}else{
					
					return false;
					
				}
			}
					
			if(annotation.superToken()){
				
				if(admin.getRole_name().equals("超级管理员")){
					
					return true;
					
				}else{
					
					return false;
					
				}
			}
			
		}else{
			
			return false;
			
		}
		
		return false;
	}
	
	
	
}
