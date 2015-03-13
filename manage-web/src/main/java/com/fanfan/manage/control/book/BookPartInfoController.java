package com.fanfan.manage.control.book;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fanfan.manage.common.Constans;
import com.fanfan.manage.inteceptor.AdminInfoToken;
import com.fanfan.manage.inteceptor.AnnatationToken;
import com.fanfan.manage.modle.admin.AdminLoginInfo;
import com.fanfan.manage.modle.base.PlatForm;
import com.fanfan.manage.modle.base.vote.VotePlan;
import com.fanfan.manage.modle.book.BookPart;
import com.fanfan.manage.modle.book.BookPartInfo;
import com.fanfan.manage.modle.book.BookPartInfoPK;
import com.fanfan.manage.service.base.PlatFormServiceImpl;
import com.fanfan.manage.service.base.vote.VotePlanServiceImpl;
import com.fanfan.manage.service.book.BookPartInfoServiceImpl;
import com.fanfan.manage.service.book.BookPartServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.vector.AdminOperateVector;

@Controller
@RequestMapping(value="book/part/info")
public class BookPartInfoController {
		
	@Autowired
	private BookPartInfoServiceImpl bookPartInfoServiceImpl;
	
	@Autowired
	private BookPartServiceImpl bookPartServiceImpl;
	
	@Autowired
	private PlatFormServiceImpl platFormServiceImpl;
	
	@Autowired
	private VotePlanServiceImpl votePlanServiceImpl;
	
	@Autowired
	private AdminOperateVector adminOperateVector;
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String create(HttpServletRequest res,Model model) throws Exception{	
		
		String partId =  res.getParameter("partId");
		String platId =  res.getParameter("platId");
		res.getSession().setAttribute("part_id", partId);
		BookPart part = bookPartServiceImpl.get(Integer.parseInt(partId));
		List<PlatForm> list = platFormServiceImpl.list();
		model.addAttribute("part", part);
		model.addAttribute("platforms", list);
		
		return "account/book/part/info/create";
		
	}
			
	@RequestMapping(value="create",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String created(BookPartInfo bookPartInfo,HttpServletRequest res) throws Exception{
		String partId = res.getParameter("partId");
		
		String platStr = res.getParameter("platId");
		int platId = 0;
		if(platStr!=null&&!platStr.equals("")){
			platId = Integer.parseInt(platStr);
		}
		
		BookPart bookPart = bookPartServiceImpl.get(Integer.parseInt(partId));
		PlatForm platForm = platFormServiceImpl.get(platId);
		BookPartInfoPK pk= new BookPartInfoPK();
		
		if(bookPart!=null && platForm !=null){
			
			pk.setBookPart(bookPart);
			pk.setPlatForm(platForm);
			bookPartInfo.setPk(pk);
			bookPartInfoServiceImpl.put(bookPartInfo,Integer.parseInt(partId),platId);
			AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
			Map adminMap =new HashMap<String,String>();
			adminMap.put("ipAddr", res.getRemoteAddr());
			adminMap.put("adminId", info.getAdmin_id());
			adminMap.put("logInfo", "create");
			adminOperateVector.operate(bookPartInfo, adminMap);	
		}
		
		res.setAttribute("partId", partId);
		return "redirect:/book/part/info/list?partId="+partId;
		
	}
	
	@RequestMapping(value="list")
	@AdminInfoToken(secondToken=true)
	public String list(HttpServletRequest res,Model model) throws Exception{
		String s = "_\\d+";
		Pattern  pattern=Pattern.compile(s);				
		String complie =res.getRequestURI();		
		Matcher mt=pattern.matcher(complie); 
		int pageIndex ;
		if(mt.find()){		
			pageIndex = Integer.parseInt(mt.group().replace("_", ""));
		}else{
			pageIndex = 1;
		}		
		//System.out.println(res.getParameter("partId"));
		int pageSize = Constans.ACCOUNT_DEFAULT_PAGE_SIZE;			
		int partId = Integer.parseInt(res.getParameter("partId"));
		BookPart bookPart = bookPartServiceImpl.get(partId); 
		String url = "/manage/book/part/info/list%s?partId="+partId;		
		List<BookPartInfo> list =null;		
		int start = pageSize*(pageIndex-1);				
		list = bookPartInfoServiceImpl.list(partId,start, pageSize);
		int total = bookPartInfoServiceImpl.count(partId);		
		PageNav<BookPartInfo> context = new PageNav<BookPartInfo>(list,total,pageSize,pageIndex,url);			
		model.addAttribute("context", context);
		model.addAttribute("part", bookPart);
		
		return "account/book/part/info/list";
	}
	
	@RequestMapping(value="view",method=RequestMethod.GET)
	@AnnatationToken(SaveToken=true)
	@AdminInfoToken(secondToken=true)
	public String view(HttpServletRequest res,Model model) throws Exception{
		
		String platId =  res.getParameter("platId");
		String partId =  res.getParameter("partId");
		
		BookPart part= bookPartServiceImpl.get(Integer.parseInt(partId));	
		BookPartInfo bookPartInfo = bookPartInfoServiceImpl.get(Integer.parseInt(partId),Integer.parseInt(platId));
		model.addAttribute("bookPartInfo", bookPartInfo);
		model.addAttribute("part", part);
//		VotePlan plan =  votePlanServiceImpl.get(part.getVotePlanId());
//		model.addAttribute("plan", plan);
		return "account/book/part/info/view";		
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@AnnatationToken(RemoveToken=true)
	@AdminInfoToken(secondToken=true)
	public String updated(HttpServletRequest res,@RequestParam("platId") int platId,@RequestParam("partId") int partId,@RequestParam("fileSize") int fileSize,@RequestParam("hasPoint") int hasPoint
			,@RequestParam("maxPoints") int maxPoints,@RequestParam("hasFee") int hasFee,@RequestParam("fee") double fee,@RequestParam("status") int status) throws Exception{
		
		
		
		
		//String fileSize =  res.getParameter("fileSize");
		String fileName = res.getParameter("fileName");		
		String fileType =  res.getParameter("fileType");
		String imageUrl =  res.getParameter("imageUrl");
		//String hasPoint = res.getParameter("hasPoint");
		//String maxPonits =  res.getParameter("hasPoints");
		//String hasFee =  res.getParameter("hasFee");		
		//String Fee =  res.getParameter("Fee");
		//String status =  res.getParameter("status");
//		BookPartInfo bookPartInfo =  new BookPartInfo();
//		BookPart bookPart =  bookPartServiceImpl.get(partId);
//		PlatForm platForm =  platFormServiceImpl.get(platId);
//		
//		BookPartInfoPK pk = new  BookPartInfoPK();		
//		pk.setBookPart(bookPart);
//		pk.setPlatForm(platForm);
//		
//		bookPartInfo.setPk(pk);	
//		bookPartInfo.setFee(fee);
//		bookPartInfoServiceImpl.update(bookPartInfo);
		BookPartInfo bookPartInfo = bookPartInfoServiceImpl.get(partId,platId);	
		bookPartInfo.setStatus(status);
		bookPartInfo.setFee(fee);
		bookPartInfo.setFileName(fileName);
		bookPartInfo.setFileSize(fileSize);
		bookPartInfo.setFileType(fileType);
		bookPartInfo.setHasPoint(hasPoint);
		bookPartInfo.setImageUrl(imageUrl);
		bookPartInfo.setMaxPoints(maxPoints);
		bookPartInfo.setHasFee(hasFee);
		bookPartInfoServiceImpl.update(bookPartInfo,partId,platId);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "create");
		adminOperateVector.operate(bookPartInfo, adminMap);				
		return  "redirect:/book/part/info/list?partId="+partId;
	}
	
	@RequestMapping(value="delete")
	@AdminInfoToken(secondToken=true)
	public String delete(@RequestParam("partId") int partId,@RequestParam("platId") int platId , HttpServletRequest res ) throws Exception{
		
		BookPartInfo bookPartInfo =  bookPartInfoServiceImpl.get(partId, platId);
		if(bookPartInfo!=null){
			bookPartInfoServiceImpl.delete(bookPartInfo);
			AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
			Map adminMap =new HashMap<String,String>();
			adminMap.put("ipAddr", res.getRemoteAddr());
			adminMap.put("adminId", info.getAdmin_id());
			adminMap.put("logInfo", "create");
			adminOperateVector.operate(bookPartInfo, adminMap);	
		}		
		return "redirect:/book/part/info/list?partId="+partId;		
	}
	
	@RequestMapping(value="upused")
	@AdminInfoToken(secondToken=true)
	public String upused(@RequestParam("partId") int partId,@RequestParam("platId") int platId ,@RequestParam("status") int status , HttpServletRequest res) throws Exception{
		
		BookPartInfo bookPartInfo =  bookPartInfoServiceImpl.get(partId, platId);
		if(bookPartInfo!=null){
			bookPartInfo.setStatus(status);
			bookPartInfoServiceImpl.update(bookPartInfo);
		}		
		
		return "redirect:/book/part/info/list?partId="+partId;	
		
	}
	

}
