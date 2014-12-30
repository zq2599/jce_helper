package com.tencent.qqlive.jcehelper.bean;

import java.io.Serializable;
import java.util.List;

public class CommonListRltBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private int returnCode;
	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	private String msg;
	private List<CommonParamBean> params;
	private CommonParamBean extendParam;

	public CommonParamBean getExtendParam() {
		return extendParam;
	}

	public void setExtendParam(CommonParamBean extendParam) {
		this.extendParam = extendParam;
	}

	public List<CommonParamBean> getParams() {
		return params;
	}

	public void setParams(List<CommonParamBean> params) {
		this.params = params;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
