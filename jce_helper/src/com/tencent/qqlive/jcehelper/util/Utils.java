package com.tencent.qqlive.jcehelper.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.tencent.qqlive.jcehelper.bean.CommonListRltBean;
import com.tencent.qqlive.jcehelper.bean.CommonParamBean;
import com.tencent.qqlive.jcehelper.bean.IdentifyInfo;
import com.tencent.qqlive.jcehelper.buffer.CoreBuffer;

public class Utils {
	public static void outputResponse(HttpServletResponse response, int returnCode, String msg, List<CommonParamBean> list){
		response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");//
		response.setCharacterEncoding("UTF-8");
		
		CommonListRltBean bean = new CommonListRltBean();
		bean.setReturnCode(returnCode);
		bean.setMsg(msg);
		
		//项目列表
		bean.setParams(list);
		JSONObject obj = JSONRltBuilder.build(bean);
		
		try{
			PrintWriter out = response.getWriter();
			out.println(obj.toString());
			out.flush();
			out.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void outputResponse(HttpServletResponse response, int returnCode, String msg){
		outputResponse(response, returnCode, msg, null);
	}
	
	

	public static CommonParamBean getRequestParam(HttpServletRequest request) {
		CommonParamBean bean = new CommonParamBean();
		if (null != request) {
			bean.setParam0(request.getParameter("param0"));
			bean.setParam1(request.getParameter("param1"));
			bean.setParam2(request.getParameter("param2"));
			bean.setParam3(request.getParameter("param3"));
			bean.setParam4(request.getParameter("param4"));
			bean.setParam5(request.getParameter("param5"));
			bean.setParam6(request.getParameter("param6"));
			bean.setParam7(request.getParameter("param7"));
			bean.setParam8(request.getParameter("param8"));
			bean.setParam9(request.getParameter("param9"));
		}
	
		return bean;
	}

	public static boolean isValidInt(String str){
		if(Utils.isEmpty(str)){
			return false;
		}
		boolean rlt = false;
		
		try{
			Integer.parseInt(str);
			rlt = true;
		}catch(NumberFormatException e){
			
		}
		
		return rlt;
	}
	
	public static boolean isEmpty(String str){
		return null==str && "".equals(str);
	}
	
	public static boolean isValidLong(String str){
		if(Utils.isEmpty(str)){
			return false;
		}
		boolean rlt = false;
		
		try{
			Long.parseLong(str);
			rlt = true;
		}catch(NumberFormatException e){
			
		}
		
		return rlt;
	}
	
	
	public static boolean isValid(String... array) {
		boolean rlt = true;
	
		for (String tmpStr : array) {
			if (null == tmpStr || "".equals(tmpStr)) {
				rlt = false;
				break;
			}
		}
	
		return rlt;
	}
	
	public static String formatDateTime(long raw){
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return sFormat.format(raw);
	}
	
	public static String formatTime(long raw){
		SimpleDateFormat sFormat = new SimpleDateFormat("HH:mm:ss.SSS");
		return sFormat.format(raw);
	}
	
	public static int optInt(String str, int defaultVal){
		int rlt = defaultVal;
		if(isValidInt(str)){
			rlt = Integer.valueOf(str);
		}
		
		return rlt;
	}

	/**
	 * 从请求参数中构建账户信息
	 * @param param
	 * @return
	 */
	public static IdentifyInfo buildIdentify(CommonParamBean param)
	{
		if(null==param || isEmpty(param.getParam1())){
			Log.e(CoreBuffer.TAG, "invalid data for buildIdentify");
			return null;
		}
		
		IdentifyInfo info = new IdentifyInfo();
		try{
			JSONObject j = new JSONObject(param.getParam1());
			info.setGuid(j.optString("guid"));
			info.setUserid(j.optString("userid"));
			info.setWxnickname(j.optString("wxnickname"));
			info.setWxheadimgurl(j.optString("wxheadimgurl"));
			info.setQq(j.optString("qq"));
			info.setQqnickname(j.optString("qqnickname"));
			info.setQqheadimgurl(j.optString("qqheadimgurl"));
		}catch(JSONException e){
			Log.e(CoreBuffer.TAG, "buildIdentify error : " + e);
		}
		
		return info;
	}
}
