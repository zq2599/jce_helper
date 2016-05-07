package cn.net.msg.plugin;

import cn.net.msg.plugin.annotation.PluginDataSource;
import cn.net.msg.plugin.annotation.PluginDataType;

/**
 * 保存插件基本信息的bean
 * @author willzhao
 *
 */
public class PluginInfo {
	public Class c;
	public String creator;
	public String desc;
	public String version;
	public PluginDataSource source;
	public PluginDataType type;
	public String jces;
	public String path;
}
