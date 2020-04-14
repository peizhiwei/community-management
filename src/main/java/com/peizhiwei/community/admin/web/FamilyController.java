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
	 * 日期格式，在每一个日期实体上添加一个注解，再在这里添加一下语句，这样可以保证后台和页面间交互的日期格式是标准的，不需要再进行格式化
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	/**
	 * 查询所有家庭成员信息
	 * @return
	 */
	@RequestMapping("/getallfamilyinfo")
	@ResponseBody
	public List<Family> getallfamilyinfo(){
		List<Family> listfamilyinfo = familyservice.getallfamilyinfo();
		return listfamilyinfo;
	}
	/**
	 * 查询所有有住户的楼栋编号，去掉重复的
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
	 * 根据楼栋编号，查询该栋楼中所有有住户的单元号
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
	 * 根据楼栋编号，单元号，查询所有有住户的房间号
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
	 * 根据楼栋编号，单元号，房间号，查询业主名
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
	 * 更新家庭成员信息
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
			rs.setMsg("修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * 新增家庭成员信息
	 * @param familyName 姓名
	 * @param familySex 性别
	 * @param familyPhone 电话
	 * @param familyBirthday 出生日期
	 * @param familyHouseOwner 业主
	 * @param familyHouseNumber 房间号
	 * @param familyRelation 与业主关系
	 * @param familyNativePlace 籍贯
	 * @param familyWorkPlace 工作单位
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
			//根据楼栋号，单元号，房间号，查询业主id
			int ownerId = familyservice.selectowneridaccording_bn_hu_hn(buildNumber, houseUnit, houseNumber);
			houseowner.setOwnerId(ownerId);
			familyinfo.setHouseOwner(houseowner);
			
			familyinfo.setFamilyRelation(familyRelation);
			familyinfo.setFamilyNativePlace(familyNativePlace);
			familyinfo.setFamilyWorkPlace(familyWorkPlace);
			familyservice.insertfamilyinfo(familyinfo);
			rs.setFlag(true);
			rs.setMsg("添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return rs;
	}
	/**
	 * 删除家庭成员信息
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
		rs.setMsg("删除成功");
		return rs;
	}
	/**
	 * 模糊查询家庭成员信息
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
	 * 批量删除家庭成员信息
	 * @param listfamilyId
	 * @return
	 */
	@RequestMapping("/checkdelete")
	@ResponseBody
	public JspResult checkdelete(@RequestBody String[] listfamilyId) {
		JspResult rs = new JspResult();
		familyservice.checkdelete(listfamilyId);
		rs.setFlag(true);
		rs.setMsg("已全部删除！");
		return rs;
	}
}
