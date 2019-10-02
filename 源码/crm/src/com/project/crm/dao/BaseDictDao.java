package com.project.crm.dao;

import java.util.List;

import com.project.crm.domain.Base_dict;

public interface BaseDictDao extends BaseDao<Base_dict>{

	List<Base_dict> findByTypeCode(String dict_item_code);

}
