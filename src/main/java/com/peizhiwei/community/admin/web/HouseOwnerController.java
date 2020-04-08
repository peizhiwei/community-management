package com.peizhiwei.community.admin.web;

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
	
	@RequestMapping("/getallonlyhouseownername")
	@ResponseBody
	public List<HouseOwner> getallonlyhouseownername(){
		List<HouseOwner> listonlyhouseownername = houseownerservice.getallonluhouseownername();
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
				houseinfo.setHouseId(houseinfoservice.gethouseidaccordinghousenumber(houseNumber));
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
				updatehouseinfo.setHouseId(houseinfoservice.gethouseidaccordinghousenumber(houseNumber));
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
	
}
