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

public class GetQuestionsByAccountAction extends BaseParamAction {
	private static final long serialVersionUID = 6704914212966434580L;
	private final static String TAG = "GetQuestionsByAccountAction";
	
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
			long creatorId = user.getId();
			List<WenbaQuestion> questions = wenbaQuestionDao.findByCreator(creatorId);
			if(null!=questions && !questions.isEmpty()){
				msg.append("question size [" + questions.size() + "], ");
				for(WenbaQuestion question : questions){
					CommonParamBean bean = new CommonParamBean();
					bean.setParam0(String.valueOf(question.getId()));
					bean.setParam1(String.valueOf(question.getCreatorId()));
					bean.setParam2(String.valueOf(question.getCreateTime()));
					bean.setParam3(String.valueOf(question.getModifyTime()));
					bean.setParam4(str(question.getQuestionTitle()));
					bean.setParam5(str(question.getQuestionContent()));
					bean.setParam6(str(question.getQuestionType()));
					bean.setParam7(str(question.getQuestionStatus()));
					bean.setParam8(String.valueOf(question.getQuestionFinishTime()));
					bean.setParam9(String.valueOf(question.getQuestionPrice()));
					bean.setParam10(str(question.getAnswers()));
					list.add(bean);
				}
			}else{
				msg.append("question is empty, ");
			}
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
