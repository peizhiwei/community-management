package com.peizhiwei.community.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.dao.ComplaintDao;
import com.peizhiwei.community.entity.Complaint;
import com.peizhiwei.community.service.ComplaintService;

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
	
}
