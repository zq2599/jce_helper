package cn.net.msg.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import cn.net.msg.dao.IWenbaUserDao;
import cn.net.msg.model.WenbaUser;
@Component("wenbaUserDao")
public class WenbaUserDaoImpl implements IWenbaUserDao {
	@Resource(name="hibernateTemplate")
	private HibernateTemplate template;

	@SuppressWarnings("unchecked")
	@Override
	public WenbaUser findByName(String name) {
		List<WenbaUser> list=template.find("from WenbaUser u where u.name=?", name);  
		return (null!=list && !list.isEmpty()) ? list.get(0) : null;
	}
}
