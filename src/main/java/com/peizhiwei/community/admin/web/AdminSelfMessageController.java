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
	 * ���ڸ�ʽ����ÿһ������ʵ�������һ��ע�⣬�����������һ����䣬�������Ա�֤��̨��ҳ��佻�������ڸ�ʽ�Ǳ�׼�ģ�����Ҫ�ٽ��и�ʽ��
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	/**
	 * ����Ա�޸ĸ�����Ϣ
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
			rs.setMsg("�޸ĳɹ���");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
	/**
	 * �޸�����
	 * @param oldPassword
	 * @param newPassword
	 * @param session
	 * @return
	 */
	@RequestMapping("/updatepassword")
	@ResponseBody
	public JspResult updatepassword(
			@RequestParam(value = "oldPassword",required = false)String oldPassword,
			@RequestParam(value = "newPassword",required = false)String newPassword,
			HttpSession session) {
		JspResult rs = new JspResult();
		Object adminsession = session.getAttribute("admin");
		Admin admin = (Admin) adminsession;
		if(admin.getAdminPassword().equals(oldPassword)) {
			admin.setAdminPassword(newPassword);
			adminselfmessageservice.updateadminpassword(admin);
			rs.setFlag(true);
			rs.setMsg("�޸ĳɹ��������µ�¼��");
		}else {
			rs.setFlag(false);
			rs.setMsg("ԭ�������");
		}
		return rs;
	}
	/**
	 * �����ֻ���
	 * @return
	 */
	@RequestMapping("/changephone")
	@ResponseBody
	public JspResult changephone(String adminPhone,HttpSession session) {
		JspResult rs = new JspResult();
		Object adminsession = session.getAttribute("admin");
		Admin admin = (Admin) adminsession;
		try {
			if(admin.getAdminPhone().equals(adminPhone)) {
				rs.setFlag(false);
				rs.setMsg("��������ֻ�����ԭ�����ֻ�����ͬ�����������");
			}else {
				if(adminselfmessageservice.selectadminphone(adminPhone)==true) {
					rs.setFlag(false);
					rs.setMsg("���ֻ����Ѵ��ڣ����������룡");
				}else {
					admin.setAdminPhone(adminPhone);
					adminselfmessageservice.updateadminphone(admin);
					rs.setFlag(true);
					rs.setMsg("�޸ĳɹ��������µ�¼��");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
}
