package com.peizhiwei.community.interceptor.owner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * ҵ����¼��֤
 * @author PEIZHIWEI
 *
 */
public class OwnerLoginInterceptor implements HandlerInterceptor {
	/**
	 * ��Ҫ����ǰ���أ�������Ա��������ǰ����дpreHandle����߼�����������
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//��session�л�ȡ�û���Ϣ
		Object owner = request.getSession().getAttribute("owner");
		if(owner!=null) {
			return true;
		}else {
			//���غ�����¼ҳ��
			response.sendRedirect(request.getContextPath()+"/owner/ownerlogin");
			return false;
		}
	}
}
