package com.fanfan.manage.control.base.vote;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fanfan.manage.inteceptor.AdminInfoToken;
import com.fanfan.manage.inteceptor.AnnatationToken;
import com.fanfan.manage.modle.admin.AdminLoginInfo;
import com.fanfan.manage.modle.base.vote.Vote;
import com.fanfan.manage.modle.base.vote.VoteOption;
import com.fanfan.manage.service.base.vote.VoteOptionServiceImpl;
import com.fanfan.manage.service.base.vote.VoteServiceImpl;
import com.fanfan.manage.service.vector.AdminOperateVector;

@Controller
@RequestMapping(value="base/vote/option/")
public class VoteOptionController {
	
	@Autowired
	private VoteServiceImpl voteServiceImpl;
	
	@Autowired
	private VoteOptionServiceImpl voteOptionServiceImpl;
	
	@Autowired 
	private AdminOperateVector adminOperateVector;
	
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	@AnnatationToken(SaveToken=true)
	@AdminInfoToken(secondToken=true)
	public String create(HttpServletRequest res,Model model)  throws Exception{
		
		String voteId = res.getParameter("voteId");
		Vote vote = voteServiceImpl.get(Integer.parseInt(voteId));
		model.addAttribute("vote", vote);		
		return "account/base/vote/option/create";		
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	@AnnatationToken(RemoveToken=true)
	public String created(VoteOption option,HttpServletRequest res)  throws Exception{
		
		String voteId = res.getParameter("voteId");
		Vote vote = voteServiceImpl.get(Integer.parseInt(voteId));		
		option.setVote(vote);		
		voteOptionServiceImpl.put(option);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "create");
		adminOperateVector.operate(vote, adminMap);
		return "redirect:/base/vote/view?id="+voteId;
		
	}
	
	@RequestMapping(value="delete")
	@AdminInfoToken(secondToken=true)
	public String delete(HttpServletRequest res)  throws Exception{
		
		String id  = res.getParameter("id");		
		String voteId =  res.getParameter("voteId");
		VoteOption option  =  voteOptionServiceImpl.get(Integer.parseInt(id));
		voteOptionServiceImpl.delete(option);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "delete");
		adminOperateVector.operate(option, adminMap);
		return "redirect:/base/vote/view?id="+voteId;
	}
	@RequestMapping(value="view")
	@AdminInfoToken(secondToken=true)
	public String view(HttpServletRequest res,Model model)  throws Exception{
		String id  = res.getParameter("id");
		VoteOption option  =  voteOptionServiceImpl.get(Integer.parseInt(id));
		model.addAttribute("option", option);
		return "account/base/vote/option/view";
		
	}
	
	@RequestMapping(value="update",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String update(HttpServletRequest res,Model model)  throws Exception{
		
		String id  = res.getParameter("id");
		String voteId =  res.getParameter("voteId");
		VoteOption option  =  voteOptionServiceImpl.get(Integer.parseInt(id));
		model.addAttribute("option", option);
		model.addAttribute("vote", voteServiceImpl.get(Integer.parseInt(voteId)));
		return "account/base/vote/option/update";
		
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String updated(HttpServletRequest res,VoteOption option)  throws Exception{
		//String id =  res.getParameter("id");
		String voteId =  res.getParameter("voteId");
		Vote vote  =  voteServiceImpl.get(Integer.parseInt(voteId));
		option.setVote(vote);
		voteOptionServiceImpl.update(option);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(option, adminMap);
		return "redirect:/base/vote/view?id="+voteId;
	}
	
}
