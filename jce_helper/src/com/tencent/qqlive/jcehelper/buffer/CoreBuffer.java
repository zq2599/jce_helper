package com.tencent.qqlive.jcehelper.buffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.tencent.qqlive.jcehelper.bean.CommonParamBean;
import com.tencent.qqlive.jcehelper.bean.IdentifyInfo;
import com.tencent.qqlive.jcehelper.businessinterface.CommonBuffer;
import com.tencent.qqlive.jcehelper.util.Log;
import com.tencent.qqlive.jcehelper.util.Utils;

public class CoreBuffer implements CommonBuffer{
	public final static String TAG = "CoreBuffer";
	private final static String SUFFIX_REQUEST = "Request";
	
	public static CoreBuffer instanceBuffer = null;
	
	Map<String, LinkedList<CommonParamBean>> map = new HashMap<String, LinkedList<CommonParamBean>>();
	
	private CoreBuffer(){
		super();
	};
	
	public static CoreBuffer getInstance(){
		if(null==instanceBuffer){
			synchronized (CoreBuffer.class) {
				instanceBuffer = new CoreBuffer();
			}
		}
		
		return instanceBuffer;
	}
	
	/**
	 * 从请求参数中取得guid
	 * @param param
	 * @return
	 */
	private static String guid(CommonParamBean param){
		return param.getParam1();
	}
	
	/**
	 * 取得请求或者响应的详细信息
	 * @param raw
	 * @return
	 */
	public static String[] getDescInfo(String raw){
		String[] rlt = new String[3];
		
		if(!Utils.isEmpty(raw)){
			try{
				JSONObject j = new JSONObject(raw);
				rlt[0] = "" + j.optLong("time");
				rlt[1] =  j.optString("className");
				rlt[2] =  "" + j.optLong("size");
			}catch(JSONException e){
				Log.e(TAG, "buildCommunicateDesc error : " + e);
			}
		}
		
		return rlt;
	}
	
	private static String buildDesc(CommonParamBean cpb){
		if(null==cpb){
			Log.i(TAG, "invalide cpb for convert");
			return null;
		}
		StringBuilder sbud = new StringBuilder();
		
		sbud.append("序号:"+cpb.getParam0());
		if(Utils.isValidInt(cpb.getParam7())){
			int cmd = Integer.valueOf(cpb.getParam7());
			sbud.append("，命令字:" + String.format("0x%x (%d)",cmd,cmd));
		}
		
		String[] requestArray = getDescInfo(cpb.getParam3());
		String[] responseArray = getDescInfo(cpb.getParam5());
		
		sbud.append("，时间:" + Utils.formatTime(Long.valueOf(requestArray[0])));
		sbud.append("，耗时:" + (Long.valueOf(responseArray[0])-Long.valueOf(requestArray[0])) + "ms");
		sbud.append("，请求包:" + requestArray[2] + "，响应包:" + responseArray[2]);
		
		String jceName = requestArray[1].replace("com.tencent.qqlive.ona.protocol.jce.", "");
		
		if(SUFFIX_REQUEST.equals(jceName.substring(jceName.length()-SUFFIX_REQUEST.length()))){
			jceName = jceName.substring(0, jceName.length()-SUFFIX_REQUEST.length());
		}
		
		sbud.append("，JCE:" + jceName);
		return sbud.toString();
	}
	
	private CommonParamBean atomicGet(String guid){
		LinkedList<CommonParamBean> allDataList = null;
		CommonParamBean singleBean = null;
		if(map.containsKey(guid)){
			allDataList = map.get(guid);
		}
		
		if(null!=allDataList && !allDataList.isEmpty()){
			singleBean = allDataList.removeFirst();
		}
		
		return singleBean;
	}
	
	private synchronized List<CommonParamBean> getAllRawData(String guid) {
		List<CommonParamBean> rlt = new ArrayList<CommonParamBean>();
		Log.i(TAG, "start getData, guid : " + guid);
		
		while(true){
			CommonParamBean singleBean = atomicGet(guid);
			if(null!=singleBean){
				rlt.add(singleBean);
			}else{
				break;
			}
		}
		
		return rlt;
	}
	
	/**
	 * 创建页面列表所需的数据，此时由于每个请求都还没打开，所以暂时不放入详情数据
	 * @param cpb
	 * @return
	 */
	private CommonParamBean buildTitleBean(CommonParamBean cpb){
		if(null==cpb){
			Log.i(TAG, "invalide cpb for convert");
			return null;
		}
		
		CommonParamBean c = new CommonParamBean();
		//标题
		c.setParam0(buildDesc(cpb));
		//数据库主键
		c.setParam1(cpb.getParam8());
		//机器信息
		c.setParam2(cpb.getParam2());
		//账号信息
		
		
		
		return c;
	}
	
	public List<CommonParamBean> getData(CommonParamBean param, int size) {
		List<CommonParamBean> rlt = new ArrayList<CommonParamBean>();
		String guid = guid(param);
		Log.i(TAG, "start getData, guid : " + guid);
		
		List<CommonParamBean> rawDataList = getAllRawData(guid);
		
		for(CommonParamBean cpb : rawDataList){
			rlt.add(buildTitleBean(cpb));
		}
		
		//清掉无用的集合
		rawDataList.clear();
		
		return rlt;
	}
	
	/**
	 * 采用同步的方式给map中新增一个key
	 * @param guid
	 * @param list
	 */
	private synchronized void addNew(String guid, LinkedList<CommonParamBean> list){
		map.put(guid, list);
	}
	
	/**
	 * 采用同步的方式给map中的list增加数据,加到尾部
	 * @param list
	 * @param singleBean
	 */
	private synchronized void atomicPut(LinkedList<CommonParamBean> list, CommonParamBean singleBean){
		list.addLast(singleBean);
		Collections.sort(list);
	}

	public void put(CommonParamBean param) {
		IdentifyInfo identifyInfo = Utils.buildIdentify(param);
		String guid = identifyInfo.getGuid();
		LinkedList<CommonParamBean> allDataList = null;
		if(map.containsKey(guid)){
			allDataList = map.get(guid);
		}
		
		if(null==allDataList){
			allDataList = new LinkedList<CommonParamBean>();
			addNew(guid, allDataList);
		}
		
		//同步进行新增操作
		atomicPut(allDataList,param);
		//Log.i(TAG, "设备[" + guid + "](" + allDataList.size() + ")新增一次交互 : " + param);
	}
	
}
