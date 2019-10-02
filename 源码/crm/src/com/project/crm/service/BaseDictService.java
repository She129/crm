package com.project.crm.service;

import java.util.List;

import com.project.crm.domain.Base_dict;

public interface BaseDictService {

	List<Base_dict> findByTypeCode(String dict_item_code);

}
