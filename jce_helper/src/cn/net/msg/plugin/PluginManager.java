package cn.net.msg.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.omg.CORBA.FieldNameHelper;

import com.google.gson.JsonObject;
import com.tencent.qqlive.jcehelper.util.Log;
import cn.net.msg.plugin.annotation.PluginInfomation;
import cn.net.msg.plugin.header.HeadCmdDesc;

public class PluginManager {
	private final static String TAG = "PluginManager";
	
	private Class[] PLUGIN_ARRAY_HEADER = {HeadCmdDesc.class};
	private Class[] PLUGIN_ARRAY_REQUEST = {};
	private Class[] PLUGIN_ARRAY_RESPONSE = {};
	
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
	} 
	
	private static class PathInfo{
		NodeType nodeType;
		String fieldName;
	}
	
	
	private NodeType getNodeType(String raw){
		NodeType rlt = NodeType.type_unknown;
		if("field".equals(raw)){
			rlt = NodeType.type_field;
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
		String raw = "{\"appId\": \"1000005\",\"requestId\": 4,\"cmdId\": 59605}";
		
		PluginManager.getInstance().doHeaderConvert(raw);
	}
	
	/**
	 * 用制定插件对原始数据做处理
	 * @param rawObject
	 * @param info
	 */
	private void doSinglePlugin(JSONObject rawObject, PluginInfo info){
		List<PathInfo> pathInfos = buildPathInfos(info.path);
		JSONObject lastObj = rawObject;
		
		if(null!=pathInfos && !pathInfos.isEmpty()){
			int size = pathInfos.size();
			for(int i=0;i<size;i++){
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
						case type_str:
							dataObj = lastObj.optString(pInfo.fieldName);
						break;
						case type_int:
							dataObj = lastObj.optInt(pInfo.fieldName);
						break;
						case type_long:
							dataObj = lastObj.optLong(pInfo.fieldName);
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
				}
			}
		}
	} 
	
	public String doHeaderConvert(String raw){
		String rlt = null;
		
		JSONObject rawObject = null;
		try{
			rawObject = new JSONObject(raw);
		}catch(Exception e){
			Log.e(TAG, "build total object error : " + e);
		}
		
		if(null!=rawObject){
			Iterator<Map.Entry<String, PluginInfo>> it = mHeaderMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, PluginInfo> entry = (Map.Entry<String, PluginInfo>)it.next();
				String key = entry.getKey();
				PluginInfo info = entry.getValue();
				Log.e(TAG, "try plugin, key : " + key + ", value : " + info);
				doSinglePlugin(rawObject, info);
			}
			rlt = rawObject.toString();
		}
		
		return rlt;
	}
	
}
