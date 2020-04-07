package com.peizhiwei.community.owner.web;

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

import com.peizhiwei.community.admin.entity.HouseOwner;
import com.peizhiwei.community.admin.entity.JspResult;
import com.peizhiwei.community.owner.service.OwnerSelfMessageService;

@Controller
@RequestMapping("/ownerselfmessage")
public class OwnerSelfMessageController {
	@Autowired
	OwnerSelfMessageService ownerselfmessageservice;
	
	/**
	 * 日期格式，在每一个日期实体上添加一个注解，再在这里添加一下语句，这样可以保证后台和页面间交互的日期格式是标准的，不需要再进行格式化
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	/**
	 * 获取所有的个人信息
	 * @return
	 */
	@RequestMapping("/getmessageofowner")
	@ResponseBody
	public HouseOwner getmessageofowner(HttpSession session) {
		HouseOwner owner = new HouseOwner();
		Object ownersession = session.getAttribute("owner");
		HouseOwner ownerinfo = (HouseOwner) ownersession;
		int ownerId = ownerinfo.getOwnerId();
		owner = ownerselfmessageservice.getmessageofowner(ownerId);
		return owner;
	}
	/**
	 * 业主修改个人信息
	 * @param ownerSex
	 * @param ownerBirthday
	 * @param ownerIdCard
	 * @param ownerNativePlace
	 * @param ownerWorkPlace
	 * @param session
	 * @return
	 */
	@RequestMapping("/changeownerinfo")
	@ResponseBody
	public JspResult changeownerinfo(
			@RequestParam(value = "ownerName",required = false)String ownerName,
			@RequestParam(value = "ownerSex",required = false)int ownerSex,
			@RequestParam(value = "ownerBirthday",required = false)Date ownerBirthday,
			@RequestParam(value = "ownerIdCard",required = false)String ownerIdCard,
			@RequestParam(value = "ownerNativePlace",required = false)String ownerNativePlace,
			@RequestParam(value = "ownerWorkPlace",required = false)String ownerWorkPlace,HttpSession session) {
		JspResult rs = new JspResult();
		Object ownersession = session.getAttribute("owner");
		HouseOwner owner = (HouseOwner) ownersession;
		try {
			owner.setOwnerName(ownerName);
			owner.setOwnerSex(ownerSex);
			owner.setOwnerBirthday(ownerBirthday);
			owner.setOwnerIdCard(ownerIdCard);
			owner.setOwnerNativePlace(ownerNativePlace);
			owner.setOwnerWorkPlace(ownerWorkPlace);
			ownerselfmessageservice.changeownerinfo(owner);
			rs.setFlag(true);
			rs.setMsg("修改成功！");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
	/**
	 * 修改密码
	 * @param oldPassword
	 * @param newPassword
	 * @param session
	 * @return
	 */
	@RequestMapping("/changepassword")
	@ResponseBody
	public JspResult changepassword(
			@RequestParam(value = "oldPassword",required = false)String oldPassword,
			@RequestParam(value = "newPassword",required = false)String newPassword,
			HttpSession session) {
		JspResult rs = new JspResult();
		Object ownersession = session.getAttribute("owner");
		HouseOwner owner = (HouseOwner) ownersession;
		if(owner.getOwnerPassword().equalsIgnoreCase(oldPassword)) {
			owner.setOwnerPassword(newPassword);
			ownerselfmessageservice.updateownerpassword(owner);
			rs.setFlag(true);
			rs.setMsg("修改成功！");
		}else {
			rs.setFlag(false);
			rs.setMsg("原密码错误！");
		}
		return rs;
	}
	/**
	 * 更换手机号
	 * @param ownerPhone
	 * @param session
	 * @return
	 */
	@RequestMapping("/changephone")
	@ResponseBody
	public JspResult changephone(String ownerPhone,HttpSession session) {
		JspResult rs = new JspResult();
		Object ownersession = session.getAttribute("owner");
		HouseOwner owner = (HouseOwner) ownersession;
		try {
			if(owner.getOwnerPhone().equals(ownerPhone)) {
				rs.setFlag(false);
				rs.setMsg("您输入的手机号与原来的手机号相同，无需更换！");
			}else {
				owner.setOwnerPhone(ownerPhone);
				ownerselfmessageservice.updateownerphone(owner);
				rs.setFlag(true);
				rs.setMsg("修改成功，请重新登录！");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
}
