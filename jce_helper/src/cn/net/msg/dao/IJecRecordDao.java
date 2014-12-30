package cn.net.msg.dao;

import cn.net.msg.model.JceRecord;

public interface IJecRecordDao {
	public void insertJceRecord(JceRecord jceRecord);
	public JceRecord findById(long id);
}
