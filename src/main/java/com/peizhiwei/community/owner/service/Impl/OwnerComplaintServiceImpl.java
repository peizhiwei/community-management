package com.peizhiwei.community.owner.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.admin.entity.Complaint;
import com.peizhiwei.community.owner.dao.OwnerComplaintDao;
import com.peizhiwei.community.owner.service.OwnerComplaintService;
@Service
public class OwnerComplaintServiceImpl implements OwnerComplaintService {
	@Autowired
	OwnerComplaintDao ownercomplaintdao;
	
	/**
	 * 新增投诉信息
	 */
	@Override
	public void insertcomplaint(Complaint complaint) {
		ownercomplaintdao.insertcomplaint(complaint);
	}
	/**
	 * 修改投诉信息
	 */
	@Override
	public void updatecomplaint(Complaint complaint) {
		ownercomplaintdao.updatecomplaint(complaint);
	}
	/**
	 * 根据业主id查询该业主的投诉信息
	 */
	@Override
	public List<Complaint> getcomplaint(int ownerId) {
		List<Complaint> listcomplaint = ownercomplaintdao.getcomplaint(ownerId);
		return listcomplaint;
	}

}
