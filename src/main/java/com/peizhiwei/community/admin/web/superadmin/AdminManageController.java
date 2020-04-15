package com.peizhiwei.community.admin.web.superadmin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.admin.entity.Admin;
import com.peizhiwei.community.admin.entity.JspResult;
import com.peizhiwei.community.admin.service.AdminManageService;
import com.peizhiwei.community.admin.service.AdminSelfMessageService;

@Controller
@RequestMapping("adminmanage")
public class AdminManageController {
	@Autowired
	AdminManageService adminmanageservice;
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
	 * ��ȡ���й���Ա��Ϣ
	 * @return
	 */
	@RequestMapping("/getalladmininfo")
	@ResponseBody
	public List<Admin> getalladmininfo(){
		List<Admin> listadmin = adminmanageservice.getalladmininfo();
		return listadmin;
	}
	/**
	 * �޸Ĺ���Ա��Ϣ
	 * @param adminId
	 * @param adminName
	 * @param adminSex
	 * @param adminNumber
	 * @param adminPhone
	 * @param adminBirthday
	 * @param adminIdCard
	 * @param adminEntryTime
	 * @param adminNativePlace
	 * @return
	 */
	@RequestMapping("/updateadminmanageinfo")
	@ResponseBody
	public JspResult updateadminmanageinfo(
			@RequestParam(value = "adminId",required = false)Integer adminId,
			@RequestParam(value = "adminName",required = false)String adminName,
			@RequestParam(value = "adminSex",required = false)int adminSex,
			@RequestParam(value = "adminNumber",required = false)String adminNumber,
			@RequestParam(value = "oldadminNumber",required = false)String oldadminNumber,
			@RequestParam(value = "adminBirthday",required = false)Date adminBirthday,
			@RequestParam(value = "adminIdCard",required = false)String adminIdCard,
			@RequestParam(value = "adminEntryTime",required = false)Date adminEntryTime,
			@RequestParam(value = "adminNativePlace",required = false)String adminNativePlace) {
		JspResult rs = new JspResult();
		Admin admin = new Admin();
		try {
			if(adminmanageservice.selectnumberofadmin(adminNumber)==false||oldadminNumber.equals(adminNumber)) {
				admin.setAdminId(adminId);
				admin.setAdminName(adminName);
				admin.setAdminSex(adminSex);
				admin.setAdminNumber(adminNumber);
				admin.setAdminBirthday(adminBirthday);
				admin.setAdminIdCard(adminIdCard);
				admin.setAdminEntryTime(adminEntryTime);
				admin.setAdminNativePlace(adminNativePlace);
				adminmanageservice.updateadmininfo(admin);
				rs.setFlag(true);
				rs.setMsg("�޸ĳɹ���");
			}else {
				rs.setFlag(false);
				rs.setMsg("������ı���Ѵ��ڣ����������룡");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
	/**
	 * ��������Ա
	 * @param adminName
	 * @param adminSex
	 * @param adminPhone
	 * @param adminBirthday
	 * @param adminIdCard
	 * @param adminEntryTime
	 * @param adminNativeplace
	 * @return
	 */
	@RequestMapping("/addadminmanageinfo")
	@ResponseBody
	public JspResult addadminmanageinfo(
			@RequestParam(value = "adminName",required = false)String adminName,
			@RequestParam(value = "adminSex",required = false)int adminSex,
			@RequestParam(value = "adminPhone",required = false)String adminPhone,
			@RequestParam(value = "adminBirthday",required = false)Date adminBirthday,
			@RequestParam(value = "adminIdCard",required = false)String adminIdCard,
			@RequestParam(value = "adminEntryTime",required = false)Date adminEntryTime,
			@RequestParam(value = "adminNativePlace",required = false)String adminNativePlace) {
		JspResult rs = new JspResult();
		Admin admin = new Admin();
		try {
			if(adminselfmessageservice.selectadminphone(adminPhone)) {
				rs.setFlag(false);
				rs.setMsg("��������ֻ����Ѵ��ڣ����������룡");
			}else {
				admin.setAdminName(adminName);
				admin.setAdminSex(adminSex);
				//��ȡ���ݿ��й���Ա�������һ��
				String maxadminnumberofstring =	adminmanageservice.selectmaxadminnumber();
				//���õ����ַ������ת������
				int maxadminnumberofint = Integer.parseInt(maxadminnumberofstring);
				maxadminnumberofint++;//����һ��
				//���������int�͵ı��ת��String����ʽ��"001"
				String adminNumber = String.format("%03d", maxadminnumberofint);
				admin.setAdminNumber(adminNumber);
				admin.setAdminPhone(adminPhone);
				admin.setAdminBirthday(adminBirthday);
				admin.setAdminIdCard(adminIdCard);
				admin.setAdminEntryTime(adminEntryTime);
				admin.setAdminNativePlace(adminNativePlace);
				admin.setAdminPassword("000000");
				admin.setAdminMan(0);
				adminmanageservice.insertadmininfo(admin);
				rs.setFlag(true);
				rs.setMsg("��ӳɹ���");
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return rs;
	}
	/**
	 * ɾ������Ա
	 * @param adminId
	 * @return
	 */
	@RequestMapping("/deleteadminmanageinfo")
	@ResponseBody
	public JspResult deleteadminmanageinfo(int adminId) {
		JspResult rs = new JspResult();
		adminmanageservice.deleteadmininfo(adminId);
		rs.setFlag(true);
		rs.setMsg("ɾ���ɹ���");
		return rs;
	}
	/**
	 * ����ɾ������Ա
	 * @param listadminId
	 * @return
	 */
	@RequestMapping("/checkdelete")
	@ResponseBody
	public JspResult checkdelete(@RequestBody int[] listadminId) {
		JspResult rs = new JspResult();
		adminmanageservice.checkdelete(listadminId);
		rs.setFlag(true);
		rs.setMsg("��ȫ��ɾ����");
		return rs;
	}
}
