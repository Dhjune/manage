package com.fanfan.manage.control.book;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fanfan.manage.common.UpZipUtil;

@Controller
public class BookUpLoadController {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	private  PrintWriter writer = null;
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = {"/book/file/upload","/book/pic/upload"}, method = RequestMethod.POST)
	public void fileUpload(HttpServletRequest  request,
			HttpServletResponse response) throws ServletException, IOException,
			FileUploadException {
		
		
		String book_id = "";
		String part_id = "";
		String plat_id = "";
				
		ServletContext application = request.getSession().getServletContext();
		String savePath = application.getRealPath("/") + "upload/";
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		// 文件保存目录URL
		String saveUrl = basePath + "upload/";		
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,epub");
		// 最大文件大小
		long maxSize = 1000000000;
		response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        writer = response.getWriter();
       // writer.println(json);  //想办法把map转成json		
		if (!ServletFileUpload.isMultipartContent(request)) {
			writer.println(objectMapper.writeValueAsString(getError("请选择文件。")));
			return;
			
		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			uploadDir.mkdirs();			
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			
			writer.println(objectMapper.writeValueAsString(getError("上传目录没有写权限。")));
			return;
		}
		String dirName = request.getParameter("dir");
		
		if (dirName == null) {
			dirName = "file";
		}
		if (!extMap.containsKey(dirName)) {
			writer.println(objectMapper.writeValueAsString(getError("目录名不正确。")));
			return;
		}
		// 创建文件夹
		

		FileItemFactory factory = new DiskFileItemFactory();
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		
		List items = upload.parseRequest(request);
		Iterator itr = items.iterator();
		String fileExt = "";
		Map<String, Object> msg = new HashMap<String, Object>();
		int fileSize = 0;		
		String newFileName = "";
		FileItem fileit =null;
		while (itr.hasNext()) {
			FileItem item =(FileItem) itr.next();
			String fileName = item.getName();
			
			if(item.isFormField()){
				
				if("book_id".equals(item.getFieldName())){					
					book_id = item.getString().trim();						
				}
				if("plat_id".equals(item.getFieldName())){
					plat_id = item.getString().trim();					
				}
				if("part_id".equals(item.getFieldName())){
					part_id = item.getString().trim();					
				}
				
			}else if (!item.isFormField()) {
				fileit = item;
				
				// 检查文件大小				
				fileSize += item.getSize();			
				if (item.getSize() > maxSize) {
					
					writer.println(objectMapper.writeValueAsString(getError("上传文件大小超过限制。")));
				}
				
				// 检查扩展名
				fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.<String> asList(extMap.get(dirName).split(","))
						.contains(fileExt)&&!Arrays.<String> asList(extMap.get("file").split(","))
						.contains(fileExt)) {
					writer.println(objectMapper.writeValueAsString(getError("上传文件扩展名是不允许的扩展名。\n只允许"+ extMap.get(dirName) + "格式。")));
					return;
				}
				
				
				
			}
			
			
			
		}
		savePath += dirName + "/" +plat_id +"/"+book_id;
		saveUrl += dirName + "/"+plat_id+"/"+book_id;
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		
		savePath +=  "/";
		saveUrl +=  "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		if(dirName.equals("image")){
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			newFileName = df.format(new Date()) + "_"
					+ new Random().nextInt(1000) + "." + fileExt;
			
		}else if (dirName.equals("file")) {
			
			newFileName = book_id+"_" + part_id;			
			newFileName += "." +fileExt;			
		}	
		String directory = savePath+book_id+"_" + part_id+"/";
		try {					
			File uploadedFile = new File(savePath, newFileName);
			byte[] buf = new byte[1024];
			int len = -1;
			InputStream in = fileit.getInputStream();
			FileOutputStream fos = new FileOutputStream(uploadedFile);
            while((len = in.read(buf)) != -1){
                fos.write(buf, 0, len);
            }
            if(in != null){
                try{
                   in.close();
                }finally{
           
                   if(fos!=null)
                       fos.close();
                }
            }						
			//fileit.write(uploadedFile);
            UpZipUtil zipUtil = new UpZipUtil();
            zipUtil.setZipfile(uploadedFile);
            zipUtil.setDirectory(directory);
            zipUtil.start();
            /*
            ZipFile zip = new ZipFile(uploadedFile);  
            for(Enumeration entries = zip.entries();entries.hasMoreElements();){  
                ZipEntry entry = (ZipEntry)entries.nextElement();  
                String zipEntryName = entry.getName();  
                InputStream in1 = zip.getInputStream(entry); 
                File upFile = new File(savePath+book_id+"_" + part_id);
        		if (!dirFile.exists()) {
        			dirFile.mkdirs();
        		}
                String outPath = (savePath+book_id+"_" + part_id+"/"+zipEntryName).replaceAll("\\*", "/");;  
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
                */ 
			
		} catch (Exception e) {
			writer.println(objectMapper.writeValueAsString(getError("上传文件失败。")));
		}
		msg.put("error", 0);
		
		saveUrl += newFileName;
		
		HashMap<String,String> map = new HashMap<String, String>();
		fileSize = fileSize / 1024 + 1 ;
//		if(dirName.equals("image")){
//			msg.put("url", saveUrl);
//			
//		}else if (dirName.equals("file")) {					
//			msg.put("url", saveUrl+","+savePath+","+fileExt+"," + fileSize +","+newFileName) ;						
//		}
		msg.put("url", saveUrl);
		msg.put("path", savePath+book_id+"_" + part_id+"/");
		msg.put("size", fileSize);
		msg.put("fileExt",fileExt);	
		savePath +=newFileName;
		String filename= savePath;
		msg.put("fileName", filename);
		
		writer.println(objectMapper.writeValueAsString(msg));
		return;
		
	}
	
	

	private Map<String, Object> getError(String message) {
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("error", 1);
		msg.put("message", message);
		return msg;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/book/file/list", method = RequestMethod.GET)
	public void fileManager(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = request.getSession().getServletContext();
		ServletOutputStream out = response.getOutputStream();
		// 根目录路径，可以指定绝对路径，比如 /var/www/attached/
		String rootPath = application.getRealPath("/") + "upload/";
		// 根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
		String rootUrl = request.getContextPath() + "/upload/";
		// 图片扩展名
		String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };

		String dirName = request.getParameter("dir");
		if (dirName != null) {
			if (!Arrays.<String> asList(
					new String[] { "image", "flash", "media", "file" })
					.contains(dirName)) {
				out.println("Invalid Directory name.");
				return;
			}
			rootPath += dirName + "/";
			rootUrl += dirName + "/";
			File saveDirFile = new File(rootPath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
		}
		// 根据path参数，设置各路径和URL
		String path = request.getParameter("path") != null ? request
				.getParameter("path") : "";
		String currentPath = rootPath + path;
		String currentUrl = rootUrl + path;
		String currentDirPath = path;
		String moveupDirPath = "";
		if (!"".equals(path)) {
			String str = currentDirPath.substring(0,
					currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0,
					str.lastIndexOf("/") + 1) : "";
		}

		// 排序形式，name or size or type
		String order = request.getParameter("order") != null ? request
				.getParameter("order").toLowerCase() : "name";

		// 不允许使用..移动到上一级目录
		if (path.indexOf("..") >= 0) {
			out.println("Access is not allowed.");
			return;
		}
		// 最后一个字符不是/
		if (!"".equals(path) && !path.endsWith("/")) {
			out.println("Parameter is not valid.");
			return;
		}
		// 目录不存在或不是目录
		File currentPathFile = new File(currentPath);
		if (!currentPathFile.isDirectory()) {
			out.println("Directory does not exist.");
			return;
		}
		// 遍历目录取的文件信息
		List<Hashtable> fileList = new ArrayList<Hashtable>();
		if (currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if (file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if (file.isFile()) {
					String fileExt = fileName.substring(
							fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String> asList(fileTypes)
							.contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime",
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file
								.lastModified()));
				fileList.add(hash);
			}
		}

		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("moveup_dir_path", moveupDirPath);
		msg.put("current_dir_path", currentDirPath);
		msg.put("current_url", currentUrl);
		msg.put("total_count", fileList.size());
		msg.put("file_list", fileList);
	
				
		response.setContentType("application/json; charset=UTF-8");
		String msgStr = objectMapper.writeValueAsString(msg);
		out.println(msgStr);
	}

	@SuppressWarnings("rawtypes")
	class NameComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filename"))
						.compareTo((String) hashB.get("filename"));
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	class SizeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long) hashA.get("filesize")) > ((Long) hashB
						.get("filesize"))) {
					return 1;
				} else if (((Long) hashA.get("filesize")) < ((Long) hashB
						.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	class TypeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filetype"))
						.compareTo((String) hashB.get("filetype"));
			}
		}
	}
}
