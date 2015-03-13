package com.fanfan.manage.control.book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fanfan.manage.api.book.service.BookInfoServiceItl;
import com.fanfan.manage.inteceptor.AdminInfoToken;
import com.fanfan.manage.inteceptor.AnnatationToken;
import com.fanfan.manage.modle.admin.AdminLoginInfo;
import com.fanfan.manage.modle.base.PlatForm;
import com.fanfan.manage.modle.base.vote.VotePlan;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.book.BookInfo;
import com.fanfan.manage.modle.book.BookInfoPK;
import com.fanfan.manage.service.base.PlatFormServiceImpl;
import com.fanfan.manage.service.base.vote.VotePlanServiceImpl;
import com.fanfan.manage.service.book.BookInfoServiceImpl;
import com.fanfan.manage.service.book.BookServiceImpl;
import com.fanfan.manage.service.vector.AdminOperateVector;

@Controller
@RequestMapping(value="book/info")
public class BookInfoController {
	
	@Autowired
	private BookInfoServiceImpl bookInfoServiceImpl;
	
	@Autowired
	private BookServiceImpl bookServiceImpl;
	
	@Autowired
	private PlatFormServiceImpl platFormServiceImpl;
	
	@Autowired
	private VotePlanServiceImpl votePlanServiceImpl;
	
	@Autowired
	private AdminOperateVector adminOperateVector;
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String  create(HttpServletRequest res, Model model)  throws Exception{
		
		String bookId = res.getParameter("bookId");
		
		List<PlatForm> plats = platFormServiceImpl.list();
		model.addAttribute("bookId", bookId);
		model.addAttribute("book", bookServiceImpl.get(Integer.parseInt(bookId)));
		model.addAttribute("plats", plats);				
		return "account/book/info/create";	
		
	}
	@RequestMapping(value="create",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String created(BookInfo bookInfo,HttpServletRequest res) throws Exception{
		
		String bookId = res.getParameter("bookId");
		String platId = res.getParameter("platId");
		Book book = bookServiceImpl.get(Integer.parseInt(bookId));
		PlatForm platForm  = platFormServiceImpl.get(Integer.parseInt(platId));
		BookInfoPK pk = new BookInfoPK();
		pk.setBook(book);
		pk.setPlatForm(platForm);
		bookInfo.setPk(pk);
		bookInfo.setBookTitle(book.getBookTitle());		
		bookInfoServiceImpl.put(bookInfo);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "create");
		adminOperateVector.operate(bookInfo, adminMap);
		return "redirect:/book/view?id="+ bookId;		
		
	}
	@RequestMapping(value="view")
	@AdminInfoToken(secondToken=true)
	public String view(HttpServletRequest res,Model model) throws Exception{
		
		String  bookId = res.getParameter("bookId");
		String platId =  res.getParameter("platId");
		BookInfo bookInfo =  bookInfoServiceImpl.get(Integer.parseInt(bookId) , Integer.parseInt(platId),true);
	//	System.out.println("the voteplan id is :"+bookInfo.getVotePlanId());
		VotePlan plan = votePlanServiceImpl.get(bookInfo.getVotePlanId(), true);
		
		List<VotePlan> votePlans = votePlanServiceImpl.list("book",bookInfo.getPk().getBook().getId(),0);		
		model.addAttribute("plan", plan);
		model.addAttribute("votePlans", votePlans);
		model.addAttribute("bookInfo", bookInfo);
		
		return "account/book/info/view";
		
	}
	
	@RequestMapping(value="update",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	@AnnatationToken(SaveToken=true)
	public String update(HttpServletRequest res ,Model model)  throws Exception{
		
		String bookId  =  res.getParameter("bookId");
		String platId =  res.getParameter("platId");
		BookInfo bookInfo = bookInfoServiceImpl.get(Integer.parseInt(bookId), Integer.parseInt(platId),true);
		model.addAttribute("bookInfo", bookInfo);
		model.addAttribute("plats", platFormServiceImpl.list());
		
		return "account/book/info/update";
		
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@AnnatationToken(RemoveToken=true)
	@AdminInfoToken(secondToken=true)
	public String update(HttpServletRequest res,BookInfo copyInfo,Model model) throws Exception{
		
		String bookId  =  res.getParameter("bookId");
		String platId =  res.getParameter("platId");
		BookInfo bookInfo = bookInfoServiceImpl.get(Integer.parseInt(bookId), Integer.parseInt(platId),true);
		bookInfo.setStatus(copyInfo.getStatus());
		bookInfo.setBigImage(copyInfo.getBigImage());
		bookInfo.setHasFee(copyInfo.getHasFee());
		bookInfo.setHasTicket(copyInfo.getHasTicket());
		bookInfo.setHotFlag(copyInfo.getHotFlag());		
		bookInfo.setRecommendFlag(copyInfo.getRecommendFlag());
		bookInfo.setRecommendReason(copyInfo.getRecommendReason());
		bookInfo.setSmallImage(copyInfo.getSmallImage());
		bookInfo.setSecondTitle(copyInfo.getSecondTitle());	
		List<VotePlan> votePlans = votePlanServiceImpl.list("book",bookInfo.getPk().getBook().getId(),0);
		//bookInfoServiceImpl.update(bookInfo);
		bookInfoServiceImpl.update(bookInfo,Integer.parseInt(platId));
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "create");
		adminOperateVector.operate(bookInfo, adminMap);
		model.addAttribute("votePlans", votePlans);  
		model.addAttribute("bookInfo", bookInfo);  		
		return "account/book/info/view";		
	}
	
	@RequestMapping(value="changeplan")
	@AdminInfoToken(secondToken=true)
	public String changePlan(HttpServletRequest res) throws Exception{
		String bookId = res.getParameter("bookId");
		String platId = res.getParameter("platId");
		String votePlanId =  res.getParameter("votePlanId");
		BookInfo bookInfo =  bookInfoServiceImpl.get(Integer.parseInt(bookId), Integer.parseInt(platId), false);
		bookInfo.setVotePlanId(Integer.parseInt(votePlanId));
		bookInfoServiceImpl.update(bookInfo,Integer.parseInt(platId));
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(bookInfo, adminMap);
		return "redirect:/book/info/view?bookId="+bookId+"&platId="+platId;
	}
	
	@RequestMapping(value="upused")
	@AdminInfoToken(secondToken=true)
	public String  upused(HttpServletRequest res) throws Exception{
		
		String bookId = res.getParameter("bookId");
		String platId = res.getParameter("platId");
		String status  = res.getParameter("status");
		BookInfo bookInfo =  bookInfoServiceImpl.get(Integer.parseInt(bookId), Integer.parseInt(platId), false);
		bookInfo.setStatus(Integer.parseInt(status));
		bookInfoServiceImpl.update(bookInfo,Integer.parseInt(platId));
		return "redirect:/book/info/view?bookId="+bookId+"&platId="+platId;
	}
	
}
