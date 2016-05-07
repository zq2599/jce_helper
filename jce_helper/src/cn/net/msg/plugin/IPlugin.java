package cn.net.msg.plugin;


/**
 * 所有插件必须实现的接口
 * @author willzhao
 *
 */
public interface IPlugin {
	public Object buildObject(Object rawObject) throws Exception;
}
