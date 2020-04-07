package com.peizhiwei.community.admin.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

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
import com.peizhiwei.community.admin.service.AdminSelfMessageService;

@Controller
@RequestMapping("/adminselfmessage")
public class AdminSelfMessageController {
	@Autowired
	AdminSelfMessageService adminselfmessageservice;
	
	/**
	 * 日期格式，在每一个日期实体上添加一个注解，再在这里添加一下语句，这样可以保证后台和页面间交互的日期格式是标准的，不需要再进行格式化
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	/**
	 * 获取个人信息(从session中获取)
	 * @param session
	 * @return
	 */
	@RequestMapping("/getmessageofadmin")
	@ResponseBody
	public Admin getmessageofadmin(HttpSession session) {
		Object adminsession = session.getAttribute("admin");
		Admin admin = new Admin();
		admin = (Admin) adminsession;
		return admin;
	}
	
	/**
	 * 管理员修改个人信息
	 * @return
	 */
	@RequestMapping("/updatemessageofadmin")
	@ResponseBody
	public JspResult updatemessageofadmin(
			@RequestParam(value = "adminName",required = false)String adminName,
			@RequestParam(value = "adminSex",required = false)int adminSex,
			@RequestParam(value = "adminBirthday",required = false)Date adminBirthday,
			@RequestParam(value = "adminIdCard",required = false)String adminIdCard,
			@RequestParam(value = "adminNativePlace",required = false)String adminNativePlace,
			HttpSession session) {
		JspResult rs = new JspResult();
		Object adminsession = session.getAttribute("admin");
		Admin admin = (Admin) adminsession;
		try {
			admin.setAdminName(adminName);
			admin.setAdminSex(adminSex);
			admin.setAdminBirthday(adminBirthday);
			admin.setAdminIdCard(adminIdCard);
			admin.setAdminNativePlace(adminNativePlace);
			adminselfmessageservice.updateadmininfo(admin);
			rs.setFlag(true);
			rs.setMsg("修改成功！");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
}
