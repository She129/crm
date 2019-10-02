package com.project.crm.dao;

import com.project.crm.domain.User;

/**
 * 用户管理的DAO的接口
 * @author jt
 *
 */
public interface UserDao extends BaseDao<User> {


	User login(User user);

}
