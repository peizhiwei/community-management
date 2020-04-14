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
	 * 日期格式，在每一个日期实体上添加一个注解，再在这里添加一下语句，这样可以保证后台和页面间交互的日期格式是标准的，不需要再进行格式化
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	/**
	 * 获取所有业主信息
	 * @return
	 */
	@RequestMapping("getallhouseownerinfo")
	@ResponseBody
	public List<HouseOwner> getallhouseownerinfo(){
		List<HouseOwner> listhouseownerinfo=houseownerservice.getallhouseownerinfo();
		return listhouseownerinfo;
	}
	/**
	 * 获取所有业主名称，去掉重复的
	 * @return
	 */
	@RequestMapping("/getallonlyhouseownername")
	@ResponseBody
	public List<String> getallonlyhouseownername(){
		List<String> listonlyhouseownername = houseownerservice.getallonluhouseownername();
		return listonlyhouseownername;
	}
	
	/**
	 * 更新业主信息
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
		if(ownerselfmessageservice.selectownerphone(ownerPhone)==false||oldownerPhone.equals(ownerPhone)) {//数据库中该手机号不存在
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
			rs.setMsg("修改成功");
		}else {
			rs.setFlag(false);
			rs.setMsg("您输入的手机号已存在，请重新输入！");
		}
		return rs;
	}
	/**
	 * 获取还有住户的楼栋编号
	 * @return
	 */
	@RequestMapping("/getallhavenullhousebuildnumber")
	@ResponseBody
	public List<String> getallhavenullhousebuildnumber(){
		List<String> buildinginfolist = houseownerservice.getallhavenullhousebuildNumber();
		return buildinginfolist;
	}
	/**
	 * 根据楼栋编号查询该楼栋中还有空房间的单元号
	 */
	@RequestMapping("/getallhavenullhousehouseunit")
	@ResponseBody
	public List<Integer> getallhavenullhousehouseunit(String buildNumber){
		List<Integer> listhouseunit = houseownerservice.getallhavenullhousehouseunit(buildNumber);
		return listhouseunit;
	}
	/**
	 * 根据楼栋编号，单元号，查询所有空房间的房间号
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
	 * 新增业主信息
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
			if(ownerselfmessageservice.selectownerphone(ownerPhone)==false) {//输入的手机号不存在
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
				houseownerservice.inserthouseownerinfo(houseownerinfo);//添加业主
				//添加业主时，在房间信息表中添加业主id,入住时间（默认为系统当前时间）
				HouseInfo updatehouseinfo= new HouseInfo();
				updatehouseinfo.setHouseInTime(new Date());
				updatehouseinfo.setHouseId(houseinfoservice.selecthouseid(buildNumber, houseUnit, houseNumber));
				HouseOwner houseowner=new HouseOwner();
				houseowner.setOwnerId(houseownerinfo.getOwnerId());//获取刚刚添加的业主id
				updatehouseinfo.setHouseOwner(houseowner);
				houseinfoservice.updatehouseinfoofownerid(updatehouseinfo);
				//添加业主的同时在缴费信息汇总表中添加该业主的缴费汇总信息
				PayInfoSum payinfosum = new PayInfoSum();
				HouseOwner paysumfoowner = new HouseOwner();
				paysumfoowner.setOwnerId(houseownerinfo.getOwnerId());//刚刚添加的业主id
				payinfosum.setHouseOwner(paysumfoowner);
				payinfosumservice.insertpayinfosum(payinfosum);
				rs.setFlag(true);
				rs.setMsg("添加成功");
			}else {
				rs.setFlag(false);
				rs.setMsg("手机号已存在，请重新输入！");
				System.out.println("手机号已存在，请重新输入！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return rs;
	}
	/**
	 * 业主迁出，同时删除家庭成员信息和车位信息
	 * @return
	 */
	@RequestMapping("/deleteowner")
	@ResponseBody
	public JspResult deleteowner(Integer ownerId) {
		JspResult rs = new JspResult();
		try {
			houseownerservice.deleteowner(ownerId);
			rs.setFlag(true);
			rs.setMsg("迁出成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * 模糊查询业主信息
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
	 * 批量删除业主
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
		rs.setMsg("已全部迁出！");
		return rs;
	}
}
