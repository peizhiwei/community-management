package com.peizhiwei.community.owner.web;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.admin.entity.Complaint;
import com.peizhiwei.community.admin.entity.HouseOwner;
import com.peizhiwei.community.admin.entity.JspResult;
import com.peizhiwei.community.owner.service.OwnerComplaintService;

@RequestMapping("/ownercomplaint")
@Controller
public class OwnerComplaintController {
	@Autowired
	OwnerComplaintService ownercomplaintservice;
	
	/**
	 * 日期格式，在每一个日期实体上添加一个注解，再在这里添加一下语句，这样可以保证后台和页面间交互的日期格式是标准的，不需要再进行格式化
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	/**
	 * 根据业主id查询该业主的投诉信息
	 * @return
	 */
	@RequestMapping("/getcomplaintinfo")
	@ResponseBody
	public List<Complaint> getcomplaintinfo(HttpSession session){
		List<Complaint> listcomplaint = new ArrayList<Complaint>();
		Object ownersession = session.getAttribute("owner");
		HouseOwner owner = (HouseOwner) ownersession;
		try {
			int ownerId = owner.getOwnerId();
			listcomplaint = ownercomplaintservice.getcomplaint(ownerId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listcomplaint;
	}
	
	/**
	 * 新增投诉信息
	 * @param complaintReason
	 * @param session
	 * @return
	 */
	@RequestMapping("/insertcomplaint")
	@ResponseBody
	public JspResult insertcomplaint(String complaintReason,HttpSession session) {
		JspResult rs = new JspResult();
		Complaint complaint = new Complaint();
		Object ownersession = session.getAttribute("owner");
		HouseOwner owner = (HouseOwner) ownersession;
		try {
			complaint.setHouseOwner(owner);
			complaint.setComplaintReason(complaintReason);
			complaint.setComplaintTime(new Date());
			ownercomplaintservice.insertcomplaint(complaint);
			rs.setFlag(true);
			rs.setMsg("投诉已提交！");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
	
	@RequestMapping("/updatecomplaint")
	@ResponseBody
	public JspResult updatecomplaint(
			@RequestParam(value = "complaintId",required =  false)Integer complaintId,
			@RequestParam(value = "complaintReason",required = false)String complaintReason) {
		JspResult rs = new JspResult();
		Complaint complaint = new Complaint();
		try {
			complaint.setComplaintId(complaintId);
			complaint.setComplaintReason(complaintReason);
			ownercomplaintservice.updatecomplaint(complaint);
			rs.setFlag(true);
			rs.setMsg("修改成功！");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
}
