package com.project.crm.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.project.crm.domain.Base_dict;
import com.project.crm.service.BaseDictService;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class BaseDictAction extends ActionSupport implements ModelDriven<Base_dict> {
	private Base_dict base_dict = new Base_dict();
	private BaseDictService baseDictService;

	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}

	@Override
	public Base_dict getModel() {
		return base_dict;
	}

	public String findByTypeCode() throws IOException {

		// 调用业务层查询:
		List<Base_dict> list = baseDictService.findByTypeCode(base_dict.getDict_type_code());
		// 将list转成JSON.---- jsonlib fastjson
		/**
		 * JSONConfig：转JSON的配置对象 JSONArray ：将数组和list集合转成JSON JSONObject：将对象和Map集合转成JSON
		 */
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "dict_sort", "dict_enable", "dict_memo" });
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		System.out.println(jsonArray.toString());
		// 将JSON打印到页面:
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
}
