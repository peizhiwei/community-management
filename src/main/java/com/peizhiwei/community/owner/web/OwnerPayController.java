package com.peizhiwei.community.owner.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.admin.entity.HouseOwner;
import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.owner.service.OwnerPayService;

@RequestMapping("/ownerpay")
@Controller
public class OwnerPayController {
	@Autowired
	OwnerPayService ownerpayservice;
	
	/**
	 * 日期格式，在每一个日期实体上添加一个注解，再在这里添加一下语句，这样可以保证后台和页面间交互的日期格式是标准的，不需要再进行格式化
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	
	/**
	 * 根据业主id查询该业主的缴费信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/getpayinfodetails")
	@ResponseBody
	public List<PayInfoDetails> getpayinfodetails(HttpSession session){
		List<PayInfoDetails> listpayinfodetails = new ArrayList<PayInfoDetails>();
		Object ownersession = session.getAttribute("owner");
		HouseOwner owner = (HouseOwner) ownersession;
		try {
			int ownerId = owner.getOwnerId();
			listpayinfodetails = ownerpayservice.getpayinfodetails(ownerId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listpayinfodetails;
	}
}
