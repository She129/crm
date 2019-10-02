package com.project.crm.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.project.crm.domain.Customer;
import com.project.crm.domain.PageBean;
import com.project.crm.service.CustomerService;
import com.project.crm.utils.UploadUtils;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private static final long serialVersionUID = 1L;
	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	private Customer customer = new Customer();
	private Integer currPage = 1;
	private Integer pageSize = 3;

	public void setPageSize(Integer pageSize) {
		if (pageSize == null)
			pageSize = 3;
		this.pageSize = pageSize;
	}

	public void setCurrPage(Integer currPage) {
		if (currPage == null)
			currPage = 1;
		this.currPage = currPage;
	}

	private String uploadFileName;
	private File upload;
	private String uploadContentType;

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	@Override
	public Customer getModel() {
		return customer;
	}

	public String saveUI() {
		return "saveUI";
	}

	public String save() throws IOException {
		if (upload != null) {
			String path = "E:/upload";
			String uUidFileName = UploadUtils.getUUidFileName(uploadFileName);
			String realPath = UploadUtils.getPath(uploadFileName);
			String url = path + realPath;
			File file = new File(url);
			if (!file.exists())
				file.mkdirs();
			File dictFile = new File(url + "/" + uUidFileName);
			FileUtils.copyFile(upload, dictFile);
			customer.setCust_image(url + "/" + uUidFileName);
		}
		customerService.save(customer);
		return "saveSuccess";
	}

	public String findAll() {
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
		if (customer.getCust_name() != null)
			dc.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
		if (customer.getBaseDictSource() != null && customer.getBaseDictSource().getDict_id() != null)
			if (!"".equals(customer.getBaseDictSource().getDict_id()))
				dc.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
		if (customer.getBaseDictIndustry() != null && customer.getBaseDictIndustry().getDict_id() != null)
			if (!"".equals(customer.getBaseDictIndustry().getDict_id()))
				dc.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
		if (customer.getBaseDictLevel() != null && customer.getBaseDictLevel().getDict_id() != null)
			if (!"".equals(customer.getBaseDictLevel().getDict_id()))
				dc.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
		PageBean<Customer> pageBean = customerService.findAll(dc, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}

	public String delete() {
		Customer nowCus = customerService.findById(customer.getCust_id());
		if (null != nowCus.getCust_image()) {
			File file = new File(nowCus.getCust_image());
			if (file.exists())
				file.delete();
		}
		customerService.delete(nowCus);
		return "deleteSuccess";
	}

	public String edit() {
		customer = customerService.findById(customer.getCust_id());
		return "editSuccess";
	}

	public String update() throws IOException {
		if (upload != null) {
			String cus_image = customer.getCust_image();
			if (cus_image != null || !"".equals(cus_image)) {
				File file = new File(cus_image);
				if (file.exists())
					file.delete();
			}
			String path = "E:/upload";
			String uuidFileName = UploadUtils.getUUidFileName(uploadFileName);
			String realPath = UploadUtils.getPath(uploadFileName);
			String url = path + realPath;
			File file2 = new File(url);
			if (!file2.exists())
				file2.mkdirs();
			File dictFile = new File(url + "/" + uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			customer.setCust_image(url + "/" + uuidFileName);
		}
		customerService.update(customer);
		return "updateSuccess";
	}
	public String findAllCustomer() throws IOException {
		List<Customer> list=customerService.findAll();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[] {"linkMans","baseDictSource","baseDictIndustry","baseDictLevel"});
		JSONArray jsonArray=JSONArray.fromObject(list, jsonConfig);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		//list转josn
		return NONE;
	}

}
