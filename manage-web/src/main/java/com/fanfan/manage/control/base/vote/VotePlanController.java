package com.fanfan.manage.control.base.vote;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fanfan.manage.common.Constants;
import com.fanfan.manage.inteceptor.AdminInfoToken;
import com.fanfan.manage.inteceptor.AnnatationToken;
import com.fanfan.manage.modle.admin.AdminLoginInfo;
import com.fanfan.manage.modle.base.PlatForm;
import com.fanfan.manage.modle.base.vote.Vote;
import com.fanfan.manage.modle.base.vote.VotePlan;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.book.BookPart;
import com.fanfan.manage.service.base.PlatFormServiceImpl;
import com.fanfan.manage.service.base.vote.VotePlanServiceImpl;
import com.fanfan.manage.service.base.vote.VoteServiceImpl;
import com.fanfan.manage.service.book.BookPartServiceImpl;
import com.fanfan.manage.service.book.BookServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.vector.AdminOperateVector;

@Controller
@RequestMapping(value="base/vote/plan/")
public class VotePlanController {
	
	@Autowired
	private VotePlanServiceImpl votePlanServiceImpl;
	
	@Autowired
	private VoteServiceImpl  voteServiceImpl;
	
	@Autowired
	private PlatFormServiceImpl platFormServiceImpl;
	
	@Autowired
	private BookPartServiceImpl bookPartServiceImpl;
	
	@Autowired
	private BookServiceImpl bookServiceImpl;
	
	@Autowired
	private AdminOperateVector adminOperateVector;
	
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	@AnnatationToken(SaveToken=true)
	@AdminInfoToken(secondToken=true)
	public String create(HttpServletRequest res ,Model model) throws Exception{
		
		String refObjId =  res.getParameter("refObjId");
		//System.out.println("the objId is "+ refObjId);
		String refSubId = res.getParameter("refSubId");
		String refType = res.getParameter("refType");
		String platId =  res.getParameter("platId");
		model.addAttribute("platforms", platFormServiceImpl.list());
		model.addAttribute("votes", voteServiceImpl.list());
		model.addAttribute("refObjId", refObjId);
		model.addAttribute("refSubId", refSubId);
		model.addAttribute("refType", refType);
		model.addAttribute("platId", platId);
		if(refType.equals("book")){
			model.addAttribute("plat", platFormServiceImpl.get(Integer.parseInt(platId)));
		}
		return "account/base/vote/plan/create";
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	@AnnatationToken(RemoveToken=true)
	@AdminInfoToken(secondToken=true)
	public String created(HttpServletRequest res,VotePlan plan) throws Exception{
		String voteId = res.getParameter("voteId");
		String platId = res.getParameter("platId");
		String openStr =  res.getParameter("openStr");
		String closeStr = res.getParameter("closeStr");		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date openTime = null;
		Date closeTime = null;
		try {
			openTime = sdf.parse(openStr);
			closeTime = sdf.parse(closeStr);
		} catch (ParseException e) {
					
			e.printStackTrace();
		}
		Vote vote = voteServiceImpl.get(Integer.parseInt(voteId));
		PlatForm platForm = platFormServiceImpl.get(Integer.parseInt(platId));
		plan.setVote(vote);
		plan.setPlatForm(platForm);
		plan.setOpenTime(openTime);
		plan.setCloseTime(closeTime);
		votePlanServiceImpl.put(plan,Integer.parseInt(platId));	
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "delete");
		adminOperateVector.operate(plan, adminMap);
		if(plan.getRefType().equals("book")){
			return "redirect:/book/info/view?bookId="+plan.getRefObjId()+"&platId="+plan.getPlatForm().getId();
		}else if(plan.getRefType().equals("part")){
			return "redirect:/book/part/view?id="+plan.getRefSubId();
			
		}
				
		return "redirect:/base/vote/plan/list";
	}
	
	@RequestMapping(value={"list","list/p_{pageIndex}"},method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String list(HttpServletRequest res,Model model)  throws Exception{
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
		String url = "/manage/base/vote/plan/list%s";		
		List<VotePlan> list =null;		
		int start = pageSize*(pageIndex-1);				
		list = votePlanServiceImpl.list(start, pageSize,"");			
		int total = votePlanServiceImpl.count();		
		PageNav<VotePlan> context = new PageNav<VotePlan>(list,total,pageSize,pageIndex,url);			
		model.addAttribute("context", context);	
		return "account/base/vote/plan/list";
		
	}
	@RequestMapping(value="view",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public  String view(HttpServletRequest res,Model model)  throws Exception{	
		
		int id =  Integer.parseInt(res.getParameter("id"));	
		VotePlan plan = votePlanServiceImpl.get(id,true);	
		BookPart bookPart =  null;
		Book book = null;
		if(plan.getRefType().equals("part")){
			bookPart = bookPartServiceImpl.get(plan.getRefSubId());
			model.addAttribute("part", bookPart);
		}else if(plan.getRefType().equals("book")){
			book  = bookServiceImpl.get(id);
			model.addAttribute("book", book);
		}				
		model.addAttribute("plan", plan);		
		return "account/base/vote/plan/view";		
	}
	@RequestMapping(value="update",method=RequestMethod.GET)
	@AnnatationToken(SaveToken=true)
	@AdminInfoToken(secondToken=true)
	public String update(Model model,HttpServletRequest res)  throws Exception{		
		String  id  = res.getParameter("id");					
		VotePlan plan = votePlanServiceImpl.get(Integer.parseInt(id),true);
		if(plan.getRefType().equals("part")){			
			BookPart part =  bookPartServiceImpl.get(plan.getRefSubId());			
			model.addAttribute("part", part);
			model.addAttribute("platForms", platFormServiceImpl.list());			
		}
		Book book =  bookServiceImpl.get(plan.getRefObjId());
		model.addAttribute("book", book);
		model.addAttribute("plan", plan);		
		model.addAttribute("votes", voteServiceImpl.list());		
		return "account/base/vote/plan/update";
		
	}

	@RequestMapping(value={"user/list","user/list/p_{pageIndex}"},method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String userPlanList(HttpServletRequest res,Model model)  throws Exception{
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
		String url = "/manage/base/vote/plan/user/list%s";		
		List<VotePlan> list =null;		
		int start = pageSize*(pageIndex-1);				
		list = votePlanServiceImpl.list(start, pageSize,"user");			
		int total = list.size();		
		PageNav<VotePlan> context = new PageNav<VotePlan>(list,total,pageSize,pageIndex,url);			
		model.addAttribute("context", context);	
		return "account/base/vote/plan/user/list";		
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@AnnatationToken(RemoveToken=true)
	@AdminInfoToken(secondToken=true)
	public String updated(VotePlan plan ,HttpServletRequest res) throws Exception{
		
		String platId =  res.getParameter("platId");
		String voteId =  res.getParameter("voteId");
		String openStr =  res.getParameter("openStr");
		String closeStr = res.getParameter("closeStr");		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date openTime = null;
		Date closeTime = null;
		try {
			openTime = sdf.parse(openStr);
			closeTime = sdf.parse(closeStr);
		} catch (ParseException e) {
					
			e.printStackTrace();
		}
		Vote vote =  voteServiceImpl.get(Integer.parseInt(voteId));
		PlatForm  platForm =  platFormServiceImpl.get(Integer.parseInt(platId));
		plan.setPlatForm(platForm);
		plan.setVote(vote);
		plan.setOpenTime(openTime);
		plan.setCloseTime(closeTime);
		votePlanServiceImpl.update(plan,Integer.parseInt(platId));
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(plan, adminMap);
		if(plan.getRefType().equals("book")){
			return "redirect:/book/info/view?bookId="+plan.getRefObjId()+"&platId="+plan.getPlatForm().getId();
		}else if(plan.getRefType().equals("part")){
			return "redirect:/book/part/view?id="+plan.getRefSubId();
			
		}
		return "redirect:/base/vote/plan/view?id="+plan.getId();
			
	}
	
	@RequestMapping(value="user/create",method=RequestMethod.GET)
	@AnnatationToken(SaveToken=true)
	@AdminInfoToken(secondToken=true)
	public String userPlnacreate(Model model)  throws Exception{
		
		model.addAttribute("platforms", platFormServiceImpl.list());
		model.addAttribute("votes", voteServiceImpl.list());
		
		return "account/base/vote/plan/user/create";
	}
	
	@RequestMapping(value="user/create",method=RequestMethod.POST)
	@AnnatationToken(RemoveToken=true)
	@AdminInfoToken(secondToken=true)
	public String userPlnacreated(HttpServletRequest res,VotePlan plan) throws Exception{
		
		String voteId = res.getParameter("voteId");
		String platId = res.getParameter("platId");
		String openStr =  res.getParameter("openStr");
		String closeStr = res.getParameter("closeStr");		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date openTime = null;
		Date closeTime = null;
		try {
			openTime = sdf.parse(openStr);
			closeTime = sdf.parse(closeStr);
		} catch (ParseException e) {
					
			e.printStackTrace();
		}
		Vote vote = voteServiceImpl.get(Integer.parseInt(voteId));
		PlatForm platForm = platFormServiceImpl.get(Integer.parseInt(platId));
		plan.setVote(vote);
		plan.setPlatForm(platForm);
		plan.setOpenTime(openTime);
		plan.setCloseTime(closeTime);
		votePlanServiceImpl.put(plan,Integer.parseInt(platId));	
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "create");
		adminOperateVector.operate(plan, adminMap);
		return "redirect:/base/vote/plan/user/list";
	}
	
	
	@RequestMapping(value="user/update",method=RequestMethod.GET)
	@AnnatationToken(SaveToken=true)
	@AdminInfoToken(secondToken=true)
	public String userPlanUpdate(Model model,HttpServletRequest res)  throws Exception{		
		String  id  = res.getParameter("id");					
		VotePlan plan = votePlanServiceImpl.get(Integer.parseInt(id),true);		
		model.addAttribute("platForms", platFormServiceImpl.list());			
		model.addAttribute("plan", plan);		
		model.addAttribute("votes", voteServiceImpl.list());		
		return "account/base/vote/plan/user/update";
		
	}

	
	
	@RequestMapping(value="user/update",method=RequestMethod.POST)
	@AnnatationToken(RemoveToken=true)
	@AdminInfoToken(secondToken=true)
	public String userPlanUpdated(VotePlan plan ,HttpServletRequest res)  throws Exception{
		
		String platId =  res.getParameter("platId");
		String voteId =  res.getParameter("voteId");
		String openStr =  res.getParameter("openStr");
		String closeStr = res.getParameter("closeStr");		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date openTime = null;
		Date closeTime = null;
		try {
			openTime = sdf.parse(openStr);
			closeTime = sdf.parse(closeStr);
		} catch (ParseException e) {
					
			e.printStackTrace();
		}
		Vote vote =  voteServiceImpl.get(Integer.parseInt(voteId));
		PlatForm  platForm =  platFormServiceImpl.get(Integer.parseInt(platId));
		plan.setPlatForm(platForm);
		plan.setVote(vote);
		plan.setOpenTime(openTime);
		plan.setCloseTime(closeTime);
		votePlanServiceImpl.update(plan,Integer.parseInt(platId));	
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(plan, adminMap);
		return "redirect:/base/vote/plan/user/list";
			
	}
	
	@RequestMapping(value="delete")
	@AdminInfoToken(secondToken=true)
	public String delete(HttpServletRequest res)  throws Exception{
		
		String id =  res.getParameter("id");
		VotePlan plan  =  votePlanServiceImpl.get(Integer.parseInt(id),true);		
		votePlanServiceImpl.delete(plan);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "delete");
		adminOperateVector.operate(plan, adminMap);
		if(plan.getRefType().equals("book")){
			return "redirect:/book/info/view?bookId="+plan.getRefObjId()+"&platId="+plan.getPlatForm().getId();
		}else if(plan.getRefType().equals("part")){
			return "redirect:/book/part/view?id="+plan.getRefSubId();			
		}
		return "redirect:/base/vote/plan/view?id="+plan.getId();
	}

}
