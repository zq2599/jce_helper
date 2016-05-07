package cn.net.msg.plugin.header;

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
		desc = "包头的token类型的描述", 
		jces = "", 
		path = "array(token),field(TokenKeyType)", 
		source = PluginDataSource.header, 
		type = PluginDataType.data_type_int, 
		version = "0.01"
)
public class HeadTokenKeyTypeDesc implements IPlugin {
	public static final byte TokenKeyType_SKEY	= 1;	//表示：TokenValue为Skey
	public static final byte TokenKeyType_LSKEY = 7;	//表示：视频弱登录态，和1定义相同， TokenValue为弱登陆他LSkey
	public static final byte TokenKeyType_Circle = 9;	//标识：视频圈登录态，视频内部使用的登录态，TokenValue为视频vuserkey
	public static final byte TokenKeyType_WX	= 100;	//标识， 微信票据，sTokenUin表示openid, TokenValue表示accesstoken

	@Override
	public Object buildObject(Object rawObject) throws Exception {
		String rlt = null;
		if(null!=rawObject && rawObject instanceof Integer){
			int type = (Integer)rawObject;
			switch(type){
			case TokenKeyType_SKEY:
				rlt = "Skey";
				break;
			case TokenKeyType_LSKEY:
				rlt = "视频弱登录态";
				break;
			case TokenKeyType_Circle:
				rlt = "视频圈登录态";
				break;
			case TokenKeyType_WX:
				rlt = "微信票据";
				break;
			default:
				rlt = "未知类型";
			}
			
			rlt = rlt + "(" + type + ")";
		}
		
		return rlt;
	}

}
