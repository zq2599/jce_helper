package cn.net.msg.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.SessionFactory;

import cn.net.msg.dao.IJecRecordDao;
import cn.net.msg.model.JceRecord;

import com.tencent.qqlive.jcehelper.bean.CommonParamBean;
import com.tencent.qqlive.jcehelper.bean.IdentifyInfo;
import com.tencent.qqlive.jcehelper.buffer.CoreBuffer;
import com.tencent.qqlive.jcehelper.util.EmojiFilter;
import com.tencent.qqlive.jcehelper.util.Log;
import com.tencent.qqlive.jcehelper.util.Utils;

public class PostJceRecordAction extends BaseParamAction {
	private static final long serialVersionUID = 6704914212966434580L;
	private final static String TAG = "PostJceRecordAction";

	private final static int[] EMOJI_FILTER_CMDS = {0xe89a};
	private final static Set<Integer> EMOJI_FILTER_SET = new HashSet<Integer>();
	
	static{
		for(int cmd : EMOJI_FILTER_CMDS){
			EMOJI_FILTER_SET.add(cmd);
		}
	}
	
	@Resource(name="jceRecordDao")
	IJecRecordDao jceRecordDao;

	@JSON(serialize = false)
	public IJecRecordDao getJceRecordDao() {
		return jceRecordDao;
	}

	public void setJceRecordDao(IJecRecordDao jceRecordDao) {
		this.jceRecordDao = jceRecordDao;
	}
	
	/**
	 * 创建业务主键
	 * 
	 * @param cpb
	 * @return
	 */
	private static String buildeBusinessId(String guid, long time) {
		return guid + "_" + time;
	}

	private JceRecord buildEntity(CommonParamBean cpb) {
		if (null == cpb) {
			Log.e(TAG, "invalid cpb");
			return null;
		}
		IdentifyInfo identifyInfo = Utils.buildIdentify(cpb);
		JceRecord entity = new JceRecord();

		long time = System.currentTimeMillis();
		entity.setBusinessId(buildeBusinessId(identifyInfo.getGuid(), time));
		entity.setReceiveTime(time);
		entity.setGuid(identifyInfo.getGuid());
		entity.setCmd(Utils.optInt(cpb.getParam7(), 0));

		entity.setParam0(cpb.getParam0());
		entity.setParam1(cpb.getParam1());
		entity.setParam2(cpb.getParam2());
		entity.setParam3(cpb.getParam3());
		entity.setParam4(cpb.getParam4());
		entity.setParam5(cpb.getParam5());
		
		//类似评论这样的协议的响应中可能有emoji表情，mysql中无法入库，所以此处要做过滤
		String responseContent = cpb.getParam6();
		if(isNeedEmojiFilter(entity.getCmd())){
			Log.i(TAG, "当前协议的响应内容需要做emoji过滤");
			responseContent = EmojiFilter.filterEmoji(responseContent);
		}
		
		entity.setParam6(responseContent);
		entity.setParam7(cpb.getParam7());
		entity.setParam8(cpb.getParam8());
		entity.setParam9(cpb.getParam9());
		/*
		entity.setParam10(cpb.getParam10());
		entity.setParam11(cpb.getParam11());
		entity.setParam12(cpb.getParam12());
		entity.setParam13(cpb.getParam13());
		entity.setParam14(cpb.getParam14());
		entity.setParam15(cpb.getParam15());
		*/
		
		return entity;
	}
	
	
	private static boolean isNeedEmojiFilter(int cmd){
		return EMOJI_FILTER_SET.contains(cmd);
	}
	
	
	@Override
	protected void doBusiness(final CommonParamBean commonParamBean) {
		long startTime = System.currentTimeMillis();
		Log.i(TAG, "start doBusiness : " + commonParamBean);
		JceRecord entity = buildEntity(commonParamBean);
		jceRecordDao.insertJceRecord(entity);
		//4,6,8分别是请求包体,响应包体，请求包头，数据量太大，所以在放入map之前先清除掉
		commonParamBean.setParam4(null);
		commonParamBean.setParam6(null);
		commonParamBean.setParam8(null);
		//数据库记录的id保存在param4字段中
		commonParamBean.setParam4(String.valueOf(entity.getId()));
		//存入任务队列
		new Thread(new Runnable() {
			@Override
			public void run() {
				CoreBuffer.getInstance().put(commonParamBean);
			}
		}).start();
		result = successBean;
		Log.i(TAG, "上报数据处理成功，耗时" + (System.currentTimeMillis()-startTime) + "ms");
	}

	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
