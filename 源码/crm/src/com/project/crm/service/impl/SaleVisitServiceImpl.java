package com.project.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.project.crm.dao.SaleVisitDao;
import com.project.crm.domain.PageBean;
import com.project.crm.domain.SaleVisit;
import com.project.crm.service.SaleVisitService;

@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {
	@Resource(name = "saleVisitDao")
	private SaleVisitDao saleVisitDao;

	@Override
	public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<SaleVisit> pageBean = new PageBean<>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		Integer totalCount = saleVisitDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		double dc = totalCount;
		Double num = Math.ceil(dc / pageSize);
		pageBean.setTotalPage(num.intValue());
		Integer begin = (currPage - 1) * pageSize;
		List<SaleVisit> list = saleVisitDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public void save(SaleVisit saleVisit) {
		saleVisitDao.save(saleVisit);
	}

	@Override
	public void delete(SaleVisit saleVisit) {
		saleVisitDao.delete(saleVisit);
	}

	@Override
	public void update(SaleVisit saleVisit) {
		saleVisitDao.update(saleVisit);
	}

	@Override
	public SaleVisit findbyId(String visit_id) {
		return saleVisitDao.findById(visit_id);
	}
}
