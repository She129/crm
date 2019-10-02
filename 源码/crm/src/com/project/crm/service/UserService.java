package com.project.crm.service;

import java.util.List;

import com.project.crm.domain.User;

/**
 * 用户管理的Service的接口
 * @author jt
 *
 */
public interface UserService {

	void regist(User user);

	User login(User user);

	List<User> findAll();

}
