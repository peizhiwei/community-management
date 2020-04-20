package com.peizhiwei.community.owner.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.admin.entity.Family;
import com.peizhiwei.community.admin.entity.HouseOwner;
import com.peizhiwei.community.admin.entity.JspResult;
import com.peizhiwei.community.owner.service.OwnerFamilyService;

@Controller
@RequestMapping("/ownerfamily")
public class OwnerFamilyController {
	@Autowired
	OwnerFamilyService ownerfamilyservice;
	/**
	 * ���ڸ�ʽ����ÿһ������ʵ�������һ��ע�⣬�����������һ����䣬�������Ա�֤��̨��ҳ��佻�������ڸ�ʽ�Ǳ�׼�ģ�����Ҫ�ٽ��и�ʽ��
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	/**
	 * ��ȡҵ���ļ�ͥ��Ա��Ϣ
	 * @param session
	 * @return
	 */
	@RequestMapping("/getfamilyofowner")
	@ResponseBody
	public List<Family> getfamilyofowner(HttpSession session){
		Object ownersession = session.getAttribute("owner");
		HouseOwner owner = (HouseOwner) ownersession;
		int ownerId = owner.getOwnerId();
		List<Family> listfamily = new ArrayList<Family>();
		listfamily = ownerfamilyservice.selectfamilyofowner(ownerId);
		return listfamily;
	}
	
	@RequestMapping("/insertfamily")
	@ResponseBody
	public JspResult insertfamily(
			@RequestParam(value = "familyName",required = false)String familyName,
			@RequestParam(value = "familySex",required = false)int familySex,
			@RequestParam(value = "familyPhone",required = false)String familyPhone,
			@RequestParam(value = "familyBirthday",required = false)Date familyBirthday,
			@RequestParam(value = "familyRelation",required = false)String familyRelation,
			@RequestParam(value = "familyNativePlace",required = false)String familyNativePlace,
			@RequestParam(value = "familyWorkPlace",required = false)String familyWorkPlace,HttpSession session) {
		JspResult rs = new JspResult();
		Family family = new Family();
		Object ownersession = session.getAttribute("owner");
		HouseOwner owner = (HouseOwner) ownersession;
		try {
			family.setFamilyName(familyName);
			family.setFamilySex(familySex);
			family.setFamilyPhone(familyPhone);
			family.setFamilyBirthday(familyBirthday);
			family.setFamilyRelation(familyRelation);
			family.setFamilyNativePlace(familyNativePlace);
			family.setFamilyWorkPlace(familyWorkPlace);
			family.setHouseOwner(owner);
			ownerfamilyservice.insertfamily(family);
			rs.setFlag(true);
			rs.setMsg("��ӳɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return rs;
	}
}
