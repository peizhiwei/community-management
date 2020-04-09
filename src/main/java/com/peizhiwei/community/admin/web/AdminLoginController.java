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
	 * 日期格式，在每一个日期实体上添加一个注解，再在这里添加一下语句，这样可以保证后台和页面间交互的日期格式是标准的，不需要再进行格式化
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
	 * 运行程序时，查询数据库中是否有管理员信息，如果没有，则在数据库中添加一位默认的超级管理员
	 * @return
	 */
	@RequestMapping("/checkadmin")
	@ResponseBody
	public JspResult checkadmin() {
		JspResult rs = new JspResult();
		if(adminLoginService.checkadminexit()==true) {
			rs.setFlag(false);
			rs.setMsg("数据库中有管理员");
		}else {
			Admin admin = new Admin();
			admin.setAdminName("超管");
			admin.setAdminSex(1);
			admin.setAdminNumber("001");
			admin.setAdminPhone("001");
			admin.setAdminEntryTime(new Date());
			admin.setAdminNativePlace("江苏");
			admin.setAdminIdCard("001");
			admin.setAdminBirthday(new Date());
			admin.setAdminPassword("000000");
			admin.setAdminMan(1);
			adminmanageservice.insertadmininfo(admin);
			rs.setFlag(true);
			rs.setMsg("欢迎超级管理员，首次登陆，请及时更改手机号和密码");
		}
		return rs;
	}
	
	/**
	 * 验证登录
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
			rs.setMsg("用户名或密码错误");
			rs.setFlag(false);
		}
		logger.error("test error");
		long endTime=System.currentTimeMillis();
		logger.debug("constTime:[{}ms]",endTime - startTime);
		logger.info("=====end=====");
		return rs;
	}
	/**
	 * 获取管理员session
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
	 * 退出
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
