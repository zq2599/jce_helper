package cn.net.msg.dao;

import java.util.List;

import cn.net.msg.model.WenbaAnswer;

public interface IWenbaAnswerDao {
	List<WenbaAnswer> findByQuestionId(long questionId);
}
