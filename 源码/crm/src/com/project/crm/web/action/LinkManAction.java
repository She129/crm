package com.project.crm.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.project.crm.domain.Customer;
import com.project.crm.domain.LinkMan;
import com.project.crm.domain.PageBean;
import com.project.crm.service.CustomerService;
import com.project.crm.service.LinkManService;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
	private LinkMan linkMan = new LinkMan();
	private LinkManService linkManService;
	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}

	@Override
	public LinkMan getModel() {
		return linkMan;
	}

	private Integer currpage = 1;
	private Integer pageSize = 3;

	public void setCurrpage(Integer currpage) {
		if (currpage == null)
			currpage = 1;
		this.currpage = currpage;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize == null)
			pageSize = 3;
		this.pageSize = pageSize;
	}

	public String findAll() {
		DetachedCriteria dc = DetachedCriteria.forClass(LinkMan.class);
		if (linkMan.getLkm_name() != null && !"".equals(linkMan.getLkm_name())) {
			dc.add(Restrictions.ilike("lkm_name", "%" + linkMan.getLkm_name() + "%"));
		}
		if (linkMan.getLkm_gender() != null && !"".equals(linkMan.getLkm_gender())) {
			dc.add(Restrictions.ilike("lkm_gender", "%" + linkMan.getLkm_gender() + "%"));
		}
		PageBean<LinkMan> pageBean = linkManService.findAll(dc, currpage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);

		return "findAll";
	}

	public String saveUI() {
		List<Customer> list = customerService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);

		return "saveUI";
	}

	public String save() {
		linkManService.save(linkMan);
		return "saveSuccess";
	}

	public String edit() {
		List<Customer> list = customerService.findAll();
		linkMan = linkManService.findById(linkMan.getLkm_id());
		ActionContext.getContext().getValueStack().set("list", list);
		ActionContext.getContext().getValueStack().push(linkMan);
		return "editSuccess";
	}

	public String update() {
		linkManService.update(linkMan);
		return "updateSuccess";
	}

	public String delete() {
		linkMan = linkManService.findById(linkMan.getLkm_id());
		linkManService.delete(linkMan);
		return "deleteSuccess";
	}
}
