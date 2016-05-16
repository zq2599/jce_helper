package cn.net.msg.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import cn.net.msg.dao.IWenbaQuestionDao;
import cn.net.msg.model.WenbaQuestion;
@Component("wenbaQuestionDao")
public class WenbaQuestionDaoImpl implements IWenbaQuestionDao {
	@Resource(name="hibernateTemplate")
	private HibernateTemplate template;

	@SuppressWarnings("unchecked")
	@Override
	public List<WenbaQuestion> findByCreator(long creator) {
		List<WenbaQuestion> list=template.find("from WenbaQuestion u where u.creatorId=?", creator);  
		return list;
	}

	@Override
	public void insert(WenbaQuestion wenbaQuestion) {
		template.save(wenbaQuestion);
	}
}
