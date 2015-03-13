package com.fanfan.manage.control.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value="common/tokenFactory")
public class TokenFactoryController {

	@RequestMapping(value="create",method=RequestMethod.POST)
	@ResponseBody
	public Map create_token(HttpServletRequest res){
		
		Map map =new HashMap();
		String token = Helps.getRandomString(16);
		map.put("token",token);
		HttpSession httpSession = res.getSession();
		httpSession.setAttribute("token", token);
		return map;
		
	}
	
}
