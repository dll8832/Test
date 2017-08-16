package com.shsxt.crm.exception;

@SuppressWarnings("serial")
public class ParamException extends RuntimeException{

	private Integer errCode;
	
	
	public ParamException(String message) {
		super();
		this.errCode=0;
	}

	public ParamException(Integer i, String message) {
		super(message);
		this.errCode=i;
	}

	public Integer getErrCode() {
		return errCode;
	}

	public void setErrCode(Integer errCode) {
		this.errCode = errCode;
	}
	
	
	
	

}
