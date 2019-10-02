package com.project.crm.dao.impl;

import java.util.List;

import com.project.crm.dao.BaseDictDao;
import com.project.crm.domain.Base_dict;

public class BaseDictDaoImpl extends BaseDaoImpl<Base_dict> implements BaseDictDao {



	@Override
	public List<Base_dict> findByTypeCode(String dict_type_code) {
		List<?> find = this.getHibernateTemplate().find("from Base_dict where dict_type_code=?", dict_type_code);
		return (List<Base_dict>) find;
	}

}
