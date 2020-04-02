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
	 * ���ڸ�ʽ����ÿһ������ʵ�������һ��ע�⣬�����������һ����䣬�������Ա�֤��̨��ҳ��佻�������ڸ�ʽ�Ǳ�׼�ģ�����Ҫ�ٽ��и�ʽ��
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	
	/**
	 * ����ҵ��id��ѯ��ҵ���Ľɷ���Ϣ
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
