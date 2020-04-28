package com.peizhiwei.community.admin.service;

import java.util.List;


import com.peizhiwei.community.admin.entity.Complaint;
import com.peizhiwei.community.util.Pager;


public interface ComplaintService {
	/**
	 * 分页查询所有投诉信息
	 */
	Pager<Complaint> pagegetallcomplaintinfo(int page,int size);
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
	/**
	 * 根据投诉信息的id删除投诉信息
	 */
	void deletecomplaint(int complaintId);
	/**
	 * 根据业主id删除该业主的所有投诉信息
	 */
	void deletecomplaintofowner(int ownerId);
	/**
	 * 模糊查询投诉信息(投诉人，楼栋编号，单元号，房间号，投诉内容)
	 * @param ownerName
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @param complaintReason
	 * @return
	 */
	Pager<Complaint> getcomplaintinfolike(String ownerName,String buildNumber,String houseUnit,String houseNumber,String complaintReason,int page,int size);
	/**
	 * 批量删除投诉信息
	 * @param listcomplaint
	 */
	void checkdelete(String[] listcomplaint);
}
