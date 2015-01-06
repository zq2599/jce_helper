package cn.net.msg.plugin.response;

import org.json.JSONArray;

import com.tencent.qqlive.jcehelper.util.Utils;

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
		desc = "ChannelDataResponse的data数组中，item对象内的data对象原本是byte数组，此处转换成可视化内容", 
		jces = "", 
		path = "array(data),field(item),field(data)", 
		source = PluginDataSource.response, 
		type = PluginDataType.data_type_array, 
		version = "0.01"
)
public class ChannelDataResponseItem implements IPlugin {

	@Override
	public Object buildObject(Object rawObject) throws Exception {
		String rlt = null;
		if(null!=rawObject && rawObject instanceof JSONArray){
			JSONArray array = (JSONArray)rawObject;
			int len = array.length();
			if(len>0){
				StringBuilder sbud = new StringBuilder();
				sbud.append("【");
				for(int i=0;i<len;i++){
					int val = array.optInt(i);
					sbud.append(val);
					if(i<(len-1)){
						sbud.append(",");
					}
				}
				sbud.append("】");
				rlt = sbud.toString();
			}
		}
		
		return rlt;
	}

}
