package com.project.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.project.crm.domain.Customer;
import com.project.crm.domain.PageBean;

public interface CustomerService {

	void save(Customer customer);

	PageBean<Customer> findAll(DetachedCriteria dc, Integer currPage, Integer pageSize);

	Customer findById(Long cust_id);

	void delete(Customer nowCus);

	void update(Customer customer);

	List<Customer> findAll();

}
