package com.peizhiwei.community.owner.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.Repair;

public interface OwnerRepairService {
	/**
	 * 根据业主id查询该业主的报修信息
	 * @param ownerId
	 * @return
	 */
	List<Repair> getrepairinfo(int ownerId);
	/**
	 * 新增报修信息
	 * @param repair
	 */
	void insertrepair(Repair repair);
	/**
	 * 修改报修信息
	 * @param repair
	 */
	void updaterepair(Repair repair);
}
