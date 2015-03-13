package com.fanfan.manage.control.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="client/")
public class ClientController {

	@RequestMapping(value="home")
	public String index(){
		
		return "account/client/index";
		
	}
	
}
