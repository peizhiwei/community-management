package com.peizhiwei.community.interceptor.owner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 业主登录验证
 * @author PEIZHIWEI
 *
 */
public class OwnerLoginInterceptor implements HandlerInterceptor {
	/**
	 * 主要做事前拦截，即管理员操作发生前，改写preHandle里的逻辑，进行拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//从session中获取用户信息
		Object owner = request.getSession().getAttribute("owner");
		if(owner!=null) {
			return true;
		}else {
			//拦截后进入登录页面
			response.sendRedirect(request.getContextPath()+"/owner/ownerlogin");
			return false;
		}
	}
}
