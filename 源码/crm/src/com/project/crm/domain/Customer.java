package com.project.crm.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * CREATE TABLE `cst_customer` ( `cust_id` bigint(32) NOT NULL AUTO_INCREMENT
 * COMMENT '客户编号(主键)', `cust_name` varchar(32) NOT NULL COMMENT '客户名称(公司名称)',
 * `cust_source` varchar(32) DEFAULT NULL COMMENT '客户信息来源', `cust_industry`
 * varchar(32) DEFAULT NULL COMMENT '客户所属行业', `cust_level` varchar(32) DEFAULT
 * NULL COMMENT '客户级别', `cust_phone` varchar(64) DEFAULT NULL COMMENT '固定电话',
 * `cust_mobile` varchar(16) DEFAULT NULL COMMENT '移动电话', PRIMARY KEY
 * (`cust_id`) ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
 * 
 * @author SLl
 *
 */
public class Customer {
	private Long cust_id;
	private String cust_name;
//	private String cust_source;
//	private String cust_industry;
//	private String cust_level;
	private Base_dict baseDictSource;
	private Base_dict baseDictIndustry;
	private Base_dict baseDictLevel;
	private Set<LinkMan> linkMans = new HashSet<>();

	public Set<LinkMan> getLinkMans() {
		return linkMans;
	}

	public void setLinkMans(Set<LinkMan> linkMans) {
		this.linkMans = linkMans;
	}

	private String cust_phone;
	private String cust_mobile;
	private String cust_image;

	public String getCust_image() {
		return cust_image;
	}

	public void setCust_image(String cust_image) {
		this.cust_image = cust_image;
	}

	public Base_dict getBaseDictSource() {
		return baseDictSource;
	}

	public void setBaseDictSource(Base_dict baseDictSource) {
		this.baseDictSource = baseDictSource;
	}

	public Base_dict getBaseDictIndustry() {
		return baseDictIndustry;
	}

	public void setBaseDictIndustry(Base_dict baseDictIndustry) {
		this.baseDictIndustry = baseDictIndustry;
	}

	public Base_dict getBaseDictLevel() {
		return baseDictLevel;
	}

	public void setBaseDictLevel(Base_dict baseDictLevel) {
		this.baseDictLevel = baseDictLevel;
	}

	public String getCust_mobile() {
		return cust_mobile;
	}

	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}

	public Long getCust_id() {
		return cust_id;
	}

	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCust_phone() {
		return cust_phone;
	}

	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}

	@Override
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", cust_name=" + cust_name + ", baseDictSource=" + baseDictSource
				+ ", baseDictIndustry=" + baseDictIndustry + ", baseDictLevel=" + baseDictLevel + ", linkMans="
				+ linkMans + ", cust_phone=" + cust_phone + ", cust_mobile=" + cust_mobile + ", cust_image="
				+ cust_image + "]";
	}
	
}
