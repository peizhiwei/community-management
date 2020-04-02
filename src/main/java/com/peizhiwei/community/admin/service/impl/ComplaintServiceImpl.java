package com.peizhiwei.community.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.admin.dao.ComplaintDao;
import com.peizhiwei.community.admin.entity.Complaint;
import com.peizhiwei.community.admin.service.ComplaintService;

@Service
public class ComplaintServiceImpl implements ComplaintService {
	@Autowired
	ComplaintDao complaintdao;
	/**
	 * 获取所有投诉信息
	 */
	@Override
	public List<Complaint> getallcomplaintinfo() {
		List<Complaint> listcomplaint = complaintdao.getallcomplaintinfo();
		return listcomplaint;
	}
	/**
	 * 受理投诉信息，添加受理人，修改投诉状态为1已受理
	 */
	@Override
	public void acceptancecomplaint(Complaint complaintId) {
		complaintdao.acceptancecomplaint(complaintId);		
	}
	/**
	 * 已解决投诉信息，修改投诉状态为2已解决
	 */
	@Override
	public void settledcomplaint(Complaint complaint) {
		complaintdao.settledcomplaint(complaint);
	}
	/**
	 * 根据投诉信息的id获取受理人id
	 */
	@Override
	public int getcomplaintinfo(int complaintId) {
		int complaint = complaintdao.getcomplaintinfo(complaintId);
		return complaint;
	}
	/**
	 * 根据投诉信息id删除投诉信息
	 */
	@Override
	public void deletecomplaint(int complaintId) {
		complaintdao.deletecomplaint(complaintId);
	}
	/**
	 * 根据业主id删除该业主的所有投诉信息
	 */
	@Override
	public void deletecomplaintofowner(int ownerId) {
		complaintdao.deletecomplaintofowner(ownerId);
	}
	
}
