package cn.net.msg.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.omg.CORBA.FieldNameHelper;

import com.google.gson.JsonObject;
import com.tencent.qqlive.jcehelper.util.Log;

import cn.net.msg.plugin.annotation.PluginDataSource;
import cn.net.msg.plugin.annotation.PluginInfomation;
import cn.net.msg.plugin.header.HeadCmdDesc;
import cn.net.msg.plugin.header.HeadPlatformDesc;
import cn.net.msg.plugin.header.HeadTokenFormat;
import cn.net.msg.plugin.header.HeadTokenKeyTypeDesc;
import cn.net.msg.plugin.response.ChannelDataResponseItem;
import cn.net.msg.plugin.response.EONAViewTypeDesc;

public class PluginManager {
	private final static String TAG = "PluginManager";
	
	private Class[] PLUGIN_ARRAY_HEADER = {HeadCmdDesc.class,HeadPlatformDesc.class,HeadTokenFormat.class,HeadTokenKeyTypeDesc.class};
	private Class[] PLUGIN_ARRAY_REQUEST = {};
	private Class[] PLUGIN_ARRAY_RESPONSE = {EONAViewTypeDesc.class, ChannelDataResponseItem.class};
	
	private Map<String, PluginInfo> mHeaderMap = new HashMap<String, PluginInfo>();
	private Map<String, PluginInfo> mRequestMap = new HashMap<String, PluginInfo>();
	private Map<String, PluginInfo> mResponseMap = new HashMap<String, PluginInfo>();
	
	private static PluginManager pluginManager;
	/**
	 * 数据初始化
	 */
	private void init(){
		for(Class c : PLUGIN_ARRAY_HEADER){
			mHeaderMap.put(c.getName(), buildPluginInfo(c));
		}
		
		for(Class c : PLUGIN_ARRAY_REQUEST){
			mRequestMap.put(c.getName(), buildPluginInfo(c));
		}
		
		for(Class c : PLUGIN_ARRAY_RESPONSE){
			mResponseMap.put(c.getName(), buildPluginInfo(c));
		}
	}
	
	private PluginManager(){
	} 
	
	public synchronized static PluginManager getInstance(){
		if(null==pluginManager){
			pluginManager = new PluginManager();
			pluginManager.init();
		}
		
		return pluginManager;
	}
	
	/**
	 * 取得某个class的配置信息
	 * @param c
	 * @return
	 */
	public PluginInfo buildPluginInfo(Class c) {
		PluginInfo p = null;
		if (c.isAnnotationPresent(PluginInfomation.class)) {
			PluginInfomation i = (PluginInfomation) c.getAnnotation(PluginInfomation.class);
			if (null != i) {
				p = new PluginInfo();
				p.c = c;
				p.creator = i.creator();
				p.desc = i.desc();
				p.version = i.version();
				p.source = i.source();
				p.type = i.type();
				p.jces = i.jces();
				p.path = i.path();
			}
		}
		
		return p;
	}
	
	private static enum NodeType{
		type_unknown,
		type_field,
		type_object,
		type_array,
	} 
	
	private static class PathInfo{
		NodeType nodeType;
		String fieldName;
	}
	
	
	private NodeType getNodeType(String raw){
		NodeType rlt = NodeType.type_unknown;
		if("field".equals(raw)){
			rlt = NodeType.type_field;
		}else if("array".equals(raw)){
			rlt = NodeType.type_array;
		}
		
		return rlt;
	}
	
	private List<PathInfo> buildPathInfos(String rawPath){
		List<PathInfo> list = new ArrayList<PathInfo>();
		String[] array = rawPath.split(",");
		if(null!=array && array.length>0){
			for(int i=0;i<array.length;i++){
				PathInfo p = new PathInfo();
				String raw = array[i];
				int startBrace = raw.indexOf("(");
				int endBrace = raw.indexOf(")");
				p.nodeType = getNodeType(raw.substring(0, startBrace));
				p.fieldName = raw.substring(startBrace+1, endBrace);
				list.add(p);
			}
		}
		
		return list;
	}
	
	public static void main(String[] args){
		//String raw = "{\"appId\": \"1000005\",\"requestId\": 4,\"cmdId\": 59605}";
		//String raw = "{\"pageContext\": \"page=2\",\"hasNextPage\": true,\"errCode\": 0,\"data\": [{\"groupId\": \"20140827046920\",\"item\": {\"itemType\": 3,\"data\": [6,0,28]},\"lineId\": \"\"}]}";
		String raw = "{\"token\": [{\"IsMainLogin\": true,\"TokenAppID\": \"wxca942bbff22e0e51\",\"TokenValue\": [79,101,122,88,99,69,105,105,66,83,75,83,120,87,48,101,111,121,108,73,101,69,106,50,67,69,79,109,66,90,120,103,49,65,119,82,112,71,115,77,72,71,119,49,78,97,82,90,84,101,104,85,65,67,75,104,108,53,45,98,56,71,104,51,76,116,85,49,54,71,83,48,112,101,81,116,69,76,52,100,85,98,78,73,74,51,104,73,97,65,45,84,84,110,109,67,79,112,120,79,95,108,111,78,69,74,73,68,78,121,85,121,106,45,101,45,68,114,67,70,84,82,57,77,97,119,54,107,114,98,78,98,99,50,51,107,82,105,76,105,68,86,54,53,111,75,120,70,52,81],\"TokenKeyType\": 100,\"TokenUin\": \"oQFqrjgKH50yNLm3ttqsgxIhCbXw\"}]}";
		
		System.out.println(PluginManager.getInstance().doConvert(PluginDataSource.header, raw));
	}
	
	/**
	 * 用制定插件对原始数据做处理
	 * @param rawObject
	 * @param info
	 */
	private void doSinglePlugin(JSONObject rawObject, PluginInfo info, List<PathInfo> pathInfos){
		//List<PathInfo> pathInfos = buildPathInfos(info.path);
		JSONObject lastObj = rawObject;
		
		if(null!=pathInfos && !pathInfos.isEmpty()){
			int size = pathInfos.size();
			boolean isContinue = true;
			for(int i=0;i<size;i++){
				//如果不需要继续处理，就会提前将isContinue标志设置为false，此处就会自动退出
				if(!isContinue){
					break;
				}
				if(null==lastObj){
					break;
				}
				PathInfo pInfo = pathInfos.get(i);
				if(null==pInfo){
					Log.e(TAG, "invalid pathInfo");
					break;
				}
				
				switch(pInfo.nodeType){
				case type_field : 
					//如果是最后一个
					if(i>=(size-1)){
						Object dataObj = null;
						switch(info.type){
						case data_type_str:
							dataObj = lastObj.optString(pInfo.fieldName);
						break;
						case data_type_int:
							dataObj = lastObj.optInt(pInfo.fieldName);
						break;
						case data_type_long:
							dataObj = lastObj.optLong(pInfo.fieldName);
						break;
						case data_type_array:
							dataObj = lastObj.optJSONArray(pInfo.fieldName);
						break;
						}
						
						//实例化一个插件对象
						try{
							IPlugin plugin = (IPlugin)info.c.newInstance();
							Object newObject = plugin.buildObject(dataObj);
							if(null!=newObject){
								lastObj.put(pInfo.fieldName, newObject);
							}
						}catch(Exception e){
							Log.e(TAG, "doSinglePlugin error : " + e);
						}
					}else{
						lastObj = lastObj.optJSONObject(pInfo.fieldName);
					}
				break;
				
				case type_array : 
					isContinue = false;
					if(lastObj.has(pInfo.fieldName)){
						try{
							//取出该数组中的所有数据，然后逐一处理，通过迭代的方式继续处理每个节点
							JSONArray jsonArray = lastObj.getJSONArray(pInfo.fieldName);
							if(null!=jsonArray && jsonArray.length()>0){
								for(int j=0;j<jsonArray.length();j++){
									JSONObject subObject = jsonArray.getJSONObject(j);
									List<PathInfo> subList = new ArrayList<PathInfo>(); 
									subList.addAll(pathInfos.subList(i+1,size));
									doSinglePlugin(subObject, info, subList);
								}
							}
						}catch(Exception e){
							Log.e(TAG, "doSinglePlugin error : " + e);
						}
					}
				break;
				
				}
			}
		}
	} 
	
	public String doConvert(PluginDataSource source, String raw){
		String rlt = null;
		
		JSONObject rawObject = null;
		try{
			rawObject = new JSONObject(raw);
		}catch(Exception e){
			Log.e(TAG, "build total object error : " + e);
		}
		
		if(null!=rawObject){
			Map<String, PluginInfo> map = null;
			switch(source){
			case header:
				map = mHeaderMap;
				break;
			case request:
				map = mRequestMap;
				break;
			case response:
				map = mResponseMap;
				break;
			}
			Iterator<Map.Entry<String, PluginInfo>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, PluginInfo> entry = (Map.Entry<String, PluginInfo>)it.next();
				String key = entry.getKey();
				PluginInfo info = entry.getValue();
				Log.e(TAG, "try plugin, key : " + key + ", value : " + info);
				List<PathInfo> pathInfos = buildPathInfos(info.path);
				doSinglePlugin(rawObject, info, pathInfos);
			}
			rlt = rawObject.toString();
		}
		
		return null==rlt ? raw : rlt;
	}
	
}
