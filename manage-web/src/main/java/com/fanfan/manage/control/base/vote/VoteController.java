package com.fanfan.manage.control.base.vote;

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
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.service.base.PlatFormServiceImpl;
import com.fanfan.manage.service.base.vote.VoteOptionServiceImpl;
import com.fanfan.manage.service.base.vote.VoteServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.vector.AdminOperateVector;

@Controller
@RequestMapping(value="base/vote/")
public class VoteController {
	
	@Autowired
	private VoteServiceImpl voteServiceImpl;	
	
	@Autowired
	private PlatFormServiceImpl platFormServiceImpl;
	
	@Autowired
	private VoteOptionServiceImpl voteOptionServiceImpl;
	
	@Autowired
	private AdminOperateVector adminOperateVector;
	
	@RequestMapping(value="home")
	public String index(){
		
		return"account/base/vote/index";		
	}
	@RequestMapping(value="create",method=RequestMethod.GET)
	@AnnatationToken(SaveToken=true)
	@AdminInfoToken(secondToken=true)
	public String create(Model model)  throws Exception{		
		model.addAttribute("platforms", platFormServiceImpl.list());
		return "account/base/vote/create";
	}
	
	@RequestMapping(value="addItem",method=RequestMethod.GET)
	@AnnatationToken(SaveToken=true)
	@AdminInfoToken(secondToken=true)
	public String addVoteItem(HttpServletRequest res,Model model)  throws Exception{	
		
		String voteId =  res.getParameter("voteId");
		model.addAttribute("platforms", platFormServiceImpl.list());
		model.addAttribute("voteId",voteId);
		model.addAttribute("vote", voteServiceImpl.get(Integer.parseInt(voteId)));
		
		return "account/base/vote/addItem";
	}
		
	@RequestMapping(value="addItem",method=RequestMethod.POST)
	@AnnatationToken(RemoveToken=true)
	@AdminInfoToken(secondToken=true)
	public String addVoteItemd(Vote vote,HttpServletRequest res)  throws Exception{
		
		String pid = res.getParameter("voteParentId");	
		String platId = res.getParameter("platId");
		Vote voteParent = voteServiceImpl.get(Integer.parseInt(pid));
		vote.setParent(voteParent);		
		voteServiceImpl.put(vote,Integer.parseInt(platId));
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "crearte");
		adminOperateVector.operate(vote, adminMap);	
		return "redirect:/base/vote/list";
		
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	@AnnatationToken(RemoveToken=true)
	public String created(Vote vote,HttpServletRequest res)  throws Exception{
		String platId = res.getParameter("platId");
		voteServiceImpl.put(vote,Integer.parseInt(platId));
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "crearte");
		adminOperateVector.operate(vote, adminMap);
		return "redirect:/base/vote/list";
						
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
		String url = "/manage/base/vote/list%s";		
		List<Vote> list =null;		
		int start = pageSize*(pageIndex-1);				
		list = voteServiceImpl.list(start, pageSize);			
		int total = voteServiceImpl.count();		
		PageNav<Vote> context = new PageNav<Vote>(list,total,pageSize,pageIndex,url);			
		model.addAttribute("context", context);	
		return "account/base/vote/list";
		
	}
	
	@RequestMapping(value="view",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public  String view(HttpServletRequest res,Model model)  throws Exception{
		
		int id =  Integer.parseInt(res.getParameter("id"));	
		Vote vote = voteServiceImpl.get(id);
		model.addAttribute("options", voteOptionServiceImpl.list(id));
		model.addAttribute("vote", vote);				
		return "account/base/vote/view";
		
	}
	
	@RequestMapping(value="update",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String update(HttpServletRequest  res,Model model)  throws Exception{
		String id = res.getParameter("id");
			
		Vote vote  =  voteServiceImpl.get(Integer.parseInt(id));
		
		model.addAttribute("vote", vote);
		model.addAttribute("platforms", platFormServiceImpl.list());
		
		return "account/base/vote/update";
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String update(HttpServletRequest  res,Vote vote)  throws Exception{
		String platId =  res.getParameter("platId");
		String pid = res.getParameter("pid");
		
		PlatForm plat = platFormServiceImpl.get(Integer.parseInt(platId));		
		vote.setPlatForm(plat);
		if(pid!=null&& !pid.equals("")){
			vote.setParent(voteServiceImpl.get(Integer.parseInt(pid)));
		}
		
		//voteServiceImpl.update(vote);
		voteServiceImpl.update(vote,Integer.parseInt(platId));
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(vote, adminMap);
		return "redirect:/base/vote/view?id="+vote.getId();
		
	}
	
	
	@RequestMapping(value="delete")
	@AdminInfoToken(secondToken=true)
	public String delete(HttpServletRequest res) throws Exception{
		
		String id =  res.getParameter("id");
		Vote vote  = voteServiceImpl.get(Integer.parseInt(id));
		voteServiceImpl.delete(vote);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "delete");
		adminOperateVector.operate(vote, adminMap);
		return "redirect:/base/vote/list";
		
		
	}
	
}
