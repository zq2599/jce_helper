package cn.net.msg.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.net.msg.constants.WenbaBizConstants;
import cn.net.msg.dao.IWenbaAnswerDao;
import cn.net.msg.dao.IWenbaQuestionDao;
import cn.net.msg.dao.IWenbaUserDao;
import cn.net.msg.model.WenbaQuestion;
import cn.net.msg.model.WenbaUser;

import com.tencent.qqlive.jcehelper.bean.CommonListRltBean;
import com.tencent.qqlive.jcehelper.bean.CommonParamBean;
import com.tencent.qqlive.jcehelper.util.JSONRltBuilder;
import com.tencent.qqlive.jcehelper.util.Log;

public class AddQuestionAction extends BaseParamAction {
	private static final long serialVersionUID = 6704914212966434580L;
	private final static String TAG = AddQuestionAction.class.getSimpleName();
	
	@Resource(name="wenbaUserDao")
	IWenbaUserDao wenbaUserDao;
	
	@Resource(name="wenbaQuestionDao")
	IWenbaQuestionDao wenbaQuestionDao;
	
	@Resource(name="wenbaAnswerDao")
	IWenbaAnswerDao wenbaAnswerDao;

	static String str(String raw){
		return null==raw ? "" : raw;
	}
	
	@Override
	protected void doBusiness(CommonParamBean commonParamBean) {
		Log.i(TAG, "start doBusiness");
		
		int errCode = WenbaBizConstants.RLT_SUCCESS;
		List<CommonParamBean> list = new ArrayList<CommonParamBean>();
		//根据账号找到用户对象
		WenbaUser user = wenbaUserDao.findByName(commonParamBean.getParam1());
		
		StringBuilder msg = new StringBuilder();
		if(null!=user){
			msg.append("user [" + user.getNick() + "], ");
			
			WenbaQuestion q = new WenbaQuestion();
			q.setCreatorId(user.getId());
			q.setCreateTime(System.currentTimeMillis());
			q.setModifyTime(q.getCreateTime());
			q.setQuestionTitle(commonParamBean.getParam4());
			q.setQuestionContent(commonParamBean.getParam5());
			q.setQuestionType(commonParamBean.getParam6());
			q.setQuestionStatus(commonParamBean.getParam7());
			q.setQuestionFinishTime(Long.valueOf(commonParamBean.getParam8()));
			q.setQuestionPrice(Long.valueOf(commonParamBean.getParam9()));
			
			wenbaQuestionDao.insert(q);
			msg.append("insert success, id [" + q.getId() + "], ");
		}else{
			errCode = WenbaBizConstants.RLT_USER_NOT_EXIST;
			msg.append("user is not exist");
		}
		
		CommonListRltBean rlt = new CommonListRltBean();
		
		rlt.setReturnCode(errCode);
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
