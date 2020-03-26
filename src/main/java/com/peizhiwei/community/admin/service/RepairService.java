package com.peizhiwei.community.admin.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.Repair;

public interface RepairService {
	/**
	 * 获取所有报修信息
	 * @return
	 */
	List<Repair> getallrepairinfo();
	/**
	 * 受理报修信息，添加受理人，修改报修状态为1表已受理，
	 * @param repairId
	 */
	void acceptancerepair(Repair repairId);
	/**
	 * 已解决报修信息，修改状态为2已解决
	 * @param Repair
	 */
	void settledrepair(Repair repair);
	/**
	 * 根据报修信息的id获取受理人id
	 * @param repairId
	 * @return
	 */
	int getrepairinfo(int repairId);
}
