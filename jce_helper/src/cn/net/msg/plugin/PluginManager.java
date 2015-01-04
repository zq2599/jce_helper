package cn.net.msg.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

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
		type_field,
		type_object,
	} 
	
	private static class PathInfo{
		NodeType nodeType;
		String fieldName;
	}
	
	
	private List<PathInfo> buildPathInfos(String rawPath){
		List<PathInfo> list = new ArrayList<PathInfo>();
		
		return list;
	}
	
	/**
	 * 用制定插件对原始数据做处理
	 * @param rawObject
	 * @param info
	 */
	private void doSinglePlugin(JSONObject rawObject, PluginInfo info){
		List<PathInfo> pathInfos = buildPathInfos(info.path);
		
	} 
	
	
	
	
	public String doHeaderConvert(String raw){
		String rlt = raw;
		/*
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
				
			}
		}
		*/
		return rlt;
	}
	
}
