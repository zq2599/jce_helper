package cn.net.msg.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;

import cn.net.msg.dao.IJecRecordDao;
import cn.net.msg.dao.IWenbaQuestionDao;
import cn.net.msg.dao.IWenbaUserDao;
import cn.net.msg.model.WenbaQuestion;
import cn.net.msg.model.WenbaUser;

import com.tencent.qqlive.jcehelper.bean.CommonListRltBean;
import com.tencent.qqlive.jcehelper.bean.CommonParamBean;
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
	
	@Resource(name="wenbaUserDao")
	IWenbaUserDao wenbaUserDao;
	
	@Resource(name="wenbaQuestionDao")
	IWenbaQuestionDao wenbaQuestionDao;

	/*
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
	*/
	
	/*
	@Override
	protected void doBusiness(CommonParamBean commonParamBean) {
		Log.i(TAG, "start doBusiness");
		WenbaUser user = wenbaUserDao.findByName(commonParamBean.getParam1());
		String msg = null==user ? "user not exist" : user.getNick();
		List<CommonParamBean> list = new ArrayList<CommonParamBean>();
		CommonListRltBean rlt = new CommonListRltBean();
		rlt.setReturnCode(0);
		rlt.setMsg(msg);
		rlt.setParams(list);
		result = JSONRltBuilder.buildStr(rlt);
	}
	*/
	
	@Override
	protected void doBusiness(CommonParamBean commonParamBean) {
		Log.i(TAG, "start doBusiness");
		WenbaUser user = wenbaUserDao.findByName(commonParamBean.getParam1());
		
		StringBuilder msg = new StringBuilder();
		if(null!=user){
			msg.append("user [" + user.getNick() + "], ");
			long creatorId = user.getId();
			List<WenbaQuestion> questions = wenbaQuestionDao.findByCreator(creatorId);
			if(null!=questions && !questions.isEmpty()){
				msg.append("question size [" + questions.size() + "], ");
				WenbaQuestion question = questions.get(0);
				if(null!=question){
					msg.append("question title [" + question.getQuestionTitle() + "], ");
				}
			}else{
				msg.append("question is empty, ");
			}
		}else{
			msg.append("user is not exist");
		}
		
		List<CommonParamBean> list = new ArrayList<CommonParamBean>();
		CommonListRltBean rlt = new CommonListRltBean();
		rlt.setReturnCode(0);
		rlt.setMsg(msg.toString());
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
