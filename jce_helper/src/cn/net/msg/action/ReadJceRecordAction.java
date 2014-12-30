package cn.net.msg.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;

import cn.net.msg.dao.IJecRecordDao;

import com.tencent.qqlive.jcehelper.bean.CommonListRltBean;
import com.tencent.qqlive.jcehelper.bean.CommonParamBean;
import com.tencent.qqlive.jcehelper.buffer.CoreBuffer;
import com.tencent.qqlive.jcehelper.util.JSONRltBuilder;
import com.tencent.qqlive.jcehelper.util.Log;

public class ReadJceRecordAction extends BaseParamAction {
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

	@Override
	protected void doBusiness(CommonParamBean commonParamBean) {
		Log.i(TAG, "start doBusiness");
		List<CommonParamBean> list = CoreBuffer.getInstance().getData(commonParamBean, 0);
		CommonListRltBean rlt = new CommonListRltBean();
		rlt.setReturnCode(0);
		rlt.setMsg("ReadJceRecordAction operate success");
		rlt.setParams(list);
		result = JSONRltBuilder.buildStr(rlt);
	}

	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
