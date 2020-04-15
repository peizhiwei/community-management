package com.peizhiwei.community.admin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	/**
	 * 根据报修信息的id删除报修信息
	 * @param repairId
	 */
	void deleterepair(int repairId);
	/**
	 * 根据业主id删除该业主的所有报修信息
	 * @param ownerId
	 */
	void deleterepairofowner(int ownerId);
	
	/**
	 * 模糊查询报修信息，(报修人，楼栋编号，单元号，房间号，报修物品)
	 * @param ownerName
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @param repairGoods
	 * @return
	 */
	List<Repair> getrepairinfolike(String ownerName,String buildNumber,String houseUnit,String houseNumber,String repairGoods);
	/**
	 * 批量删除报修信息
	 * @param listrepairId
	 */
	void checkdelete(int[] listrepairId);
}
