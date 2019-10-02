package com.project.crm.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.project.crm.dao.UserDao;
import com.project.crm.domain.User;

/**
 * 用户管理的DAO的实现类
 * 
 * @author jt
 *
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {



	@Override
	public User login(User user) {
		List<User> list = (List<User>) this.getHibernateTemplate()
				.find("from User where user_code=? and user_password=?", user.getUser_code(), user.getUser_password());
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

}
