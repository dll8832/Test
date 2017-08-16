package com.shsxt.crm.util;

import org.apache.commons.lang3.StringUtils;

import com.shsxt.crm.contant.Contant;
import com.shsxt.crm.exception.ParamException;
import com.shsxt.crm.model.SaleChance;

public class AssertUtil {
	
	public static void intIsNotNull(Integer value,String message){
		if (value==null) {
			throw new ParamException(loadMsg(message));
		}
	}
	public static void stringIsNotEmpty(String str,String message){
		
		if (StringUtils.isBlank(str)) {
			throw new ParamException(loadMsg(message));
		}
	}
	
	
	private static String loadMsg(String... message){
		String msg=" ";
		if (msg!=null&&message.length>0&&StringUtils.isNoneBlank(message[0])) {
			msg=message[0];
		}else{
			msg=Contant.ERR_MESSAGE;
		}
		return msg;
	}
	public static void notNull(Object obj, String string) {
		if (obj==null) {
			throw new ParamException(loadMsg(string));
		}
		
	}



}
