package com.project.crm.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.project.crm.domain.Customer;
import com.project.crm.domain.User;
import com.project.crm.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * 用户管理的Action的类
 * 
 * @author jt
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
	// 模型驱动使用的对象
	private User user = new User();

	@Override
	public User getModel() {
		return user;
	}

	// 注入Service:
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 用户注册的方法:regist
	 */
	public String regist() {
		userService.regist(user);
		return LOGIN;
	}

	public String login() {
		User existuser = userService.login(user);
		if (existuser == null) {
			this.addActionError("用户名或密码错误!");
		} else {
			ActionContext.getContext().getSession().put("existuser", existuser);
			return SUCCESS;
		}
		return LOGIN;
	}
	public String findAllUser() throws IOException {
		List<User> list = userService.findAll();
		JSONArray jsonArray=JSONArray.fromObject(list);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
}
