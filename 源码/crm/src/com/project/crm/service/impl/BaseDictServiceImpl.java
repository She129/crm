package com.project.crm.service.impl;

import java.util.List;

import com.project.crm.dao.BaseDictDao;
import com.project.crm.domain.Base_dict;
import com.project.crm.service.BaseDictService;

public class BaseDictServiceImpl implements BaseDictService {
	private BaseDictDao baseDictDao;

	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	@Override
	public List<Base_dict> findByTypeCode(String dict_type_code) {
		return baseDictDao.findByTypeCode(dict_type_code);
		
	}

}
