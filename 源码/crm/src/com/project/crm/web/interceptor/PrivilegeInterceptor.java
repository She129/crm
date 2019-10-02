package com.project.crm.web.interceptor;

import org.aopalliance.intercept.Invocation;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.project.crm.domain.User;

public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existuser");
		if (null == user) {
			ActionSupport action = (ActionSupport) arg0.getAction();
			action.addActionError("您还没有登录，没有权限访问！");
			return action.LOGIN;
		} else
			return arg0.invoke();
	}

}
