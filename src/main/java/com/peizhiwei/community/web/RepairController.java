package com.peizhiwei.community.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.entity.Admin;
import com.peizhiwei.community.entity.JspResult;
import com.peizhiwei.community.entity.Repair;
import com.peizhiwei.community.service.RepairService;

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
}
