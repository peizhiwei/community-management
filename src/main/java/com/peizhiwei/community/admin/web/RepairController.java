package com.peizhiwei.community.admin.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.admin.entity.Admin;
import com.peizhiwei.community.admin.entity.JspResult;
import com.peizhiwei.community.admin.entity.Repair;
import com.peizhiwei.community.admin.service.RepairService;

@Controller
@RequestMapping("/repairinfo")
public class RepairController {
	@Autowired
	RepairService repairservice;
	
	/**
	 * 日期格式，在每一个日期实体上添加一个注解，再在这里添加一下语句，这样可以保证后台和页面间交互的日期格式是标准的，不需要再进行格式化
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	/**
	 * 获取所有的报修信息
	 * @return
	 */
	@RequestMapping("/getallrepairinfo")
	@ResponseBody
	public List<Repair> getallrepairinfo(){
		List<Repair> listrepairinfo = repairservice.getallrepairinfo();
		return listrepairinfo;
	}
	/**
	 * 受理报修信息
	 * @return
	 */
	@RequestMapping("/acceptance")
	@ResponseBody
	public JspResult acceptance(Integer repairId,HttpSession session) {
		JspResult rs = new JspResult();
		Repair repair = new Repair();
		try {
			Object adminsession = session.getAttribute("admin");
			Admin admin = (Admin) adminsession;
			repair.setAdmin(admin);
			repair.setRepairId(repairId);
			repairservice.acceptancerepair(repair);
			rs.setFlag(true);
			rs.setMsg("已受理");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * 已解决投诉信息，修改投诉状态为2已解决
	 * @param complaintId
	 * @return
	 */
	@RequestMapping("/settled")
	@ResponseBody
	public JspResult settled(Integer repairId,HttpSession session) {
		JspResult rs = new JspResult();
		Repair repair = new Repair();
		try {
			Object adminsession = session.getAttribute("admin");
			Admin admin = (Admin) adminsession;
			if(admin.getAdminId()!=repairservice.getrepairinfo(repairId)) {
				rs.setFlag(false);
				rs.setMsg("您不是受理人，无权执行此操作");
			}else {
				repair.setRepairId(repairId);
				repair.setRepairSettleTime(new Date());
				repairservice.settledrepair(repair);
				rs.setFlag(true);
				rs.setMsg("已解决");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * 根据报修信息id删除报修信息
	 * @param repairId
	 * @return
	 */
	@RequestMapping("/deleterepair")
	@ResponseBody
	public JspResult deleterepair(Integer repairId) {
		JspResult rs = new JspResult();
		repairservice.deleterepair(repairId);
		rs.setFlag(true);
		rs.setMsg("删除成功！");
		return rs;
	}
	/**
	 * 模糊查询报修信息(报修人，楼栋编号，单元号，房间号，报修物品)
	 * @param ownerName
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @param complaintReason
	 * @return
	 */
	@RequestMapping("/getrepairinfolike")
	@ResponseBody
	public List<Repair> getrepairinfolike(
			@RequestParam(value = "ownerName",required = false)String ownerName,
			@RequestParam(value = "buildNumber",required = false)String buildNumber,
			@RequestParam(value = "houseUnit",required = false)String houseUnit,
			@RequestParam(value = "houseNumber",required = false)String houseNumber,
			@RequestParam(value = "repairGoods",required = false)String repairGoods){
		List<Repair> listrepair = repairservice.getrepairinfolike(ownerName, buildNumber, houseUnit, houseNumber, repairGoods);
		return listrepair;
	}
	/**
	 * 批量删除报修信息
	 * @param listrepairId
	 * @return
	 */
	@RequestMapping("/checkdelete")
	@ResponseBody
	public JspResult checkdelete(@RequestBody int[] listrepairId) {
		JspResult rs = new JspResult();
		repairservice.checkdelete(listrepairId);
		rs.setFlag(true);
		rs.setMsg("已全部删除！");
		return rs;
	}
}
