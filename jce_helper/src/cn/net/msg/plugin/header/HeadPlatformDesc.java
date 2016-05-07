package cn.net.msg.plugin.header;

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
		desc = "包头platformVersion字段添加标题信息", 
		jces = "", 
		path = "field(qua),field(platformVersion)", 
		source = PluginDataSource.header, 
		type = PluginDataType.data_type_str, 
		version = "0.01"
)
public class HeadPlatformDesc implements IPlugin {

	@Override
	public Object buildObject(Object rawObject) throws Exception {
		String rlt = null;
		StringBuilder sbud = null;
		if(null!=rawObject && rawObject instanceof String){
			String raw = (String)rawObject;
			if(!Utils.isEmpty(raw)){
				String[] array = raw.split(",");
				if(null!=array){
					sbud = new StringBuilder();
					if(array.length>0){
						sbud.append("机型 : " + array[0]);
					}
					
					if(array.length>1){
						sbud.append(", API : " + array[1]);
					}
					
					if(array.length>2){
						sbud.append(", 安卓版本 : " + array[2]);
					}
					
					rlt = sbud.toString();
				}
			}
		}
		
		return rlt;
	}

}
