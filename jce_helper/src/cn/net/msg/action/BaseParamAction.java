package cn.net.msg.action;

import com.opensymphony.xwork2.ActionSupport;
import com.tencent.qqlive.jcehelper.bean.CommonListRltBean;
import com.tencent.qqlive.jcehelper.bean.CommonParamBean;
import com.tencent.qqlive.jcehelper.util.JSONRltBuilder;
import com.tencent.qqlive.jcehelper.util.Log;

public abstract class BaseParamAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2208726842484026403L;

	private final static String TAG = "BaseParamAction";
	
	/**
	 * 表示成功的对象
	 */
	protected static String successBean;
	
	static{
		CommonListRltBean bean = new CommonListRltBean();
		bean.setReturnCode(0);
		bean.setMsg("operation success");
		successBean = JSONRltBuilder.buildStr(bean);
		bean = null;
	}

	private String param0;
	private String param1;
	private String param2;
	private String param3;
	private String param4;
	private String param5;
	private String param6;
	private String param7;
	private String param8;
	private String param9;
	private String param10;
	private String param11;
	private String param12;
	private String param13;
	private String param14;
	private String param15;

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public String getParam3() {
		return param3;
	}

	public void setParam3(String param3) {
		this.param3 = param3;
	}

	public String getParam4() {
		return param4;
	}

	public void setParam4(String param4) {
		this.param4 = param4;
	}

	public String getParam5() {
		return param5;
	}

	public void setParam5(String param5) {
		this.param5 = param5;
	}

	public String getParam6() {
		return param6;
	}

	public void setParam6(String param6) {
		this.param6 = param6;
	}

	public String getParam10() {
		return param10;
	}

	public void setParam10(String param10) {
		this.param10 = param10;
	}

	public String getParam11() {
		return param11;
	}

	public void setParam11(String param11) {
		this.param11 = param11;
	}

	public String getParam12() {
		return param12;
	}

	public void setParam12(String param12) {
		this.param12 = param12;
	}

	public String getParam13() {
		return param13;
	}

	public void setParam13(String param13) {
		this.param13 = param13;
	}

	public String getParam14() {
		return param14;
	}

	public void setParam14(String param14) {
		this.param14 = param14;
	}

	public String getParam15() {
		return param15;
	}

	public void setParam15(String param15) {
		this.param15 = param15;
	}

	public String getParam0() {
		return param0;
	}

	public String getParam7() {
		return param7;
	}

	public void setParam7(String param7) {
		this.param7 = param7;
	}

	public String getParam8() {
		return param8;
	}

	public void setParam8(String param8) {
		this.param8 = param8;
	}

	public String getParam9() {
		return param9;
	}

	public void setParam9(String param9) {
		this.param9 = param9;
	}

	public abstract String getResult();

	public void setParam0(String param0) {
		this.param0 = param0;
	}

	/**
	 * @return
	 */
	protected CommonParamBean getBean() {
		CommonParamBean commonParamBean = new CommonParamBean();
		commonParamBean.setParam0(param0);
		commonParamBean.setParam1(param1);
		commonParamBean.setParam2(param2);
		commonParamBean.setParam3(param3);
		commonParamBean.setParam4(param4);
		commonParamBean.setParam5(param5);
		commonParamBean.setParam6(param6);
		commonParamBean.setParam7(param7);
		commonParamBean.setParam8(param8);
		commonParamBean.setParam9(param9);
		commonParamBean.setParam10(param10);
		commonParamBean.setParam11(param11);
		commonParamBean.setParam12(param12);
		commonParamBean.setParam13(param13);
		commonParamBean.setParam14(param14);
		commonParamBean.setParam15(param15);
		Log.i(TAG, "commonParamBean : " + param0);

		return commonParamBean;
	}

	protected abstract void doBusiness(CommonParamBean commonParamBean);

	@Override
	public String execute() throws Exception {
		CommonParamBean commonParamBean = getBean();
		Log.i(TAG, "invoke doBusiness in child class instance : " + commonParamBean);
		doBusiness(commonParamBean);
		Log.i(TAG, "doBusiness result : " + getResult());
		return SUCCESS;
	}
}
