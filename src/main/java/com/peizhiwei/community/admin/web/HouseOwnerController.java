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

import com.peizhiwei.community.admin.entity.HouseInfo;
import com.peizhiwei.community.admin.entity.HouseOwner;
import com.peizhiwei.community.admin.entity.JspResult;
import com.peizhiwei.community.admin.entity.PayInfoSum;
import com.peizhiwei.community.admin.service.HouseInfoService;
import com.peizhiwei.community.admin.service.HouseOwnerService;
import com.peizhiwei.community.admin.service.PayInfoSumService;
import com.peizhiwei.community.owner.service.OwnerSelfMessageService;

@RequestMapping("houseownerinfo")
@Controller
public class HouseOwnerController {
	@Autowired
	HouseOwnerService houseownerservice;
	@Autowired
	HouseInfoService houseinfoservice;
	@Autowired
	PayInfoSumService payinfosumservice;
	@Autowired
	OwnerSelfMessageService ownerselfmessageservice;
	/**
	 * ���ڸ�ʽ����ÿһ������ʵ�������һ��ע�⣬�����������һ����䣬�������Ա�֤��̨��ҳ��佻�������ڸ�ʽ�Ǳ�׼�ģ�����Ҫ�ٽ��и�ʽ��
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	/**
	 * ��ȡ����ҵ����Ϣ
	 * @return
	 */
	@RequestMapping("getallhouseownerinfo")
	@ResponseBody
	public List<HouseOwner> getallhouseownerinfo(){
		List<HouseOwner> listhouseownerinfo=houseownerservice.getallhouseownerinfo();
		return listhouseownerinfo;
	}
	/**
	 * ��ȡ����ҵ�����ƣ�ȥ���ظ���
	 * @return
	 */
	@RequestMapping("/getallonlyhouseownername")
	@ResponseBody
	public List<String> getallonlyhouseownername(){
		List<String> listonlyhouseownername = houseownerservice.getallonluhouseownername();
		return listonlyhouseownername;
	}
	
	/**
	 * ����ҵ����Ϣ
	 * @param ownerId
	 * @param ownerName
	 * @param ownerSex
	 * @param ownerPhone
	 * @param ownerBirthday
	 * @param ownerIdCard
	 * @param ownerNativePlace
	 * @param ownerWorkPlace
	 * @return
	 */
	@RequestMapping("/updatehouseownerinfo")
	@ResponseBody
	public JspResult updatehouseownerinfo(
			@RequestParam(value = "ownerId",required = false)Integer ownerId,
			@RequestParam(value = "ownerName",required = false)String ownerName,
			@RequestParam(value = "ownerSex",required = false)int ownerSex,
			@RequestParam(value = "ownerPhone",required = false)String ownerPhone,
			@RequestParam(value = "oldownerPhone",required = false)String oldownerPhone,
			@RequestParam(value = "ownerBirthday",required = false)Date ownerBirthday,
			@RequestParam(value = "ownerIdCard",required = false)String ownerIdCard,
			@RequestParam(value = "ownerNativePlace",required = false)String ownerNativePlace,
			@RequestParam(value = "ownerWorkPlace",required = false)String ownerWorkPlace) {
		HouseOwner houseowner=new HouseOwner();
		JspResult rs = new JspResult();
		if(ownerselfmessageservice.selectownerphone(ownerPhone)==false||oldownerPhone.equals(ownerPhone)) {//���ݿ��и��ֻ��Ų�����
			houseowner.setOwnerId(ownerId);
			houseowner.setOwnerName(ownerName);
			houseowner.setOwnerSex(ownerSex);
			houseowner.setOwnerPhone(ownerPhone);
			houseowner.setOwnerBirthday(ownerBirthday);
			houseowner.setOwnerIdCard(ownerIdCard);
			houseowner.setOwnerNativePlace(ownerNativePlace);
			houseowner.setOwnerWorkPlace(ownerWorkPlace);
			houseownerservice.updatehouseownerinfo(houseowner);
			rs.setFlag(true);
			rs.setMsg("�޸ĳɹ�");
		}else {
			rs.setFlag(false);
			rs.setMsg("��������ֻ����Ѵ��ڣ����������룡");
		}
		return rs;
	}
	/**
	 * ��ȡ����ס����¥�����
	 * @return
	 */
	@RequestMapping("/getallhavenullhousebuildnumber")
	@ResponseBody
	public List<String> getallhavenullhousebuildnumber(){
		List<String> buildinginfolist = houseownerservice.getallhavenullhousebuildNumber();
		return buildinginfolist;
	}
	/**
	 * ����¥����Ų�ѯ��¥���л��пշ���ĵ�Ԫ��
	 */
	@RequestMapping("/getallhavenullhousehouseunit")
	@ResponseBody
	public List<Integer> getallhavenullhousehouseunit(String buildNumber){
		List<Integer> listhouseunit = houseownerservice.getallhavenullhousehouseunit(buildNumber);
		return listhouseunit;
	}
	/**
	 * ����¥����ţ���Ԫ�ţ���ѯ���пշ���ķ����
	 * @param buildNumber
	 * @param houseUnit
	 * @return
	 */
	@RequestMapping("/getallnullhousehousenumber")
	@ResponseBody
	public List<String> getallnullhousehousenumber(@RequestParam(value = "buildNumber",required = false)String buildNumber,
			@RequestParam(value = "houseUnit",required = false)int houseUnit){
		List<String> listhousenumber = houseownerservice.getallnullhousehousenumber(buildNumber, houseUnit);
		return listhousenumber;
	}
	/**
	 * ����ҵ����Ϣ
	 * @param ownerName
	 * @param houseNumber
	 * @param ownerSex
	 * @param ownerPhone
	 * @param ownerBirthday
	 * @param ownerIdCard
	 * @param ownerNativePlace
	 * @param ownerWorkPlace
	 * @return
	 */
	@RequestMapping("inserthouseownerinfo")
	@ResponseBody
	public JspResult inserthouseownerinfo(
			@RequestParam(value = "ownerName",required = false)String ownerName,
			@RequestParam(value = "buildNumber",required = false)String buildNumber,
			@RequestParam(value = "houseUnit",required = false)int houseUnit,
			@RequestParam(value = "houseNumber",required = false)String houseNumber,
			@RequestParam(value = "ownerSex",required = false)int ownerSex,
			@RequestParam(value = "ownerPhone",required = false)String ownerPhone,
			@RequestParam(value = "ownerBirthday",required = false)Date ownerBirthday,
			@RequestParam(value = "ownerIdCard",required = false)String ownerIdCard,
			@RequestParam(value = "ownerNativePlace",required = false)String ownerNativePlace,
			@RequestParam(value = "ownerWorkPlace",required = false)String ownerWorkPlace) {
		HouseOwner houseownerinfo = new HouseOwner();
		HouseInfo houseinfo= new HouseInfo();
		JspResult rs = new JspResult();
		try {
			if(ownerselfmessageservice.selectownerphone(ownerPhone)==false) {//������ֻ��Ų�����
				houseownerinfo.setOwnerName(ownerName);
				houseinfo.setHouseId(houseinfoservice.selecthouseid(buildNumber, houseUnit, houseNumber));
				houseownerinfo.setHouseInfo(houseinfo);
				
				houseownerinfo.setOwnerSex(ownerSex);
				houseownerinfo.setOwnerPhone(ownerPhone);
				houseownerinfo.setOwnerPassword("000000");
				houseownerinfo.setOwnerBirthday(ownerBirthday);
				houseownerinfo.setOwnerIdCard(ownerIdCard);
				houseownerinfo.setOwnerNativePlace(ownerNativePlace);
				houseownerinfo.setOwnerWorkPlace(ownerWorkPlace);
				houseownerservice.inserthouseownerinfo(houseownerinfo);//���ҵ��
				//���ҵ��ʱ���ڷ�����Ϣ�������ҵ��id,��סʱ�䣨Ĭ��Ϊϵͳ��ǰʱ�䣩
				HouseInfo updatehouseinfo= new HouseInfo();
				updatehouseinfo.setHouseInTime(new Date());
				updatehouseinfo.setHouseId(houseinfoservice.selecthouseid(buildNumber, houseUnit, houseNumber));
				HouseOwner houseowner=new HouseOwner();
				houseowner.setOwnerId(houseownerinfo.getOwnerId());//��ȡ�ո���ӵ�ҵ��id
				updatehouseinfo.setHouseOwner(houseowner);
				houseinfoservice.updatehouseinfoofownerid(updatehouseinfo);
				//���ҵ����ͬʱ�ڽɷ���Ϣ���ܱ�����Ӹ�ҵ���Ľɷѻ�����Ϣ
				PayInfoSum payinfosum = new PayInfoSum();
				HouseOwner paysumfoowner = new HouseOwner();
				paysumfoowner.setOwnerId(houseownerinfo.getOwnerId());//�ո���ӵ�ҵ��id
				payinfosum.setHouseOwner(paysumfoowner);
				payinfosumservice.insertpayinfosum(payinfosum);
				rs.setFlag(true);
				rs.setMsg("��ӳɹ�");
			}else {
				rs.setFlag(false);
				rs.setMsg("�ֻ����Ѵ��ڣ����������룡");
				System.out.println("�ֻ����Ѵ��ڣ����������룡");
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return rs;
	}
	/**
	 * ҵ��Ǩ����ͬʱɾ����ͥ��Ա��Ϣ�ͳ�λ��Ϣ
	 * @return
	 */
	@RequestMapping("/deleteowner")
	@ResponseBody
	public JspResult deleteowner(Integer ownerId) {
		JspResult rs = new JspResult();
		try {
			houseownerservice.deleteowner(ownerId);
			rs.setFlag(true);
			rs.setMsg("Ǩ���ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * ģ����ѯҵ����Ϣ
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @param ownerName
	 * @param ownerPhone
	 * @return
	 */
	@RequestMapping("/gethouseownerinfolike")
	@ResponseBody
	public List<HouseOwner> gethouseownerinfolike(
			@RequestParam(value = "buildNumber",required = false)String buildNumber,
			@RequestParam(value = "houseUnit",required = false)String houseUnit,
			@RequestParam(value = "houseNumber",required = false)String houseNumber,
			@RequestParam(value = "ownerName",required = false)String ownerName,
			@RequestParam(value = "ownerPhone",required = false)String ownerPhone){
		List<HouseOwner> listhouseowner = new ArrayList<HouseOwner>();
		listhouseowner = houseownerservice.gethouseownerinfolike(buildNumber, houseUnit, houseNumber, ownerName, ownerPhone);
		return listhouseowner;
	}
	/**
	 * ����ɾ��ҵ��
	 * @param listownerId
	 * @return
	 */
	@RequestMapping("/checkdelete")
	@ResponseBody
	public JspResult checkdelete(@RequestBody List<Integer> listownerId) {
		JspResult rs = new JspResult();
		for(int i=0;i<listownerId.size();i++) {
			houseownerservice.deleteowner(listownerId.get(i));
		}
		rs.setFlag(true);
		rs.setMsg("��ȫ��Ǩ����");
		return rs;
	}
}
