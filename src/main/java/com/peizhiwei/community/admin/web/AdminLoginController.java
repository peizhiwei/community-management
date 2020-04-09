package com.peizhiwei.community.admin.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.admin.entity.Admin;
import com.peizhiwei.community.admin.entity.JspResult;
import com.peizhiwei.community.admin.service.AdminLoginService;
import com.peizhiwei.community.admin.service.AdminManageService;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {
	Logger logger=LoggerFactory.getLogger(AdminLoginController.class);
	
	@Autowired
	AdminLoginService adminLoginService;
	@Autowired
	AdminManageService adminmanageservice;
	
	
	/**
	 * ���ڸ�ʽ����ÿһ������ʵ�������һ��ע�⣬�����������һ����䣬�������Ա�֤��̨��ҳ��佻�������ڸ�ʽ�Ǳ�׼�ģ�����Ҫ�ٽ��и�ʽ��
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	@RequestMapping("/adminlogin")
	private String adminlogin() {
		return "/adminlogin";
	}
	@RequestMapping("/adminback")
	private String backindex() {
		return "/adminback";
	}
	@RequestMapping("/superadminback")
	private String superadminindex() {
		return "/superadminback";
	}
	/**
	 * ���г���ʱ����ѯ���ݿ����Ƿ��й���Ա��Ϣ�����û�У��������ݿ������һλĬ�ϵĳ�������Ա
	 * @return
	 */
	@RequestMapping("/checkadmin")
	@ResponseBody
	public JspResult checkadmin() {
		JspResult rs = new JspResult();
		if(adminLoginService.checkadminexit()==true) {
			rs.setFlag(false);
			rs.setMsg("���ݿ����й���Ա");
		}else {
			Admin admin = new Admin();
			admin.setAdminName("����");
			admin.setAdminSex(1);
			admin.setAdminNumber("001");
			admin.setAdminPhone("001");
			admin.setAdminEntryTime(new Date());
			admin.setAdminNativePlace("����");
			admin.setAdminIdCard("001");
			admin.setAdminBirthday(new Date());
			admin.setAdminPassword("000000");
			admin.setAdminMan(1);
			adminmanageservice.insertadmininfo(admin);
			rs.setFlag(true);
			rs.setMsg("��ӭ��������Ա���״ε�½���뼰ʱ�����ֻ��ź�����");
		}
		return rs;
	}
	
	/**
	 * ��֤��¼
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping("/checklogin")
	@ResponseBody
	private JspResult adminlogin(@RequestParam(value = "username",required = false)String username,
								@RequestParam(value = "password",required = false)String password,
								HttpServletRequest request) {
		logger.info("=====start=====");
		long startTime=System.currentTimeMillis();
		Admin admin=new Admin();
		JspResult rs=new JspResult();
		admin=adminLoginService.checkAdminLogin(username, password);
		if(admin!=null) {
			request.getSession().setAttribute("admin",admin);
			rs.setFlag(true);
			rs.setMsg("/community/admin/adminback");
		}else {
			rs.setMsg("�û������������");
			rs.setFlag(false);
		}
		logger.error("test error");
		long endTime=System.currentTimeMillis();
		logger.debug("constTime:[{}ms]",endTime - startTime);
		logger.info("=====end=====");
		return rs;
	}
	/**
	 * ��ȡ����Աsession
	 * @param session
	 * @return
	 */
	@RequestMapping("/getadminsession")
	@ResponseBody
	public Admin getusername(HttpSession session) {
		Object adminsession = session.getAttribute("admin");
		Admin admin = (Admin) adminsession;
		return admin;
	}
	
	/**
	 * �˳�
	 * @param session
	 * @return
	 */
	@RequestMapping("/signout")
	@ResponseBody
	public JspResult signout(HttpSession session) {
		JspResult rs = new JspResult();
		session.setAttribute("admin", null);
		rs.setFlag(true);
		rs.setMsg("/community/admin/adminlogin");
		return rs;
	}
}
