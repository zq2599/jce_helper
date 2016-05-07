package cn.net.msg.dao;

import cn.net.msg.model.WenbaUser;

public interface IWenbaUserDao {
	public WenbaUser findByName(String name);
}
