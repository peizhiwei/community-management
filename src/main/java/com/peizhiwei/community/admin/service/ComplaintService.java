package com.peizhiwei.community.admin.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.Complaint;


public interface ComplaintService {
	/**
	 * 获取所有投诉信息
	 * @return
	 */
	List<Complaint> getallcomplaintinfo();
	/**
	 * 受理投诉信息，添加受理人，修改投诉状态为1表已受理，
	 * @param complaintid
	 */
	void acceptancecomplaint(Complaint complaintId);
	
	/**
	 * 已解决投诉信息，修改投诉状态为2已解决
	 * @param complaint
	 */
	void settledcomplaint(Complaint complaint);
	/**
	 * 根据投诉信息的id获取受理人id
	 * @param complaintId
	 * @return
	 */
	int getcomplaintinfo(int complaintId);
}
