
package com.project.crm.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.project.crm.dao.BaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	Class clazz;

	// 方法1.构造方法传入class
	/**
	 * 方法二：反射获得实际类型
	 */

	public BaseDaoImpl() {
		clazz = this.getClass();// 实际被调用的class
		Type type = clazz.getGenericSuperclass();
		ParameterizedType pType = (ParameterizedType) type;
		Type[] types = pType.getActualTypeArguments();
		this.clazz = (Class) types[0];

	}

	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public T findById(Serializable id) {
		// TODO Auto-generated method stub
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from " + clazz.getSimpleName());
	}

	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if (list.size() > 0)
			return list.get(0).intValue();
		return null;
	}

	@Override
	public List<T> findByPage(DetachedCriteria dc, Integer begin, Integer pageSize) {
		dc.setProjection(null);
		return (List<T>) this.getHibernateTemplate().findByCriteria(dc, begin, pageSize);
	}

}
