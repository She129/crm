package com.project.crm.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {
	public void save(T t);

	public void update(T t);

	public void delete(T t);

	public T findById(Serializable id);

	/**
	 * 查询所有
	 */
	public List<T> findAll();

	public Integer findCount(DetachedCriteria detachedCriteria);

	public List<T> findByPage(DetachedCriteria dc, Integer begin, Integer pageSize);
}
