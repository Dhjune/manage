package com.fanfan.manage.control.account;

import java.io.File;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Created with IntelliJ IDEA.
 * User: fanfan
 * Date: 14-3-4
 * Time: 上午9:15
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class AccountController {

    @RequestMapping(value = "account",method = RequestMethod.GET)
    public String account(){
        return "account/index";
    }
    
    @RequestMapping(value = "account/error/repeat",method = RequestMethod.GET)
    public String error(){
    	
    	return "account/error/repeat";	
    }
    /*
    @RequestMapping(value="account/test/get",method=RequestMethod.GET)
    public String  get(){
    	return "account/error/form";
    }
    
    @RequestMapping(value="account/test/post",method=RequestMethod.POST)
    public String post(HttpServletRequest res,@RequestParam("file1") CommonsMultipartFile file,
    		@RequestParam("file2") File file2){
    	System.out.println("file1"+file.toString());
    	System.out.println("file2"+file2.toString());
    	if (!((CommonsMultipartFile) file).isEmpty()) {
			   String path = "D:/test/";  //获取本地存储路径
			   System.out.println(path);
			   String fileName = file.getOriginalFilename();
			   String fileType = fileName.substring(fileName.lastIndexOf("."));
			   System.out.println(fileType); 
			   File file3 = new File(path,new Date().getTime() + fileType); //新建一个文件
			   try {
				    ((CommonsMultipartFile) file).getFileItem().write(file2); //将上传的文件写入新建的文件中
			   } catch (Exception e) {
				    e.printStackTrace();
			   }
			   return "account/error/form";
			}else{
				return "account/error/form";
			}

    	
    }
    */
    @RequestMapping(value="admin/error/permission")
    public String permission(){
    	return "account/error/permission";
    	
    }
    
    @RequestMapping(value="admin/error/cr_permission")
    public String cr_permission(){
    	return "account/error/cr_permission";
    	
    }

	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		
	}

}
