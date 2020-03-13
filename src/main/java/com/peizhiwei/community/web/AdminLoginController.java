package com.peizhiwei.community.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.entity.Admin;
import com.peizhiwei.community.entity.JspResult;
import com.peizhiwei.community.service.AdminLoginService;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {
	Logger logger=LoggerFactory.getLogger(AdminLoginController.class);
	
	@Autowired
	AdminLoginService adminLoginService;
	
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
	
	@RequestMapping("/checklogin")
	@ResponseBody
	private JspResult adminlogin(String username,String password) {
		logger.info("=====start=====");
		long startTime=System.currentTimeMillis();
		Admin admin=new Admin();
		JspResult rs=new JspResult();
		admin=adminLoginService.checkAdminLogin(username, password);
		if(admin!=null) {
			rs.setFlag(true);
			if(admin.getAdminMan()==1) {
				rs.setMsg("/community/admin/superadminback");
			}else {
				rs.setMsg("/community/admin/adminback");
			}
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
}
