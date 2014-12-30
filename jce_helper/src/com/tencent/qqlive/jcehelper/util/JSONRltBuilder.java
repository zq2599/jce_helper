package com.tencent.qqlive.jcehelper.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tencent.qqlive.jcehelper.bean.CommonListRltBean;
import com.tencent.qqlive.jcehelper.bean.CommonParamBean;

public class JSONRltBuilder {

	public static void optPut(JSONObject obj, String key, String value)
			throws JSONException {
		obj.put(key, null == value ? "" : value);
	}

	public static JSONObject buildSingle(CommonParamBean param)
			throws JSONException {
		JSONObject obj = new JSONObject();
		optPut(obj, "param0", param.getParam0());
		optPut(obj, "param1", param.getParam1());
		optPut(obj, "param2", param.getParam2());
		optPut(obj, "param3", param.getParam3());
		optPut(obj, "param4", param.getParam4());
		optPut(obj, "param5", param.getParam5());
		optPut(obj, "param6", param.getParam6());
		optPut(obj, "param7", param.getParam7());
		optPut(obj, "param8", param.getParam8());
		optPut(obj, "param9", param.getParam9());
		return obj;
	}

	public static JSONObject build(CommonListRltBean bean) {
		JSONObject rlt = new JSONObject();
		try {
			rlt.put("returnCode", bean.getReturnCode());
			rlt.put("msg", bean.getMsg());
			
			if(null!=bean.getExtendParam()){
				rlt.put("extend", buildSingle(bean.getExtendParam()));
			}

			if (null != bean.getParams() && !bean.getParams().isEmpty()) {
				JSONArray array = new JSONArray();
				for (CommonParamBean param : bean.getParams()) {
					array.put(buildSingle(param));
				}

				rlt.put("params", array);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return rlt;
	}
	
	public static String buildStr(CommonListRltBean bean) {
		JSONObject object = build(bean);
		return null==object ? "" : object.toString();
	}
}
