package com.shsxt.crm.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.util.CookieUtil;



@Controller
@RequestMapping("user")
public class IndexController extends BaseController{
	
	@RequestMapping("index")
	public String login(){
		return "index";
	}
	@RequestMapping("main")
	public String main(HttpServletRequest request,Model model){
		
		String realName=CookieUtil.getCookieValue(request, "realName");
		model.addAttribute("realName", realName);
		String userName=CookieUtil.getCookieValue(request, "userName");
		model.addAttribute("userName", userName);
		
		
		return "main";
	}
	
	
}
