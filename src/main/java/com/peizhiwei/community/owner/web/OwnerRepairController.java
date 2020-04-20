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
	 * ���ڸ�ʽ����ÿһ������ʵ�������һ��ע�⣬�����������һ����䣬�������Ա�֤��̨��ҳ��佻�������ڸ�ʽ�Ǳ�׼�ģ�����Ҫ�ٽ��и�ʽ��
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	/**
	 * ����ҵ��id��ѯ��ҵ���ı�����Ϣ
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
	 * ����������Ϣ
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
			rs.setMsg("�ύ�ɹ�!");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return rs;
	}
	/**
	 * �޸ı�����Ϣ
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
			rs.setMsg("�ύ�ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return rs;
	}
}
