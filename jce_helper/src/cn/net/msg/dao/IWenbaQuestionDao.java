package cn.net.msg.dao;

import java.util.List;

import cn.net.msg.model.WenbaQuestion;

public interface IWenbaQuestionDao {
	List<WenbaQuestion> findByCreator(long creator);
}
