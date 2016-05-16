package cn.net.msg.dao;

import java.util.List;

import cn.net.msg.model.WenbaQuestion;

public interface IWenbaQuestionDao {
	List<WenbaQuestion> findByCreator(long creator);
	
	/**
	 * 向数据库新增一条记录
	 * @param wenbaQuestion
	 */
	void insert(WenbaQuestion wenbaQuestion);
}
