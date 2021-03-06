package cn.net.msg.plugin.header;

import org.json.JSONObject;

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
		desc = "包头命令字转成十六进制和十进制两种表述", 
		jces = "", 
		path = "field(cmdId)", 
		source = PluginDataSource.header, 
		type = PluginDataType.data_type_int, 
		version = "0.01"
)
public class HeadCmdDesc implements IPlugin {

	@Override
	public Object buildObject(Object rawObject) throws Exception {
		String rlt = null;
		if(null!=rawObject && rawObject instanceof Integer){
			int cmd = (Integer)rawObject;
			rlt = String.format("0x%x (%d)",cmd,cmd);
		}
		
		return rlt;
	}

}
