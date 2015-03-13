package com.fanfan.manage.control.book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fanfan.manage.api.book.service.BookPartServiceItl;
import com.fanfan.manage.common.Constants;
import com.fanfan.manage.inteceptor.AdminInfoToken;
import com.fanfan.manage.inteceptor.AnnatationToken;
import com.fanfan.manage.modle.admin.AdminLoginInfo;
import com.fanfan.manage.modle.base.vote.VotePlan;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.book.BookInfo;
import com.fanfan.manage.modle.book.BookPart;
import com.fanfan.manage.service.base.PlatFormServiceImpl;
import com.fanfan.manage.service.base.vote.VotePlanServiceImpl;
import com.fanfan.manage.service.book.BookInfoServiceImpl;
import com.fanfan.manage.service.book.BookPartServiceImpl;
import com.fanfan.manage.service.book.BookPicServiceImpl;
import com.fanfan.manage.service.book.BookServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.vector.AdminOperateVector;


@Controller
@RequestMapping(value="book/part")
public class BookPartController {
	
	@Autowired
	private BookPartServiceImpl bookPartServiceImpl;
	
	@Autowired
	private BookServiceImpl bookServiceImpl;
	
	@Autowired
	private PlatFormServiceImpl platFormServiceImpl;
	
	@Autowired
	private VotePlanServiceImpl votePlanServiceImpl;
	
	@Autowired
	private BookInfoServiceImpl bookInfoServiceImpl;
	
	@Autowired
	private AdminOperateVector adminOperateVector ;
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String create(HttpServletRequest res,Model model) throws Exception{
		
		String bookId = res.getParameter("bookId");
		res.setAttribute("bookId", bookId);	
		model.addAttribute("book", bookServiceImpl.get(Integer.parseInt(bookId)));
		model.addAttribute("partIndex", bookPartServiceImpl.partIndex(Integer.parseInt(bookId))) ;
		return "account/book/part/create";
		
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String created(BookPart bookPart,HttpServletRequest res) throws Exception{
		
		String bookId = res.getParameter("bookId");			
		Book book = bookServiceImpl.get(Integer.parseInt(bookId));
		bookPart.setBook(book);
		bookPartServiceImpl.put(bookPart);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "create");
		adminOperateVector.operate(bookPart, adminMap);
		Map map = bookPartServiceImpl.lastPart(Integer.parseInt(bookId));
		bookInfoServiceImpl.megerLastPart(book.getId(), map);
		return "redirect:/book/part/view?id="+bookPart.getId();
		
	}
	
	@RequestMapping(value={"list","list/p_{pageIndex}"})
	@AdminInfoToken(secondToken=true)
	public String list(Model model,HttpServletRequest res) throws Exception{
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
		int pageSize = Constants.ACCOUNT_DEFAULT_PAGE_SIZE;
		int bookId = Integer.parseInt(res.getParameter("bookId"));
		String url = "/manage/book/part/list%s?bookId="+bookId;		
		List<BookPart> list =null;	
		Book book = bookServiceImpl.get(bookId);
		int start = pageSize*(pageIndex-1);				
		list = bookPartServiceImpl.list(bookId,start, pageSize);			
		int total = bookPartServiceImpl.count(bookId);		
		PageNav<BookPart> context = new PageNav<BookPart>(list,total,pageSize,pageIndex,url);			
		model.addAttribute("context", context);
		model.addAttribute("bookId", bookId);
		model.addAttribute("book", book);
		return "account/book/part/list";
	}
	
	@RequestMapping(value="view")
	@AdminInfoToken(secondToken=true)
	public String view(HttpServletRequest res,Model model) throws Exception{
		String  id  =res.getParameter("id");
		BookPart part =  bookPartServiceImpl.get(Integer.parseInt(id));
		List<VotePlan> votePlans = votePlanServiceImpl.list("part",part.getBook().getId(),part.getId());
		model.addAttribute("part", part);
		model.addAttribute("votePlans", votePlans);
		return "account/book/part/view";
	}
	
	@RequestMapping(value="changeplan")
	@AdminInfoToken(secondToken=true)
	public String changePlan(HttpServletRequest res) throws Exception{
		
		String id = res.getParameter("id");
		String votePlanId =  res.getParameter("votePlanId");
		BookPart part = bookPartServiceImpl.get(Integer.parseInt(id));
		part.setVotePlanId(Integer.parseInt(votePlanId));
		bookPartServiceImpl.update(part);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(part, adminMap);
		return "redirect:/book/part/view?id="+id;
	}
	
	@RequestMapping(value="update",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	@AnnatationToken(SaveToken=true)
	public String update(HttpServletRequest res,Model model) throws Exception{
		
		String id = res.getParameter("id");
		BookPart part = bookPartServiceImpl.get(Integer.parseInt(id));
		model.addAttribute("part", part);		
		return "account/book/part/update";
		
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	@AnnatationToken(RemoveToken=true)
	public String updated(BookPart bookPart,HttpServletRequest res) throws Exception{
		
		String bookId =  res.getParameter("bookId");
		Book book = bookServiceImpl.get(Integer.parseInt(bookId));		
		bookPart.setBook(book);
		bookPartServiceImpl.update(bookPart);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(bookPart, adminMap);
		Map map = bookPartServiceImpl.lastPart(Integer.parseInt(bookId));
		bookInfoServiceImpl.megerLastPart(book.getId(), map);
		
		return "redirect:/book/part/view?id="+bookPart.getId();
		
	}
	
	@RequestMapping(value="upused")
	@AdminInfoToken(secondToken=true)
	public String upused(HttpServletRequest res) throws Exception{
		
		String id =  res.getParameter("id");
		String status =  res.getParameter("status");
		BookPart bookPart =  bookPartServiceImpl.get(Integer.parseInt(id));
		if(bookPart!=null){
			bookPart.setStatus(Integer.parseInt(status));
			bookPartServiceImpl.update(bookPart);			
			Map map = bookPartServiceImpl.lastPart(bookPart.getBook().getId());
			bookInfoServiceImpl.megerLastPart(bookPart.getBook().getId(), map);
		}
		return "redirect:/book/part/view?id="+id;
		
	}
	
	@RequestMapping(value="delete")
	@AdminInfoToken(secondToken=true)
	public String delete(HttpServletRequest res) throws Exception{
		
		String id =  res.getParameter("id");
		BookPart bookPart =  bookPartServiceImpl.get(Integer.parseInt(id));
		bookPartServiceImpl.delete(bookPart);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "delete");
		adminOperateVector.operate(bookPart, adminMap);
		return "redirect:/book/part/list?bookId="+bookPart.getBook().getId();
		
	}
	
}
