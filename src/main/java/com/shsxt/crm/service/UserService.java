package com.shsxt.crm.service;


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.shsxt.crm.base.ResultInfo;
import com.shsxt.crm.contant.Contant;
import com.shsxt.crm.dao.UserDao;
import com.shsxt.crm.exception.ParamException;
import com.shsxt.crm.model.User;
import com.shsxt.crm.util.MD5Util;
import com.shsxt.crm.util.UserIDBase64;
import com.shsxt.crm.vo.UserLoginIdentity;
import com.shsxt.crm.vo.UserVO;

@Service
public class UserService {
	
	@Resource
	private UserDao userDao;
	
	
	/**
	 * 登路校验
	 * @param userName
	 * @param passWord
	 * @return
	 */

	public UserLoginIdentity login(String userName, String password) {
		//Map<String, Object> result=new HashMap<String, Object>();
		
		if (StringUtils.isBlank(userName)) {
			throw new ParamException("请输入用户名");
			
			/*result.put("resultCode", 0);
			result.put("resulmessage", "请输入用户名");
			return result;*/
		}
		
		if (StringUtils.isBlank(password)) {
			
			throw new ParamException(0,"请输入用户密码。");
			
			/*
			result.put("resultCode", 0);
			result.put("resultmessage", "请输入用户密码");
			return result;*/
		}
		
		
		
		User user=userDao.queryUserByName(userName);
		
		if (user==null) {
			throw new ParamException("用户密码或者用户不正确。。");
			/*result.put("resultCode", 0);
			result.put("resultmessage", "用户密码或者用户不正确");
			return result;*/
		}
		
		String md5pwd= MD5Util.md5Method(password);
				
		if (!md5pwd.equals(user.getPassword().trim())) {
			throw new ParamException("用户密码或者用户不正确。。");
			/*result.put("resultCode", 0);
			result.put("resultmessage", "用户密码或者用户不正确");
			return result;*/
		}
		
		/**
		 * 构建登陆实体
		 */
		
		UserLoginIdentity userLoginIdentity=new UserLoginIdentity();
		userLoginIdentity.setRealName(user.getTrueName());
		userLoginIdentity.setUserIdString(UserIDBase64.encoderUserID(user.getId()));
		userLoginIdentity.setUserName(userName);
		
		
		
		
		/*result.put("resultCode", 1);
		result.put("resultmessage", "登陆成功。。。。");
		result.put("result", userLoginIdentity);*/
		//ResultInfo result=new ResultInfo(Contant.SUCCESS_CODE,Contant.SUCCESS_MESSAGE,userLoginIdentity );
		return userLoginIdentity;
		

	}


	public List<UserVO> findCutomerManager() {
		List<UserVO> customerManagers = userDao.findCutomerManager();
		return customerManagers;
	}

}
