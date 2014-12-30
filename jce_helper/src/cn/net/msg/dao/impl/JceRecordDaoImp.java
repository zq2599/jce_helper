package cn.net.msg.dao.impl;

import javax.annotation.Resource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import cn.net.msg.dao.IJecRecordDao;
import cn.net.msg.model.JceRecord;

@Component("jceRecordDao")
public class JceRecordDaoImp implements IJecRecordDao {
	@Resource(name = "hibernateTemplate")
	private HibernateTemplate template;

	@Override
	public void insertJceRecord(JceRecord jceRecord) {
		template.save(jceRecord);
	}

	@Override
	public JceRecord findById(long id) {
		return template.get(JceRecord.class, id);
	}
}
