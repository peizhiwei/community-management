package com.peizhiwei.community.owner.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.admin.entity.Repair;
import com.peizhiwei.community.owner.dao.OwnerRepairDao;
import com.peizhiwei.community.owner.service.OwnerRepairService;
@Service
public class OwnerRepairServiceImpl implements OwnerRepairService {
	@Autowired
	OwnerRepairDao ownerrepairdao;
	
	/**
	 * 新增报修信息
	 */
	@Override
	public void insertrepair(Repair repair) {
		ownerrepairdao.insertrepair(repair);
	}
	/**
	 * 修改报修信息
	 */
	@Override
	public void updaterepair(Repair repair) {
		ownerrepairdao.updaterepair(repair);
	}
	/**
	 * 根据业主id查询该业主的报修信息
	 */
	@Override
	public List<Repair> getrepairinfo(int ownerId) {
		List<Repair> listrepair = ownerrepairdao.getrepairinfo(ownerId);
		return listrepair;
	}

}
