package com.project.crm.web.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.project.crm.domain.Customer;
import com.project.crm.domain.PageBean;
import com.project.crm.domain.SaleVisit;
import com.project.crm.service.SaleVisitService;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {
	SaleVisit saleVisit = new SaleVisit();

	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}

	private Integer currPage = 1;
	private Integer pageSize = 3;
	private Date visit_end_time;

	public Date getVisit_end_time() {
		return visit_end_time;
	}

	public void setVisit_end_time(Date visit_end_time) {
		this.visit_end_time = visit_end_time;
	}

	public void setCurrPage(Integer currPage) {
		if (currPage == null)
			currPage = 1;
		this.currPage = currPage;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize == null)
			pageSize = 3;
		this.pageSize = pageSize;
	}

	@Resource(name = "saleVisitService")
	private SaleVisitService saleVisitService;

	public String findAll() {
		// 创建离线的条件查询对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
		if (saleVisit.getVisit_time() != null) {
			detachedCriteria.add(Restrictions.ge("visit_time", saleVisit.getVisit_time()));
		}
		if (visit_end_time != null) {
			detachedCriteria.add(Restrictions.le("visit_time", visit_end_time));
		}
		PageBean<SaleVisit> pageBean = saleVisitService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}

	public String saveUI() {
		return "saveUI";

	}

	public String save() {
		saleVisitService.save(saleVisit);
		return "saveSuccess";
	}

	public String edit() {
		saleVisit=saleVisitService. findbyId(saleVisit.getVisit_id());
		return "editSuccess";
	}
	public String update() {
		saleVisitService.update(saleVisit);
		return "updateSuccess";
	}

	public String delete() {
		saleVisitService.delete(saleVisit);
		return "deleteSuccess";
	}
}
