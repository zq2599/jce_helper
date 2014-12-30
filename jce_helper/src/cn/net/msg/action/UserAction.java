package cn.net.msg.action;

import java.util.List;

import javax.annotation.Resource;

import cn.net.msg.model.User;
import cn.net.msg.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
/*
 * 处理用户操作的Action类
 */
public class UserAction extends ActionSupport{
	javax.servlet.Filter jFilter = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = -7574227471454774126L;
	private User user;
	private List<User> list;
	@Resource(name="userService")
	UserService service;
	
	public String inseart() {
		service.insertUser(user);
		return "success";
	}

	public String delete(){
		service.deleteById(user.getId());
		return "success";
	}
	
	public String  findByName(){
		setList(service.findByName(user.getName()));
		return "success";
	}
	
	public String update(){
		service.updateUser(user);
		return "success";
	}
	
	public String findAll(){
		setList(service.findAll());
		return "success";
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}
	/*
	 * 校验插入数据的操作
	 */
	public void validateInseart(){
		if(null==user.getName()||"".equals(user.getName().trim())) 
			this.addFieldError("name", "name can`t be null");
		else if(16<user.getName().trim().length()) 
			this.addFieldError("name", "name is limited in 16 characters");
		
		if(0>user.getAge()||150<user.getAge())
			this.addFieldError("age", "age is limited between 0~150");
		
		if(null==user.getSex()||((!user.getSex().equals("man"))&&(!user.getSex().equals("woman"))))
			this.addFieldError("sex", "value of sex is unvalid");
	}
	/*
	 * 校验更新数据的操作
	 */
	public void validateUpdate(){
		if(100000>user.getId())
			this.addFieldError("id", "value of id is unvalid");
		
		if(null==user.getName()||"".equals(user.getName().trim())) 
			this.addFieldError("name", "name can`t be null");
		else if(16<user.getName().trim().length()) 
			this.addFieldError("name", "name is limited in 16 characters");
		
		if(0>user.getAge()||150<user.getAge())
			this.addFieldError("age", "age is limited between 0~150");
		
		if(null==user.getSex()||((!user.getSex().equals("man"))&&(!user.getSex().equals("woman"))))
			this.addFieldError("sex", "value of sex is unvalid");
	}
	/*
	 * 校验删除数据的操作
	 */
	public void validateDelete(){
		if(100000>user.getId())
			this.addFieldError("id", "value of id is unvalid");
	}
	public void validateFindByName(){
		if(null==user.getName()||"".equals(user.getName().trim())) 
			this.addFieldError("name", "name can`t be null");
		else if(16<user.getName().trim().length()) 
			this.addFieldError("name", "name is limited in 16 characters");
	}
}
