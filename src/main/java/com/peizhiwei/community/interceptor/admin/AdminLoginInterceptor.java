package com.peizhiwei.community.interceptor.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * ϵͳ����Ա��¼��֤
 * @author PEIZHIWEI
 *
 */
public class AdminLoginInterceptor implements HandlerInterceptor {
	/**
	 * ��Ҫ����ǰ���أ�������Ա��������ǰ����дpreHandle����߼�����������
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//��session��ȡ���û���Ϣ
		Object admin = request.getSession().getAttribute("admin");
		if(admin!=null) {
			return true;
		}else {
			//���غ�����¼ҳ��
			response.sendRedirect(request.getContextPath()+"/admin/adminlogin");
			return false;
		}
	}
}
