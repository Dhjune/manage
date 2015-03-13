package com.fanfan.manage.control.copyright;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fanfan.manage.common.Constants;
import com.fanfan.manage.control.common.Helps;
import com.fanfan.manage.inteceptor.AdminInfoToken;
import com.fanfan.manage.inteceptor.AnnatationToken;
import com.fanfan.manage.modle.admin.AdminLoginInfo;
import com.fanfan.manage.modle.copyright.CrAuthor;
import com.fanfan.manage.modle.copyright.CrBook;
import com.fanfan.manage.modle.copyright.CrBookFile;
import com.fanfan.manage.modle.copyright.CrInfo;
import com.fanfan.manage.modle.copyright.CrLog;
import com.fanfan.manage.modle.copyright.CrPay;
import com.fanfan.manage.modle.copyright.CrVList;
import com.fanfan.manage.sdk.wordfactory.WordFactoryMain;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.common.PageNavJson;
import com.fanfan.manage.service.copyright.CrAuthorServiceImpl;
import com.fanfan.manage.service.copyright.CrBookServiceImpl;
import com.fanfan.manage.service.copyright.CrBookfileServiceImpl;
import com.fanfan.manage.service.copyright.CrInfoServiceImpl;
import com.fanfan.manage.service.copyright.CrLogServiceImpl;
import com.fanfan.manage.service.copyright.CrPayServiceImpl;

@Controller
@RequestMapping(value="copyright/")
public class CopyrightController {
	
	@Autowired
	private CrBookServiceImpl crBookServiceImpl;
	
	@Autowired
	private CrBookfileServiceImpl crBookfileServiceImpl;
	
	@Autowired
	private CrInfoServiceImpl crInfoServiecImpl;
	
	@Autowired
	private CrAuthorServiceImpl crAuthorServiceImpl;
	
	@Autowired
	private CrPayServiceImpl crPayServiceImpl;
	
	@Autowired
	private CrLogServiceImpl crLogServiceImpl ;
	
	@RequestMapping(value="index")
	public String index(HttpServletRequest res){
		
		res.setAttribute("copyright", true);
		
		return "account/copyright/index";
	}
	
	@RequestMapping(value={"list","list/p_{pageIndex}"},method=RequestMethod.GET)
	@AdminInfoToken(cprightSomeLookToken=true)
	public String list(HttpServletRequest res ,Model model){
		String s = "_\\d+";
		Pattern  pattern=Pattern.compile(s); 
		String complie =res.getRequestURI();
		Matcher mt=pattern.matcher(complie); 
		int pageIndex;
		
		if(mt.find()){	
			
			pageIndex = Integer.parseInt(mt.group().replace("_", ""));
			
		}else{	
			
			pageIndex = 1;	
			
		}		
		
		int pageSize = Constants.COPYRIGHT_DEFAULT_PAGE_SIZE;					
		List<CrVList> list =null;	
		String staStr  = res.getParameter("status");
		int status = 1;
		
		if(staStr !=null && !staStr.equals("")){
			status  = Integer.parseInt(staStr);
		}
		
		String url = "/manage/copyright/list%s";	
		int start = pageSize*(pageIndex-1);				
		list = crBookServiceImpl.list(start, pageSize);			
		int total = crBookServiceImpl.count();		
		PageNav<CrVList> context = new PageNav<CrVList>(list,total,pageSize,pageIndex,url);			
		model.addAttribute("context", context);						
		return "account/copyright/list";
	}
	
	@RequestMapping(value="pageNavJson",produces = "application/json")
	@ResponseBody
	@AdminInfoToken(cprightSomeLookToken=true)
	public PageNavJson pageNavJson(@RequestBody Map map) throws Exception{
		
		List<CrVList> list = null;		
		
		int pageSize = Constants.COPYRIGHT_DEFAULT_PAGE_SIZE;	
		int pageIndex ;	
//		System.out.println(map.get("list"));
//		List mlist = (List) map.get("list");
//		for(Object o : mlist){
//			System.out.println(o.toString());
//		}
		
//		for (Object key : map.keySet()) {			
//			System.out.println(key);			
//		}
		if(map.get("pageIndex")!=null){
			pageIndex = Integer.parseInt(map.get("pageIndex").toString());
		}else{
			pageIndex = 1;
		}
		
		int start = pageSize*(pageIndex-1);	
		list =  crBookServiceImpl.list(start,pageSize,map);	
		int total = crBookServiceImpl.count(map);	
		PageNavJson<CrVList> context = new PageNavJson<CrVList>(list,total,pageSize,pageIndex);
		return context;		
	}
	
	
	
	@RequestMapping(value="view")
	@AdminInfoToken(cprightSomeLookToken=true)
	public String view(Model model,@RequestParam("bookId") int bookId){
		CrBook crBook = null;
		CrAuthor crAuthor = null;
		crBook =crBookServiceImpl.get(bookId);
		CrBookFile crBookfile = crBookfileServiceImpl.getByBookId(bookId);
		crAuthor = crAuthorServiceImpl.get(crBook.getAuthorId());
		List<CrPay> crPays = crPayServiceImpl.getListByBookId(bookId);
		List<CrInfo> crInfos = crInfoServiecImpl.getListByBookId(bookId);
		List<CrLog> crLogs = crLogServiceImpl.getListByBookId(1,9,bookId);
		model.addAttribute("crBook",crBook);
		model.addAttribute("crAuthor", crAuthor);
		model.addAttribute("crInfos",crInfos);
		model.addAttribute("crPays",crPays);
		model.addAttribute("crBookfile",crBookfile);
		model.addAttribute("crLogs", crLogs);
		return  "account/copyright/view";
		
	}
	
	@RequestMapping(value="book/create",method=RequestMethod.GET)
	@AdminInfoToken(cprightSomeToken=true)
	@AnnatationToken(SaveToken=true)
	public String book_create(){
		
		return "account/copyright/book/create";
		
	}
	
	@RequestMapping(value="book/create",method=RequestMethod.POST)
	@AdminInfoToken(cprightSomeToken=true)
	@AnnatationToken(RemoveToken=true)
	public String book_created(CrBook crBook,HttpSession session){
		crBookServiceImpl.save(crBook);
		if(crBook.getId()>0){
			AdminLoginInfo admin = (AdminLoginInfo) session.getAttribute("admin");
			CrLog crLog=new CrLog();
			crLog.setAdminName(admin.getLogin_name());
			crLog.setBehaviour("创建作品");
			crLog.setLogInfo("创建了《"+crBook.getBookName()+"》这部作品的作品信息!");
			crLog.setBookId(crBook.getId());
			crLogServiceImpl.save(crLog);
			return "redirect:/copyright/view?bookId="+crBook.getId();
		}else{
			return "redirect:/copyright/list";
		}
		
	}
	
	@RequestMapping(value="book/update")
	@ResponseBody
	@AdminInfoToken(cprightSomeToken=true)
	public Map book_save(CrBook crBook,HttpServletRequest res){
		
		crBookServiceImpl.update(crBook);
		Map map = new HashMap();
		map.put("message", "success");
		return map;
		
	}
	
	@RequestMapping(value="book/delete")
	@AdminInfoToken(cprightAllToken=true)
	public String book_delete(HttpServletRequest res){
		String pageStr = res.getParameter("pageIndex");
		String bookId =  res.getParameter("bookId");
		if(bookId !=null && !bookId.equals("")){
			CrBook crBook = crBookServiceImpl.get(Integer.parseInt(bookId));
			crBookServiceImpl.delete(crBook);
			
		}
		
		int pageIndex = 1;
		if(pageStr!=null && !pageStr.equals("")){
			pageIndex = Integer.parseInt(pageStr);
		}
		return "redirect:/copyright/list/p_"+pageIndex;
	}
	
	
	@RequestMapping(value="author/saveOrupdate",method=RequestMethod.POST)
	@ResponseBody
	@AdminInfoToken(cprightAllToken=true)
	public Map author_save(HttpServletRequest res,CrAuthor crAuthor,HttpSession session){
//		Map maps = res.getParameterMap();		 
//		for (Object key : maps.keySet()) {			
//			System.out.println(key);			
//		}
		String bookIdStr= res.getParameter("bookId");
		CrBook crBook = null;
		Map map = new HashMap();
		crBook =crBookServiceImpl.get(Integer.parseInt(bookIdStr));
		if(crAuthor!=null && !crAuthor.getName().equals("")){
			crAuthorServiceImpl.updateAuthorandBook(crAuthor,Integer.parseInt(bookIdStr));
			AdminLoginInfo admin = (AdminLoginInfo) session.getAttribute("admin");
			CrLog crLog=new CrLog();
			crLog.setAdminName(admin.getLogin_name());
			crLog.setBehaviour("修改");
			crLog.setLogInfo("修改了作者信息!");
			crLog.setBookId(crBook.getId());
			crLogServiceImpl.save(crLog);
			map.put("message", "success");
			map.put("authorId", crAuthor.getId());
		}else{
			map.put("message", "作者姓名不能为空！");
			map.put("authorId", 0);
		}
			
		
		return map;
		
	}
	
	@RequestMapping(value="author/search")
	@ResponseBody
	public CrAuthor author_search(HttpServletRequest res){
		String authorName = res.getParameter("authorName");
		
		CrAuthor crAuthor = null;
		crAuthor =crAuthorServiceImpl.searchByName(authorName);
		return crAuthor;
	}
	
	@RequestMapping(value="info/create",method=RequestMethod.GET)
	@AdminInfoToken(cprightAllToken=true)
	@AnnatationToken(SaveToken=true)
	public String info_create(HttpServletRequest res,Model model){
		model.addAttribute("bookId", res.getParameter("bookId"));
		String bookId =  res.getParameter("bookId");	
		model.addAttribute("crBook", crBookServiceImpl.get(Integer.parseInt(bookId)));
		return "account/copyright/info/create";
	}
	
	@RequestMapping(value="info/create",method=RequestMethod.POST)
	@AnnatationToken(RemoveToken=true)
	@AdminInfoToken(cprightSomeToken=true)
	public String info_created(CrInfo crInfo,HttpSession session){
		
		crInfoServiecImpl.save(crInfo);	
		CrBook crBook = null;
		crBook =crBookServiceImpl.get(crInfo.getBookId());
		AdminLoginInfo admin = (AdminLoginInfo) session.getAttribute("admin");
		CrLog crLog=new CrLog();
		crLog.setAdminName(admin.getLogin_name());
		crLog.setBehaviour("创建");
		crLog.setLogInfo("创建了版权信息!");
		crLog.setBookId(crBook.getId());
		crLogServiceImpl.save(crLog);
		
		return "redirect:/copyright/view?bookId="+crInfo.getBookId();
	}
	
	@RequestMapping(value="info/view",method=RequestMethod.GET)
	@AnnatationToken(SaveToken=true)
	@AdminInfoToken(cprightAllToken=true)
	public String info_view(HttpServletRequest res,Model model){
		String id = res.getParameter("id");
		CrInfo crInfo = crInfoServiecImpl.get(Integer.parseInt(id));
		model.addAttribute("crInfo", crInfo);
		model.addAttribute("crBook", crBookServiceImpl.get(crInfo.getBookId()));
		return "account/copyright/info/view";
	}
	
	@RequestMapping(value="info/update",method=RequestMethod.POST)
	@AdminInfoToken(cprightSomeToken=true)
	@AnnatationToken(RemoveToken=true)
	public String info_updated(CrInfo crInfo,HttpSession session){
		crInfoServiecImpl.update(crInfo);
		CrBook crBook = null;
		crBook =crBookServiceImpl.get(crInfo.getBookId());
		AdminLoginInfo admin = (AdminLoginInfo) session.getAttribute("admin");
		CrLog crLog=new CrLog();
		crLog.setAdminName(admin.getLogin_name());
		crLog.setBehaviour("修改");
		crLog.setLogInfo("修改了版权信息!");
		crLog.setBookId(crBook.getId());
		crLogServiceImpl.save(crLog);
		return "redirect:/copyright/view?bookId="+crInfo.getBookId();
	}
	
	@RequestMapping("info/delete")
	@AdminInfoToken(cprightAllToken=true)
	public String info_delete(HttpServletRequest res){
		
		String id = res.getParameter("id");
		System.out.println("the id  is "+  id);
		String  bookId = res.getParameter("bookId");
		if(id!=null && !id.equals("")){
			
			CrInfo crInfo = crInfoServiecImpl.get(Integer.parseInt(id));
			System.out.println(crInfo);
			crInfoServiecImpl.delete(crInfo);
			
		}
				
		return "redirect:/copyright/view?bookId="+bookId;
		
	}
	
	@RequestMapping(value="pay/create",method=RequestMethod.GET)
	@AdminInfoToken(cprightAllToken=true)
	@AnnatationToken(SaveToken=true)
	public String pay_create(HttpServletRequest res,Model model){
		
		String bookId =  res.getParameter("bookId");	
		model.addAttribute("crBook", crBookServiceImpl.get(Integer.parseInt(bookId)));
		model.addAttribute("bookId", res.getParameter("bookId"));
		
		return "account/copyright/pay/create";
	}
	
	@RequestMapping(value="pay/create",method=RequestMethod.POST)
	@AdminInfoToken(cprightAllToken=true)
	@AnnatationToken(RemoveToken=true)
	public String pay_created(CrPay crPay,HttpSession session){
		
		crPayServiceImpl.save(crPay);
		CrBook crBook = null;
		crBook =crBookServiceImpl.get(crPay.getBookId());
		AdminLoginInfo admin = (AdminLoginInfo) session.getAttribute("admin");
		CrLog crLog=new CrLog();
		crLog.setAdminName(admin.getLogin_name());
		crLog.setBehaviour("创建");
		crLog.setLogInfo("创建了支付信息!");
		crLog.setBookId(crBook.getId());
		crLogServiceImpl.save(crLog);
		return "redirect:/copyright/view?bookId="+crPay.getBookId();
		
	}
	
	@RequestMapping(value="pay/view",method=RequestMethod.GET)
	@AdminInfoToken(cprightAllToken=true)
	@AnnatationToken(SaveToken=true)
	public String pay_view(HttpServletRequest res,Model model){
		
		CrPay crPay = null;	
		String id =  res.getParameter("id");		
		crPay = crPayServiceImpl.get(Integer.parseInt(id));
		model.addAttribute("crPay", crPay);
		model.addAttribute("crBook", crBookServiceImpl.get(crPay.getBookId()));
		return "account/copyright/pay/view";
	}
	
	@RequestMapping(value="pay/update",method=RequestMethod.POST)
	@AnnatationToken(RemoveToken=true)
	@AdminInfoToken(cprightAllToken=true)
	public String pay_updated(CrPay crPay,HttpSession session){
		
		crPayServiceImpl.update(crPay);	
		
		CrBook crBook = null;
		crBook =crBookServiceImpl.get(crPay.getBookId());
		AdminLoginInfo admin = (AdminLoginInfo) session.getAttribute("admin");
		CrLog crLog=new CrLog();
		crLog.setAdminName(admin.getLogin_name());
		crLog.setBehaviour("修改");
		crLog.setLogInfo("修改了支付信息!");
		crLog.setBookId(crBook.getId());
		crLogServiceImpl.save(crLog);
		return "redirect:/copyright/view?bookId="+crPay.getBookId();
		
	}
	
	@RequestMapping("pay/delete")
	@AdminInfoToken(cprightAllToken=true)
	public String pay_delete(HttpServletRequest res){
		String id = res.getParameter("id");
		String  bookId = res.getParameter("bookId");
		if(id!=null && !id.equals("")){
			CrPay crPay = crPayServiceImpl.get(Integer.parseInt(id));
			crPayServiceImpl.delete(crPay);
		}
		
		
		
		return "redirect:/copyright/view?bookId="+bookId;
	}
	
	@RequestMapping(value="bookfile/save",method=RequestMethod.POST)
	@AdminInfoToken(cprightAllToken=true)
	public String bookfile_save(CrBookFile bookfile){
		crBookfileServiceImpl.save(bookfile);		
		return "redirect:/copyright/view?bookId="+bookfile.getBookId();
		
	}
	
	@RequestMapping(value="bookfile/down")
	@AdminInfoToken(secondToken=true)
	public void bookfile_down(HttpServletRequest res,HttpServletResponse response) throws Exception{
		
		String id =  res.getParameter("id");
		CrBookFile crBookfile=crBookfileServiceImpl.get(Integer.parseInt(id));
		File file = new File(crBookfile.getSavePath());
        // 取得文件名。
        String filename = file.getName();
        // 取得文件的后缀名。
        String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
        
     // 以流的形式下载文件。
        InputStream fis = new BufferedInputStream(new FileInputStream(crBookfile.getSavePath()));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        // 清空response
        response.reset();
        // 设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
        response.addHeader("Content-Length", "" + file.length());
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
	}
	
	@RequestMapping(value="down/word")
	public void view_word(HttpServletRequest res,HttpServletResponse response){
		
		String type = res.getParameter("type");
		if(type!=null && !type.equals("")){
			String header = "form-data;name=\"june\";filename="+Helps.getRandomString(12)+".doc";
			Map map = init_wordDataMap(type, res);
			
			response.setContentType("application/octet-stream");		
			response.addHeader("Content-Disposition", header);
	
			       ServletOutputStream sos;
				try {
					WordFactoryMain wMain =new WordFactoryMain();
					
					sos = response.getOutputStream();
					
					wMain.createWord(type, map, sos);
					
					//sos.write("hello".getBytes());//动态生成下载的内容
				    sos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	
	
	public Map init_wordDataMap(String type,HttpServletRequest res){
		Map dataMap = new HashMap();
		if(type.equals("list")){
			
			List<CrVList> list = null; 
			String pageIndex =  res.getParameter("pageIndex");			
			int start = Constants.COPYRIGHT_DEFAULT_PAGE_SIZE*(Integer.parseInt(pageIndex)-1);			
			list = crBookServiceImpl.list(start, Constants.COPYRIGHT_DEFAULT_PAGE_SIZE);
			dataMap.put("books", list);
			
		}else if(type.equals("view")){
			CrBook crBook = null;
			CrAuthor crAuthor = null;
			String bookId = res.getParameter("bookId");
			crBook = crBookServiceImpl.get(Integer.parseInt(bookId));
			List<CrInfo> crInfos = crInfoServiecImpl.getListByBookId(Integer.parseInt(bookId));
			crAuthor =  crAuthorServiceImpl.get(crBook.getAuthorId());
			try {
				
				crBook.setCoverImageOne(getImageStr(crBook.getCoverImageOne()));
				crAuthor.setCardImage(getImageStr(crAuthor.getCardImage()));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			dataMap.put("crBook", crBook);
			dataMap.put("crAuthor", crAuthor);
			dataMap.put("crInfos", crInfos);
			
		}						
		return dataMap;		
	}
	
	public String getImageStr(String imageUrl) throws Exception{
		URL url = new URL(imageUrl);
    	InputStream is = url.openStream();
    	//is.available();
        InputStream in = null;
        byte[] buff = null;
        try {
           // buff = new byte[400000];
        	buff = new byte[is.available()];
            is.read(buff);           
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }        
        Base64 encoder = new Base64();
        return encoder.encodeToString(buff);
     
	}
	
	
}
