package cn.net.msg.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import cn.net.msg.dao.IWenbaAnswerDao;
import cn.net.msg.model.WenbaAnswer;
@Component("wenbaAnswerDao")
public class WenbaAnswerDaoImpl implements IWenbaAnswerDao {
	@Resource(name="hibernateTemplate")
	private HibernateTemplate template;

	@SuppressWarnings("unchecked")
	@Override
	public List<WenbaAnswer> findByQuestionId(long questionId) {
		List<WenbaAnswer> list=template.find("from WenbaAnswer u where u.questionId=?", questionId);  
		return list;
	}
}
