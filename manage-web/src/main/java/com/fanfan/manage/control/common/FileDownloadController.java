package com.fanfan.manage.control.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fanfan.manage.dao.copyright.CrBookDaoImpl;
import com.fanfan.manage.sdk.wordfactory.WordFactoryMain;
import com.fanfan.manage.service.copyright.CrBookServiceImpl;

@Controller
public class FileDownloadController {
	@Autowired
	private CrBookServiceImpl crBookServiceImpl;
	
	@RequestMapping(value="download/test")
	public void test(HttpServletResponse response){
		response.setContentType("application/octet-stream");
		
		response.addHeader("Content-Disposition", "form-data;name=\"june\";filename=myword.txt");
		response.setHeader("name", "yingu");
		       ServletOutputStream sos;
			try {
				WordFactoryMain wMain =new WordFactoryMain();
				
				sos = response.getOutputStream();
//				Map map =  new HashMap();
//				map.put("books", crBookServiceImpl.list(0, 1));
//				wMain.createWord("", map, sos);
				System.out.println("request find");
				sos.write("hello".getBytes());//动态生成下载的内容
				response.addHeader("Content-Disposition", "form-data;name=\"111\";filename=byebye.txt");
				sos.write("byebye".getBytes());
			    sos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}       
	}
	
	@RequestMapping("form/test")
	public void form_test(HttpServletRequest res,HttpServletResponse response){
		response.setContentType("application/octet-stream");
		
		response.addHeader("Content-Disposition", "form-data;name=\"june\";filename=myword.txt");
		response.setHeader("name", "yingu");
		       ServletOutputStream sos;
			try {
				WordFactoryMain wMain =new WordFactoryMain();
				
				sos = response.getOutputStream();
//				Map map =  new HashMap();
//				map.put("books", crBookServiceImpl.list(0, 1));
//				wMain.createWord("", map, sos);
				System.out.println("request find");
				sos.write("hello".getBytes());//动态生成下载的内容
			    sos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
	}
	
	@RequestMapping(value="download/fortest")
	public void fileUpload(HttpServletRequest  request,HttpServletResponse response) throws FileUploadException, IOException{
		String url = "http://localhost:8080/manage/download/test";
		URL urlObj;		
		HttpURLConnection con;		
		StringBuffer buffer = new StringBuffer();		
		BufferedReader reader = null;				
		urlObj = new URL(url);			
		con = (HttpURLConnection) urlObj.openConnection();
		reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String line = null;
		String name = con.getHeaderField("name");
		String head =  con.getHeaderField("Content-Disposition");
		while ((line = reader.readLine()) != null) {			
			buffer.append(line);			
		}
		System.out.println("the buff is"+buffer.toString());
		System.out.println(head);
		System.out.println("the name is"+name);
		response.getWriter().write("1111111");
	}
	
	
}
