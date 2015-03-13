package com.fanfan.manage.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UpZipUtil extends Thread{
	
	
	private File zipfile;
	
	private String directory;
	
	public void run(){
		upZip(zipfile,directory);
	}
	
	public void upZip(File zipFile,String directory){
		ZipFile zip;
		try {
			zip = new ZipFile(zipFile);
		 
        for(Enumeration entries = zip.entries();entries.hasMoreElements();){  
            ZipEntry entry = (ZipEntry)entries.nextElement();  
            String zipEntryName = entry.getName();  
            InputStream in1 = zip.getInputStream(entry); 
            File upFile = new File(directory);
    		if (!upFile.exists()) {
    			upFile.mkdirs();
    		}
            String outPath = (directory+zipEntryName).replaceAll("\\*", "/");;  
            //判断路径是否存在,不存在则创建文件路径  
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));  
            if(!file.exists()){  
                file.mkdirs();  
            }  
            //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压  
            if(new File(outPath).isDirectory()){  
                continue;  
            }  
            //输出文件路径信息                           
            OutputStream out = new FileOutputStream(outPath);  
            byte[] buf1 = new byte[1024];  
            int len1=0;  
            while((len1=in1.read(buf1))>0){  
                out.write(buf1,0,len1);  
            }  
            in1.close();  
            out.close();  
            }   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public File getZipfile() {
		return zipfile;
	}

	public void setZipfile(File zipfile) {
		this.zipfile = zipfile;
	}
	
	
}
