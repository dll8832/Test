package com.shsxt.crm.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.shsxt.crm.contant.Contant;
import com.shsxt.crm.exception.ParamException;

public class BaseController {
	
	@ModelAttribute
	public void preMothed(HttpServletRequest re,Model model){
		String ctx=re.getContextPath();
		model.addAttribute("ctx", ctx);
		
	}
	
	/**
	 * 返回成功或者失败的时候resultInfo信息
	 */
	public ResultInfo failue(Integer errCode,String errMessage){
		ResultInfo result=new ResultInfo(errCode, errMessage,errMessage);
		return result;
	}
	
	public ResultInfo failue(String errMessage){
		ResultInfo result=failue(Contant.ERR_CODE,errMessage);
		return result;
	}
	
	public ResultInfo failue(ParamException e){
		ResultInfo result=failue(e.getErrCode(),e.getMessage());
		return result;
		
	}
	
	public ResultInfo success(Object result){
		
		ResultInfo resultInfo=new ResultInfo(Contant.SUCCESS_CODE, Contant.SUCCESS_MESSAGE, result);
		return resultInfo;
	}

}
