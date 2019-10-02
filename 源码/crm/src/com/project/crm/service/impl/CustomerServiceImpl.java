package com.project.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.project.crm.dao.CustomerDao;
import com.project.crm.domain.Customer;
import com.project.crm.domain.PageBean;
import com.project.crm.service.CustomerService;

@Transactional
public class CustomerServiceImpl implements CustomerService {
	private CustomerDao customerDao;

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	public PageBean<Customer> findAll(DetachedCriteria dc, Integer currPage, Integer pageSize) {
		PageBean<Customer> pageBean = new PageBean<>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		Integer totalCount = customerDao.findCount(dc);
		pageBean.setTotalCount(totalCount);
		double doubleValue = totalCount.doubleValue();
		Double num = Math.ceil(doubleValue / pageSize);
		pageBean.setTotalPage(num.intValue());
		Integer begin=(currPage-1)*pageSize;
		List<Customer> list=customerDao.findByPage(dc,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}

	@Override
	public void delete(Customer nowCus) {
		customerDao.delete(nowCus);
	}

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

}
