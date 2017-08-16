package com.shsxt.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.base.ResultInfo;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.vo.UserLoginIdentity;
import com.shsxt.crm.vo.UserVO;

@Controller
@RequestMapping("user")
public class UserController extends BaseController{

	@Autowired
	private UserService userService;
	
	/**
	 * 执行登陆
	 * @param userName
	 * @param passWord
	 * @return
	 */
	
	@RequestMapping("login01")
	@ResponseBody
	public ResultInfo login(String userName,String password){
		
		ResultInfo result;
		//try {
		UserLoginIdentity userLoginIdentity=userService.login(userName, password);
			result=success(userLoginIdentity);
	/*	} catch (Exception e) {
			//result=failue(e);
			result=new ResultInfo(Contant.ERR_CODE, Contant.ERR_MESSAGE, "caozuoshibai");
			
		}*/
		
		
		return result;
		
		
	}
	@RequestMapping("find_customer_manager")
	@ResponseBody
	public List<UserVO> findCutomerManager() {
		List<UserVO> customerManagers = userService.findCutomerManager();
		return customerManagers;
	}
	
}
