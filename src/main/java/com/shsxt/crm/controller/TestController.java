package com.shsxt.crm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.print.attribute.HashAttributeSet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.crm.model.User;
import com.shsxt.crm.service.UserService;

@Controller
@RequestMapping("test")
public class TestController {
	
	@Resource
	private UserService userService;
	
	//@GetMapping("get/{id}")
	@RequestMapping("id")
	@ResponseBody
	public Map<String, Object> get(Integer id){
		//User user=userService.queryById(id);
		 Map<String, Object> map=new HashMap<String,Object>();
		 map.put("code", 1);
		 map.put("message", "haole");
		// map.put("result", user);

		return map;
	}

}
