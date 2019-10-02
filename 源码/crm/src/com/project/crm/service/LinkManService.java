package com.project.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.project.crm.domain.LinkMan;
import com.project.crm.domain.PageBean;

public interface LinkManService {

	PageBean<LinkMan> findAll(DetachedCriteria dc, Integer currpage, Integer pageSize);

	void save(LinkMan linkMan);

	LinkMan findById(Long lkm_id);

	void update(LinkMan linkMan);

	void delete(LinkMan linkMan);

}
