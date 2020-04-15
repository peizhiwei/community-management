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
	 * ���ڸ�ʽ����ÿһ������ʵ�������һ��ע�⣬�����������һ����䣬�������Ա�֤��̨��ҳ��佻�������ڸ�ʽ�Ǳ�׼�ģ�����Ҫ�ٽ��и�ʽ��
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	/**
	 * ��ȡ���еı�����Ϣ
	 * @return
	 */
	@RequestMapping("/getallrepairinfo")
	@ResponseBody
	public List<Repair> getallrepairinfo(){
		List<Repair> listrepairinfo = repairservice.getallrepairinfo();
		return listrepairinfo;
	}
	/**
	 * ��������Ϣ
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
			rs.setMsg("������");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * �ѽ��Ͷ����Ϣ���޸�Ͷ��״̬Ϊ2�ѽ��
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
				rs.setMsg("�����������ˣ���Ȩִ�д˲���");
			}else {
				repair.setRepairId(repairId);
				repair.setRepairSettleTime(new Date());
				repairservice.settledrepair(repair);
				rs.setFlag(true);
				rs.setMsg("�ѽ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * ���ݱ�����Ϣidɾ��������Ϣ
	 * @param repairId
	 * @return
	 */
	@RequestMapping("/deleterepair")
	@ResponseBody
	public JspResult deleterepair(Integer repairId) {
		JspResult rs = new JspResult();
		repairservice.deleterepair(repairId);
		rs.setFlag(true);
		rs.setMsg("ɾ���ɹ���");
		return rs;
	}
	/**
	 * ģ����ѯ������Ϣ(�����ˣ�¥����ţ���Ԫ�ţ�����ţ�������Ʒ)
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
	 * ����ɾ��������Ϣ
	 * @param listrepairId
	 * @return
	 */
	@RequestMapping("/checkdelete")
	@ResponseBody
	public JspResult checkdelete(@RequestBody int[] listrepairId) {
		JspResult rs = new JspResult();
		repairservice.checkdelete(listrepairId);
		rs.setFlag(true);
		rs.setMsg("��ȫ��ɾ����");
		return rs;
	}
}
