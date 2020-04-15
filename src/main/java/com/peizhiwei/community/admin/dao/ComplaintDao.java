package com.peizhiwei.community.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.peizhiwei.community.admin.entity.Complaint;

public interface ComplaintDao {
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
	List<Complaint> getcomplaintinfolike(@Param("ownerName")String ownerName,@Param("buildNumber")String buildNumber,
			@Param("houseUnit")String houseUnit,@Param("houseNumber")String houseNumber,@Param("complaintReason")String complaintReason);
	/**
	 * 批量删除投诉信息
	 * @param listcomplaint
	 */
	void checkdelete(String[] listcomplaint);
}
