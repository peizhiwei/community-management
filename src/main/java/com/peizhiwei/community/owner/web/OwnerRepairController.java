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

import com.peizhiwei.community.admin.entity.HouseOwner;
import com.peizhiwei.community.admin.entity.JspResult;
import com.peizhiwei.community.admin.entity.Repair;
import com.peizhiwei.community.owner.service.OwnerRepairService;

@RequestMapping("/ownerrepair")
@Controller
public class OwnerRepairController {
	@Autowired
	OwnerRepairService ownerrepairservice;
	
	/**
	 * 日期格式，在每一个日期实体上添加一个注解，再在这里添加一下语句，这样可以保证后台和页面间交互的日期格式是标准的，不需要再进行格式化
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	/**
	 * 根据业主id查询该业主的报修信息
	 * @return
	 */
	@RequestMapping("/getrepairinfo")
	@ResponseBody
	public List<Repair> getrepairinfo(HttpSession session){
		List<Repair> listrepair = new ArrayList<Repair>();
		Object ownersession = session.getAttribute("owner");
		HouseOwner owner = (HouseOwner) ownersession;
		try {
			int ownerId = owner.getOwnerId();
			listrepair = ownerrepairservice.getrepairinfo(ownerId);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return listrepair;
	}
	
	/**
	 * 新增报修信息
	 * @param repairGoods
	 * @param repairReason
	 * @param session
	 * @return
	 */
	@RequestMapping("/insertrepair")
	@ResponseBody
	public JspResult insertrepair(
			@RequestParam(value = "repairGoods",required = false)String repairGoods,
			@RequestParam(value = "repairReason",required = false)String repairReason,HttpSession session) {
		JspResult rs = new JspResult();
		Repair repair = new Repair();
		Object ownersession = session.getAttribute("owner");
		HouseOwner owner = (HouseOwner) ownersession;
		try {
			repair.setHouseOwner(owner);
			repair.setRepairGoods(repairGoods);
			repair.setRepairReason(repairReason);
			repair.setRepairTime(new Date());
			ownerrepairservice.insertrepair(repair);
			rs.setFlag(true);
			rs.setMsg("提交成功!");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return rs;
	}
	/**
	 * 修改报修信息
	 * @param repairId
	 * @param repairGoods
	 * @param repairReason
	 * @return
	 */
	@RequestMapping("/updaterepair")
	@ResponseBody
	public JspResult updaterepair(
			@RequestParam(value = "repairId",required = false)Integer repairId,
			@RequestParam(value = "repairGoods",required = false)String repairGoods,
			@RequestParam(value = "repairReason",required = false)String repairReason) {
		JspResult rs = new JspResult();
		Repair repair = new Repair();
		try {
			repair.setRepairId(repairId);
			repair.setRepairGoods(repairGoods);
			repair.setRepairReason(repairReason);
			ownerrepairservice.updaterepair(repair);
			rs.setFlag(true);
			rs.setMsg("提交成功！");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return rs;
	}
}
