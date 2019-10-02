package com.project.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.project.crm.dao.LinkManDao;
import com.project.crm.domain.LinkMan;
import com.project.crm.domain.PageBean;
import com.project.crm.service.LinkManService;

@Transactional
public class LinkManServiceImpl implements LinkManService {
	private LinkManDao linkManDao;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	@Override
	public PageBean<LinkMan> findAll(DetachedCriteria dc, Integer currpage, Integer pageSize) {
		PageBean<LinkMan> pageBean = new PageBean<>();
		pageBean.setCurrPage(currpage);
		pageBean.setPageSize(pageSize);
		Integer totalCount = linkManDao.findCount(dc);
		pageBean.setTotalCount(totalCount);
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		Integer begin = (currpage - 1) * pageSize;
		List<LinkMan> list = linkManDao.findByPage(dc, begin, pageSize);
		pageBean.setList(list);

		return pageBean;
	}

	@Override
	public void save(LinkMan linkMan) {
		linkManDao.save(linkMan);
	}

	@Override
	public LinkMan findById(Long lkm_id) {
		return linkManDao.findById(lkm_id);
	}

	@Override
	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}

	@Override
	public void delete(LinkMan linkMan) {
		linkManDao.delete(linkMan);
	}

}
