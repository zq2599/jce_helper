package cn.net.msg.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;

import cn.net.msg.dao.IJecRecordDao;
import cn.net.msg.model.JceRecord;
import cn.net.msg.plugin.PluginManager;
import cn.net.msg.plugin.annotation.PluginDataSource;

import com.tencent.qqlive.jcehelper.bean.CommonListRltBean;
import com.tencent.qqlive.jcehelper.bean.CommonParamBean;
import com.tencent.qqlive.jcehelper.buffer.CoreBuffer;
import com.tencent.qqlive.jcehelper.util.JSONFormat;
import com.tencent.qqlive.jcehelper.util.JSONRltBuilder;
import com.tencent.qqlive.jcehelper.util.Log;
import com.tencent.qqlive.jcehelper.util.Utils;

public class ReadSingleJceRecordAction extends BaseParamAction {
	private static final long serialVersionUID = 6704914212966434580L;
	private final static String TAG = "PostJceRecordAction";

	@Resource(name="jceRecordDao")
	IJecRecordDao jceRecordDao;

	@JSON(serialize = false)
	public IJecRecordDao getJceRecordDao() {
		return jceRecordDao;
	}

	public void setJceRecordDao(IJecRecordDao jceRecordDao) {
		this.jceRecordDao = jceRecordDao;
	}
	
	private static CommonParamBean entity2CommonParamBean(JceRecord entity){
		CommonParamBean rlt = null;
		if(null!=entity){
			rlt = new CommonParamBean();
			rlt.setParam0(entity.getParam0());
			rlt.setParam1(entity.getParam1());
			rlt.setParam2(entity.getParam2());
			rlt.setParam3(entity.getParam3());
			rlt.setParam4(entity.getParam4());
			rlt.setParam5(entity.getParam5());
			rlt.setParam6(entity.getParam6());
			rlt.setParam7(entity.getParam7());
			rlt.setParam8(entity.getParam8());
			rlt.setParam9(entity.getParam9());
		}
		
		return rlt;
	}

	@Override
	protected void doBusiness(CommonParamBean commonParamBean) {
		Log.i(TAG, "start doBusiness");
		JceRecord jceRecord = null;
		if(null!=commonParamBean && Utils.isValidLong(commonParamBean.getParam0())){
			jceRecord = jceRecordDao.findById(Long.valueOf(commonParamBean.getParam0()));
		}
		
		CommonListRltBean rlt = new CommonListRltBean();
		rlt.setReturnCode(0);
		rlt.setMsg("ReadJceRecordAction operate success");
		//如果从数据库中查到了数据，就转成CommonParamBean
		if(null!=jceRecord){
			List<CommonParamBean> list = new ArrayList<CommonParamBean>();
			CommonParamBean resultCpb = entity2CommonParamBean(jceRecord);
			//按照页面的要求来设置数据内容
			doConvert(resultCpb);
			list.add(resultCpb);
			rlt.setParams(list);
		}
		
		result = JSONRltBuilder.buildStr(rlt);
	}

	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	private static String buildCommunicateDesc(String raw){
		String[] array = CoreBuffer.getDescInfo(raw);
		StringBuilder sbud = new StringBuilder();
		sbud.append("时间 : " + Utils.formatDateTime(Long.valueOf(array[0])));
		sbud.append(JSONFormat.STR_SPACE + "类信息 : " + array[1]);
		return sbud.toString();
	}
	
	private void doConvert(CommonParamBean c){
		if(null==c){
			Log.i(TAG, "invalide cpb for convert");
			return;
		}
		PluginManager pm = PluginManager.getInstance();
		
		if(Utils.isValid(c.getParam3())){
			c.setParam3(buildCommunicateDesc(c.getParam3()));
		}
		if(Utils.isValid(c.getParam5())){
			c.setParam5(buildCommunicateDesc(c.getParam5()));
		}
		
		//param9中放的是gson格式化好的数据，用于用于复制
		c.setParam9(JSONFormat.jsonFormatter(c.getParam4()));
		//param4中放的是gson格式化之后，再把换行和空格换成html标签的数据，用于网页上显示
		String pluginRequest = pm.doConvert(PluginDataSource.request, c.getParam4());
		pluginRequest = JSONFormat.jsonFormatter(pluginRequest);
		c.setParam4(JSONFormat.htmlFormat(pluginRequest));
		
		//param10中放的是gson格式化好的数据，用于用于复制
		c.setParam10(JSONFormat.jsonFormatter(c.getParam6()));
		//param6中放的是gson格式化之后，再把换行和空格换成html标签的数据，用于网页上显示
		String pluginResponse = pm.doConvert(PluginDataSource.response, c.getParam10());
		pluginResponse = JSONFormat.jsonFormatter(pluginResponse);
		c.setParam6(JSONFormat.htmlFormat(pluginResponse));
		
		//param8中是格式化成html格式的json
		String pluginHeader = pm.doConvert(PluginDataSource.header, c.getParam8());
		String formatParam8 = JSONFormat.jsonFormatter(pluginHeader);
		//param4中放的是gson格式化之后，再把换行和空格换成html标签的数据，用于网页上显示
		c.setParam8(JSONFormat.htmlFormat(formatParam8));
	}

}
