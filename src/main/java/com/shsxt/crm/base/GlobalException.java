package com.shsxt.crm.base;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.crm.exception.ParamException;

@ControllerAdvice
public class GlobalException extends BaseController{
	
	
	@ExceptionHandler(value={ParamException.class})
	@ResponseBody
	public ResultInfo globalException(ParamException e){
		
		return failue(e);
	}
}
/*
1,	为什么全局异常这个要继承baseController    --------
	因为baseController 中有异常处理 返回   ResultInfo 主要是返回这个  然后便于返回
2,	@ControllerAdvice	注解的意思为  加强controller 
	
*/