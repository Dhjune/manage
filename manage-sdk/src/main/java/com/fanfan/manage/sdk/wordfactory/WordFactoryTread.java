package com.fanfan.manage.sdk.wordfactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

public class WordFactoryTread extends Thread{
	
	private WordFactoryMain wMain;
	
	private Map map;
	
	private OutputStream servletOutputStream;
	
	private String templateFtl;
	
	{
		Properties prop = new Properties();   
	    InputStream in = Object.class.getResourceAsStream("/wordsettings.properties");
	    try {   
            prop.load(in);   
            String templateFtl = prop.getProperty("template").trim();   
              
        } catch (IOException e) {   
            e.printStackTrace();   
        } 
	}
	
	
	@Override
	public void run() {
		wMain.createWord(templateFtl,map, servletOutputStream);
	}


	public OutputStream getServletOutputStream() {
		return servletOutputStream;
	}


	public void setServletOutputStream(OutputStream servletOutputStream) {
		this.servletOutputStream = servletOutputStream;
	}


	public Map getMap() {
		return map;
	}


	public void setMap(Map map) {
		this.map = map;
	}


	public String getTemplateFtl() {
		return templateFtl;
	}


	public void setTemplateFtl(String templateFtl) {
		this.templateFtl = templateFtl;
	}


	public WordFactoryMain getwMain() {
		return wMain;
	}


	public void setwMain(WordFactoryMain wMain) {
		this.wMain = wMain;
	}
	
}
