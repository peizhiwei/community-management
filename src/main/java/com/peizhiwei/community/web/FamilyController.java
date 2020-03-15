package com.peizhiwei.community.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.entity.Family;
import com.peizhiwei.community.entity.HouseOwner;
import com.peizhiwei.community.entity.JspResult;
import com.peizhiwei.community.service.FamilyService;
import com.peizhiwei.community.service.HouseOwnerService;

@RequestMapping("/familyinfo")
@Controller
public class FamilyController {
	@Autowired
	FamilyService familyservice;
	@Autowired
	HouseOwnerService houseownerservice;
	
	/**
	 * ���ڸ�ʽ����ÿһ������ʵ�������һ��ע�⣬�����������һ����䣬�������Ա�֤��̨��ҳ��佻�������ڸ�ʽ�Ǳ�׼�ģ�����Ҫ�ٽ��и�ʽ��
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	/**
	 * ��ѯ���м�ͥ��Ա��Ϣ
	 * @return
	 */
	@RequestMapping("getallfamilyinfo")
	@ResponseBody
	public List<Family> getallfamilyinfo(){
		List<Family> listfamilyinfo = familyservice.getallfamilyinfo();
		return listfamilyinfo;
	}
	/**
	 * ���¼�ͥ��Ա��Ϣ
	 * @param familyId
	 * @param familyName
	 * @param familySex
	 * @param familyPhone
	 * @param familyBirthday
	 * @param familyRelation
	 * @param familyNativePlace
	 * @param familyWorkPlace
	 * @return
	 */
	@RequestMapping("/updatefamilyinfo")
	@ResponseBody
	public JspResult updatefamilyinfo(
			@RequestParam(value = "familyId",required = false)Integer familyId,
			@RequestParam(value = "familyName",required = false)String familyName,
			@RequestParam(value = "familySex",required = false)int familySex,
			@RequestParam(value = "familyPhone",required = false)String familyPhone,
			@RequestParam(value = "familyBirthday",required = false)Date familyBirthday,
			@RequestParam(value = "familyRelation",required = false)String familyRelation,
			@RequestParam(value = "familyNativePlace",required = false)String familyNativePlace,
			@RequestParam(value = "familyWorkPlace",required = false)String familyWorkPlace) {
		Family family = new Family();
		JspResult rs = new JspResult();
		try {
			family.setFamilyId(familyId);
			family.setFamilyName(familyName);
			family.setFamilySex(familySex);
			family.setFamilyPhone(familyPhone);
			family.setFamilyBirthday(familyBirthday);
			family.setFamilyRelation(familyRelation);
			family.setFamilyNativePlace(familyNativePlace);
			family.setFamilyWorkPlace(familyWorkPlace);
			
			familyservice.updatefamilyinfo(family);
			rs.setFlag(true);
			rs.setMsg("�޸ĳɹ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * ������ͥ��Ա��Ϣ
	 * @param familyName ����
	 * @param familySex �Ա�
	 * @param familyPhone �绰
	 * @param familyBirthday ��������
	 * @param familyHouseOwner ҵ��
	 * @param familyHouseNumber �����
	 * @param familyRelation ��ҵ����ϵ
	 * @param familyNativePlace ����
	 * @param familyWorkPlace ������λ
	 * @return
	 */
	@RequestMapping("/insertfamilyinfo")
	@ResponseBody
	public JspResult insertfamilyinfo(
			@RequestParam(value = "familyName",required = false)String familyName,
			@RequestParam(value = "familySex",required = false)int familySex,
			@RequestParam(value = "familyPhone",required = false)String familyPhone,
			@RequestParam(value = "familyBirthday",required = false)Date familyBirthday,
			@RequestParam(value = "familyHouseOwner",required = false)String familyHouseOwner,
			@RequestParam(value = "familyHouseNumber",required = false)String familyHouseNumber,
			@RequestParam(value = "familyRelation",required = false)String familyRelation,
			@RequestParam(value = "familyNativePlace",required = false)String familyNativePlace,
			@RequestParam(value = "familyWorkPlace",required = false)String familyWorkPlace) {
		Family familyinfo = new Family();
		HouseOwner houseowner = new HouseOwner();
		JspResult rs = new JspResult();
		try {
			familyinfo.setFamilyName(familyName);
			familyinfo.setFamilySex(familySex);
			familyinfo.setFamilyPhone(familyPhone);
			familyinfo.setFamilyBirthday(familyBirthday);
			//���ݷ���Ų�ѯҵ������Ϣ
			houseowner = houseownerservice.gethouseownerinfoaccordinghousenumber(familyHouseNumber);
			familyinfo.setHouseOwner(houseowner);
			
			familyinfo.setFamilyRelation(familyRelation);
			familyinfo.setFamilyNativePlace(familyNativePlace);
			familyinfo.setFamilyWorkPlace(familyWorkPlace);
			familyservice.insertfamilyinfo(familyinfo);
			rs.setFlag(true);
			rs.setMsg("��ӳɹ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * ɾ����ͥ��Ա��Ϣ
	 * @param familyId
	 * @return
	 */
	@RequestMapping("/deletefamilyinfo")
	@ResponseBody
	public JspResult deletefamilyinfo(Integer familyId) {
		System.out.println(familyId);
		JspResult rs = new JspResult();
		familyservice.deletefamilyinfo(familyId);
		rs.setFlag(true);
		rs.setMsg("ɾ���ɹ�");
		return rs;
	}
}
