package com.peizhiwei.community.owner.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.Complaint;

public interface OwnerComplaintService {
	
	/**
	 * 根据业主id查询该业主的投诉信息
	 * @param ownerId
	 * @return
	 */
	List<Complaint> getcomplaint(int ownerId);
	/**
	 * 新增投诉信息
	 * @param complaint
	 */
	void insertcomplaint(Complaint complaint);
	/**
	 * 修改投诉信息
	 * @param complaint
	 */
	void updatecomplaint(Complaint complaint);
}
