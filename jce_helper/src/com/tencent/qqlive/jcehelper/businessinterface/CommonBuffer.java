package com.tencent.qqlive.jcehelper.businessinterface;

import java.util.List;

import com.tencent.qqlive.jcehelper.bean.CommonParamBean;

public interface CommonBuffer {
	/**
	 * 取出指定大小的数据
	 * @param param
	 * @param size
	 * @return
	 */
	public List<CommonParamBean> getData(CommonParamBean param, int size);
	

	/**
	 * 将数据存入缓冲区
	 * @param param
	 */
	public void put(CommonParamBean param);
}
