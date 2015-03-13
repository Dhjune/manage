package com.fanfan.manage.sdk.wordfactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class WordFactoryMain {
	
	private String listFtl ="";
	private String viewFtl = "";
	
	private Configuration configuration = null;	
	{
		configuration = new Configuration();  
        configuration.setDefaultEncoding("UTF-8"); 
        Properties prop = new Properties();   
	    InputStream in = WordFactoryMain.class.getResourceAsStream("wordsettings.properties");
	    try {   
            prop.load(in);   
            listFtl = prop.getProperty("list_template").trim();   
            viewFtl = prop.getProperty("view_template").trim();   
              
        } catch (IOException e) {   
            e.printStackTrace();   
        } 
        
	}
	
	
	public void createWord(String type,Map<String,Object> map,OutputStream servletOutputStream){
		Map dataMap = map; 
        configuration.setClassForTemplateLoading(this.getClass(), "");  //FTL文件所存在的位置  
        Template t=null;  
        try { 
        	if(type.equals("view")){
        		t = configuration.getTemplate(viewFtl);       		
        	}else{
        		t = configuration.getTemplate(listFtl); 
        	}
           
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        
        Writer out = null;  
        out = new BufferedWriter(new OutputStreamWriter(servletOutputStream));            
        try {  
            t.process(dataMap, out);  
        } catch (TemplateException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
	}
	
	
}
