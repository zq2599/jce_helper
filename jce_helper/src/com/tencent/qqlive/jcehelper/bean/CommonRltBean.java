package com.tencent.qqlive.jcehelper.bean;

import java.io.Serializable;

public class CommonRltBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private int returnCode;
	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	private String msg;
	private CommonParamBean param;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public CommonParamBean getParam() {
		return param;
	}

	public void setParam(CommonParamBean param) {
		this.param = param;
	}

	
	
}
