package com.fanfan.manage.service.vector;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fanfan.manage.common.AbstractVector;
import com.fanfan.manage.modle.admin.AdminLog;
import com.fanfan.manage.service.admin.AdminLogServiceImpl;

@Component
public class AdminOperateVector extends AbstractVector{
	
	@Autowired
	private AdminLogServiceImpl adminLogServiceImpl;
	
	private static Log log = LogFactory.getLog(AdminOperateVector.class.getName());
	
	
	public <T> void operate(T obj,Map map)  throws Exception{
		Table table  =  obj.getClass().getAnnotation(Table.class);
		String tableName = table.name();
		System.out.println("table name is :"+tableName);
//		System.out.println("the class is:"+obj.getClass().toString());
		Class clazz = obj.getClass();
		Field[] fileds =  clazz.getDeclaredFields();
		Field field = null;
		String row_id ="";
		try {
			for (Field item : fileds) {
				String fieldName = "";
				String methodName = "";
				if(item.getAnnotation(Id.class)!=null){
					fieldName = item.getName();
					methodName = "get"+toFirstLetterUpperCase(fieldName);
					Object object =  clazz.getMethod(methodName).invoke(obj);
					Column col = item.getAnnotation(Column.class);
					if(!row_id.equals("")){
						row_id += ",";
					}
					row_id = col.name()+":"+object.toString();
//					System.out.println("the id is"+row_id);
				}else if(item.getAnnotation(EmbeddedId.class)!=null){
					fieldName = item.getName();
					methodName = "get"+toFirstLetterUpperCase(fieldName);
					Object object =  clazz.getMethod(methodName).invoke(obj);
					row_id = analysis(object,row_id);
				}
			}
		}
//			field = clazz.getDeclaredField("pk");	
//			Annotation[] annos = field.getAnnotations();
//			for(Annotation annotation :annos ){
//				String annoStr = annotation.toString();
//				
//				if(annoStr.endsWith("EmbeddedId()")){
//					System.out.println("this method id ok");
//				}
//				System.out.println("annotation is :"+annotation.toString());
//				
//			}
//			String name= field.getName();
//			String getMethodName = "get"+toFirstLetterUpperCase(name);
//			Object pk = clazz.getMethod(getMethodName).invoke(obj);
//			Class pkClazz = pk.getClass();
//			
//			
//			System.out.println(pk);
//			
//			for(Field item :pkClazz.getDeclaredFields()){
//				System.out.println(item.toString());
//			}
			
			
			//System.out.println(field.toString());
		catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for(Field item :fileds){
//			
//			System.out.println(item.toString());
//		}
		AdminLog lg = new AdminLog();
	//	System.out.println(map.get("adminId"));
		lg.setAdminId(Integer.parseInt(map.get("adminId").toString()));
		lg.setRowId(row_id);
		lg.setLogType(1);
		lg.setLogTable(tableName);
		lg.setLogLevel(2);
		lg.setIpAddr(map.get("ipAddr").toString());
		lg.setLogInfo(map.get("logInfo").toString());
		//System.out.println("the logInfo :"+map.get("logInfo").toString());
		lg.setAdditionalInfo("");
		adminLogServiceImpl.put(lg);
		
	}
	
	
	
	
	public String  analysis(Object obj,String row_id){
		Class clazz = obj.getClass();
		Field  fields[] = clazz.getDeclaredFields();
		for(Field item : fields){
			String getMethodName = "";
			Object val=null;
//			Annotation[] annos = item.getAnnotations();			
//			item.getAnnotation(JoinColumn.class);
//			for(Annotation annotation :annos ){
//				System.out.println(annotation.toString());
//			}
			try {
				if(item.getAnnotation(JoinColumn.class)!=null){
					getMethodName = "get"+toFirstLetterUpperCase(item.getName());
					val = clazz.getMethod(getMethodName).invoke(obj);
					
					Field field =  val.getClass().getDeclaredField("id");
					
					getMethodName ="get"+toFirstLetterUpperCase(field.getName());
					
					Object object = val.getClass().getMethod(getMethodName).invoke(val);
					Column col = field.getAnnotation(Column.class);
					if(!row_id.equals("")){
						row_id += ",";
					}
					row_id += col.name()+":"+object.toString();
//					System.out.println("PK Id is:"+row_id);
					
					
					
				}
								
				
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				
				val.getClass().getDeclaredField("id");
				
			} catch (Exception e){
				
			}
		}
		return row_id;
	}
	
	
	public static String toFirstLetterUpperCase(String str) {  
	    if(str == null || str.length() < 2){  
	        return str;  
	    }  
	     String firstLetter = str.substring(0, 1).toUpperCase();  
	     return firstLetter + str.substring(1, str.length());  
	 }  
	
	
}
