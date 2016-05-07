package cn.net.msg.plugin.header;

import org.json.JSONArray;

import cn.net.msg.plugin.IPlugin;
import cn.net.msg.plugin.annotation.PluginDataSource;
import cn.net.msg.plugin.annotation.PluginDataType;
import cn.net.msg.plugin.annotation.PluginInfomation;

/**
 * 包头命令字转成十六进制和十进制两种表述
 * @author willzhao
 *
 */
@PluginInfomation
(
		creator = "willzhao", 
		desc = "包头的token展示成char数组", 
		jces = "", 
		path = "array(token),field(TokenValue)", 
		source = PluginDataSource.header, 
		type = PluginDataType.data_type_array, 
		version = "0.01"
)
public class HeadTokenFormat implements IPlugin {

	@Override
	public Object buildObject(Object rawObject) throws Exception {
		String rlt = null;
		if(null!=rawObject && rawObject instanceof JSONArray){
			JSONArray array = (JSONArray)rawObject;
			int len = array.length();
			if(len>0){
				StringBuilder sbud = new StringBuilder();
				for(int i=0;i<len;i++){
					int val = array.optInt(i);
					sbud.append((char)val);
				}
				rlt = sbud.toString();
			}
		}
		
		return rlt;
	}

}
