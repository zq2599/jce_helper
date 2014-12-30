package com.tencent.qqlive.jcehelper.util;

import com.tencent.qqlive.jcehelper.bean.CommonListRltBean;

public class Constants {
	/**
	 * 通用的最简单的返回成功对象
	 */
	public static String COMMON_SUCC = null;

	public static String PATH_CUSTOMERS = "customers";

	public static String KEY_FAVORITE = "favorite";
	
	public static String KEY_CART = "cart";
	
	public static String KEY_ORDER ="order";
	
	public static String KEY_USERINFO ="userInfo";
	
	
	static{
		CommonListRltBean commonListRltBean = new CommonListRltBean();
		commonListRltBean.setReturnCode(0);
		commonListRltBean.setMsg("operate success");
		COMMON_SUCC =  JSONRltBuilder.build(commonListRltBean).toString();
	}
}
