package com.peizhiwei.community.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peizhiwei.community.admin.dao.ComplaintDao;
import com.peizhiwei.community.admin.entity.Complaint;
import com.peizhiwei.community.admin.entity.Parking;
import com.peizhiwei.community.admin.service.ComplaintService;
import com.peizhiwei.community.util.Pager;

@Service
@Transactional(rollbackFor = Exception.class)
public class ComplaintServiceImpl implements ComplaintService {
	@Autowired
	ComplaintDao complaintdao;
	/**
	 * 分页查询所有投诉信息
	 */
	@Override
	public Pager<Complaint> pagegetallcomplaintinfo(int page, int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size", size);
		List<Complaint> listcomplaint = complaintdao.pagegetallcomplaintinfo(params);
		Pager<Complaint> pager = new Pager<Complaint>();
		pager.setRows(listcomplaint);
		pager.setTotal(complaintdao.count());
		return pager;
	}
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
	/**
	 * 模糊查询投诉信息，(投诉人，楼栋编号，单元号，房间号，投诉原因)
	 */
	@Override
	public Pager<Complaint> getcomplaintinfolike(String ownerName, String buildNumber, String houseUnit,
			String houseNumber, String complaintReason,int page,int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size",size);
		List<Complaint> listcomplaint = complaintdao.getcomplaintinfolike(ownerName, buildNumber, houseUnit, houseNumber, complaintReason, params);
 		Pager<Complaint> pagecomplaint = new Pager<Complaint>();
 		pagecomplaint.setRows(listcomplaint);
 		pagecomplaint.setTotal(complaintdao.likecount(ownerName, buildNumber, houseUnit, houseNumber, complaintReason));
		return pagecomplaint;
	}
	/**
	 * 批量删除投诉信息
	 */
	@Override
	public void checkdelete(String[] listcomplaint) {
		complaintdao.checkdelete(listcomplaint);
	}
}
