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
	 * 日期格式，在每一个日期实体上添加一个注解，再在这里添加一下语句，这样可以保证后台和页面间交互的日期格式是标准的，不需要再进行格式化
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	/**
	 * 获取所有管理员信息
	 * @return
	 */
	@RequestMapping("/getalladmininfo")
	@ResponseBody
	public List<Admin> getalladmininfo(){
		List<Admin> listadmin = adminmanageservice.getalladmininfo();
		return listadmin;
	}
	/**
	 * 修改管理员信息
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
				rs.setMsg("修改成功！");
			}else {
				rs.setFlag(false);
				rs.setMsg("您输入的编号已存在，请重新输入！");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
	/**
	 * 新增管理员
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
				rs.setMsg("您输入的手机号已存在，请重新输入！");
			}else {
				admin.setAdminName(adminName);
				admin.setAdminSex(adminSex);
				//获取数据库中管理员编号最大的一个
				String maxadminnumberofstring =	adminmanageservice.selectmaxadminnumber();
				//将得到的字符串编号转成数字
				int maxadminnumberofint = Integer.parseInt(maxadminnumberofstring);
				maxadminnumberofint++;//自增一次
				//将自增后的int型的编号转成String，形式如"001"
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
				rs.setMsg("添加成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return rs;
	}
	/**
	 * 删除管理员
	 * @param adminId
	 * @return
	 */
	@RequestMapping("/deleteadminmanageinfo")
	@ResponseBody
	public JspResult deleteadminmanageinfo(int adminId) {
		JspResult rs = new JspResult();
		adminmanageservice.deleteadmininfo(adminId);
		rs.setFlag(true);
		rs.setMsg("删除成功！");
		return rs;
	}
	/**
	 * 批量删除管理员
	 * @param listadminId
	 * @return
	 */
	@RequestMapping("/checkdelete")
	@ResponseBody
	public JspResult checkdelete(@RequestBody int[] listadminId) {
		JspResult rs = new JspResult();
		adminmanageservice.checkdelete(listadminId);
		rs.setFlag(true);
		rs.setMsg("已全部删除！");
		return rs;
	}
}
