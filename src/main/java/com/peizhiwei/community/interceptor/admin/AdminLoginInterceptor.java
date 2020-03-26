package com.peizhiwei.community.interceptor.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 系统管理员登录验证
 * @author PEIZHIWEI
 *
 */
public class AdminLoginInterceptor implements HandlerInterceptor {
	/**
	 * 主要做事前拦截，即管理员操作发生前，改写preHandle里的逻辑，进行拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//从session中取出用户信息
		Object admin = request.getSession().getAttribute("admin");
		if(admin!=null) {
			return true;
		}else {
			//拦截后进入登录页面
			response.sendRedirect(request.getContextPath()+"/admin/adminlogin");
			return false;
		}
	}
}
