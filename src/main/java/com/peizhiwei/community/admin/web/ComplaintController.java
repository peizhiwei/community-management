package com.peizhiwei.community.admin.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.peizhiwei.community.admin.entity.Admin;
import com.peizhiwei.community.admin.entity.Complaint;
import com.peizhiwei.community.admin.entity.JspResult;
import com.peizhiwei.community.admin.service.ComplaintService;

@RequestMapping("/complaintinfo")
@Controller
public class ComplaintController {
	@Autowired
	ComplaintService complaintservice;
	/**
	 * ���ڸ�ʽ����ÿһ������ʵ�������һ��ע�⣬�����������һ����䣬�������Ա�֤��̨��ҳ��佻�������ڸ�ʽ�Ǳ�׼�ģ�����Ҫ�ٽ��и�ʽ��
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	/**
	 * ��ȡ����Ͷ����Ϣ
	 * @return
	 */
	@RequestMapping("/getallcomplaintinfo")
	@ResponseBody
	public List<Complaint> getallcomplaintinfo(){
		List<Complaint> listcomplaint = new ArrayList<Complaint>();
		try {
			listcomplaint=complaintservice.getallcomplaintinfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listcomplaint;
	}
	/**
	 * ����Ͷ����Ϣ
	 * @return
	 */
	@RequestMapping("/acceptance")
	@ResponseBody
	public JspResult acceptance(Integer complaintId,HttpSession session) {
		JspResult rs = new JspResult();
		Complaint complaint = new Complaint();
		try {
			Object adminsession = session.getAttribute("admin");
			Admin admin = (Admin) adminsession;
			complaint.setAdmin(admin);
			complaint.setComplaintId(complaintId);
			complaintservice.acceptancecomplaint(complaint);
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
	public JspResult settled(Integer complaintId,HttpSession session) {
		JspResult rs = new JspResult();
		Complaint complaint = new Complaint();
		try {
			Object adminsession = session.getAttribute("admin");
			Admin admin = (Admin) adminsession;
			if(admin.getAdminId()!=complaintservice.getcomplaintinfo(complaintId)) {
				rs.setFlag(false);
				rs.setMsg("�����������ˣ���Ȩִ�д˲���");
			}else {
				complaint.setComplaintId(complaintId);
				complaint.setComplaintSettleTime(new Date());
				complaintservice.settledcomplaint(complaint);
				rs.setFlag(true);
				rs.setMsg("�ѽ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * ����Ͷ����Ϣidɾ��Ͷ����Ϣ
	 * @param complaintId
	 * @return
	 */
	@RequestMapping("/deletecomplaint")
	@ResponseBody
	public JspResult deletecomplaint(Integer complaintId) {
		JspResult rs = new JspResult();
		complaintservice.deletecomplaint(complaintId);
		rs.setFlag(true);
		rs.setMsg("ɾ���ɹ���");
		return rs;
	}
}
