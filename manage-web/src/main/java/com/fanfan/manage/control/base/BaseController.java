package com.fanfan.manage.control.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="base/")
public class BaseController {

	@RequestMapping(value="home")
	
	public String index(){
		return "account/base/index";
	}
	
	
}
