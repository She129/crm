package com.project.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.project.crm.domain.PageBean;
import com.project.crm.domain.SaleVisit;

public interface SaleVisitService {

	PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void save(SaleVisit saleVisit);



	void delete(SaleVisit saleVisit);

	void update(SaleVisit saleVisit);

	SaleVisit findbyId(String visit_id);

}
