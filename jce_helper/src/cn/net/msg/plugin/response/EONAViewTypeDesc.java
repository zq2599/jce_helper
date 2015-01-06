package cn.net.msg.plugin.response;

import cn.net.msg.plugin.IPlugin;
import cn.net.msg.plugin.annotation.PluginDataSource;
import cn.net.msg.plugin.annotation.PluginDataType;
import cn.net.msg.plugin.annotation.PluginInfomation;

import com.tencent.qqlive.jcehelper.test.EONAViewType;

/**
 * 包头命令字转成十六进制和十进制两种表述
 * @author willzhao
 *
 */
@PluginInfomation
(
		creator = "willzhao", 
		desc = "EONAViewType转换成可视化内容", 
		jces = "", 
		path = "array(data),field(item),field(itemType)", 
		source = PluginDataSource.response, 
		type = PluginDataType.data_type_int, 
		version = "0.01"
)
public class EONAViewTypeDesc implements IPlugin {

	@Override
	public Object buildObject(Object rawObject) throws Exception {
		String rlt = null;
		if(null!=rawObject && rawObject instanceof Integer){
			EONAViewType viewTypeObj = EONAViewType.convert(((Integer)rawObject).intValue());
			String simpleClassName = viewTypeObj.toString().substring("Enum".length());
			rlt = simpleClassName + "(" + rawObject + ")";
		}
		
		return rlt;
	}

}
