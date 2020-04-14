package com.peizhiwei.community.admin.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.peizhiwei.community.admin.entity.Family;
import com.peizhiwei.community.admin.entity.HouseOwner;
import com.peizhiwei.community.admin.entity.JspResult;
import com.peizhiwei.community.admin.service.FamilyService;
import com.peizhiwei.community.admin.service.HouseOwnerService;

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
	@RequestMapping("/getallfamilyinfo")
	@ResponseBody
	public List<Family> getallfamilyinfo(){
		List<Family> listfamilyinfo = familyservice.getallfamilyinfo();
		return listfamilyinfo;
	}
	/**
	 * ��ѯ������ס����¥����ţ�ȥ���ظ���
	 * @param ownerName
	 * @return
	 */
	@RequestMapping("/gethaveownerbuildnumber")
	@ResponseBody
	public List<String> gethaveownerbuildnumber(){
		List<String> listbuildnumber = familyservice.gethaveownerbuildnumber();
		return listbuildnumber;
	}
	/**
	 * ����¥����ţ���ѯ�ö�¥��������ס���ĵ�Ԫ��
	 * @param ownerName
	 * @param buildNumber
	 * @return
	 */
	@RequestMapping("/gethaveownerhouseunitaccordingbuildnumber")
	@ResponseBody
	public List<Integer> gethaveownerhouseunitaccordingbuildnumber(String buildNumber){
		List<Integer> listhouseunit = familyservice.gethaveownerhouseunitaccordingbuildnumber(buildNumber);
		return listhouseunit;
	}
	/**
	 * ����¥����ţ���Ԫ�ţ���ѯ������ס���ķ����
	 * @param ownerName
	 * @param buildNumber
	 * @param houseUnit
	 * @return
	 */
	@RequestMapping("/gethaveownerhousenumber")
	@ResponseBody
	public List<String> gethaveownerhousenumber(
			@RequestParam(value = "buildNumber",required = false)String buildNumber,
			@RequestParam(value = "houseUnit",required = false)int houseUnit){
		List<String> listhousenumber = familyservice.gethaveownerhousenumber(buildNumber, houseUnit);
		return listhousenumber;
	}
	/**
	 * ����¥����ţ���Ԫ�ţ�����ţ���ѯҵ����
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @return
	 */
	@RequestMapping("/getownername")
	@ResponseBody
	public JspResult getownername(
			@RequestParam(value = "buildNumber",required = false)String buildNumber,
			@RequestParam(value = "houseUnit",required = false)int houseUnit,
			@RequestParam(value = "houseNumber",required = false)String houseNumber) {
		String ownerName = familyservice.getownername(buildNumber, houseUnit, houseNumber);
		JspResult rs = new JspResult();
		rs.setMsg(ownerName);
		return rs;
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
			@RequestParam(value = "ownerName",required = false)String ownerName,
			@RequestParam(value = "buildNumber",required = false)String buildNumber,
			@RequestParam(value = "houseUnit",required = false)int houseUnit,
			@RequestParam(value = "houseNumber",required = false)String houseNumber,
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
			//����¥���ţ���Ԫ�ţ�����ţ���ѯҵ��id
			int ownerId = familyservice.selectowneridaccording_bn_hu_hn(buildNumber, houseUnit, houseNumber);
			houseowner.setOwnerId(ownerId);
			familyinfo.setHouseOwner(houseowner);
			
			familyinfo.setFamilyRelation(familyRelation);
			familyinfo.setFamilyNativePlace(familyNativePlace);
			familyinfo.setFamilyWorkPlace(familyWorkPlace);
			familyservice.insertfamilyinfo(familyinfo);
			rs.setFlag(true);
			rs.setMsg("��ӳɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
	/**
	 * ģ����ѯ��ͥ��Ա��Ϣ
	 * @return
	 */
	@RequestMapping("/getfamilyinfolike")
	@ResponseBody
	public List<Family> getfamilyinfolike(
			@RequestParam(value = "buildNumber",required = false)String buildNumber,
			@RequestParam(value = "houseUnit",required = false)String houseUnit,
			@RequestParam(value = "houseNumber",required = false)String houseNumber,
			@RequestParam(value = "ownerName",required = false)String ownerName,
			@RequestParam(value = "familyName",required = false)String familyName){
		List<Family> listfamily = new ArrayList<Family>();
		listfamily = familyservice.getfamilyinfolike(buildNumber, houseUnit, houseNumber, ownerName, familyName);
		return listfamily;
	}
	/**
	 * ����ɾ����ͥ��Ա��Ϣ
	 * @param listfamilyId
	 * @return
	 */
	@RequestMapping("/checkdelete")
	@ResponseBody
	public JspResult checkdelete(@RequestBody String[] listfamilyId) {
		JspResult rs = new JspResult();
		familyservice.checkdelete(listfamilyId);
		rs.setFlag(true);
		rs.setMsg("��ȫ��ɾ����");
		return rs;
	}
}
