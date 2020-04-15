package com.peizhiwei.community.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.admin.dao.RepairDao;
import com.peizhiwei.community.admin.entity.Repair;
import com.peizhiwei.community.admin.service.RepairService;

@Service
public class RepairServiceImpl implements RepairService {
	@Autowired
	RepairDao repairdao;
	/**
	 * 获取所有报修信息
	 */
	@Override
	public List<Repair> getallrepairinfo() {
		List<Repair> listrepairinfo = repairdao.getallrepairinfo();
		return listrepairinfo;
	}
	/**
	 * 受理报修信息，添加受理人，修改报修状态为1表已受理
	 */
	@Override
	public void acceptancerepair(Repair repairId) {
		repairdao.acceptancerepair(repairId);		
	}
	/**
	 * 已解决报修信息，修改状态为2已解决
	 */
	@Override
	public void settledrepair(Repair repair) {
		repairdao.settledrepair(repair);
		
	}
	/**
	 *  根据报修信息的id获取受理人id
	 */
	@Override
	public int getrepairinfo(int repairId) {
		int adminid = repairdao.getrepairinfo(repairId);
		return adminid;
	}
	/**
	 * 根据报修信息id删除报修信息
	 */
	@Override
	public void deleterepair(int repairId) {
		repairdao.deleterepair(repairId);
	}
	/**
	 * 根据业主id删除该业主的所有报修信息
	 */
	@Override
	public void deleterepairofowner(int ownerId) {
		repairdao.deleterepairofowner(ownerId);
	}
	/**
	 * 模糊查询报修信息，(报修人，楼栋编号，单元号，房间号，报修物品)
	 */
	@Override
	public List<Repair> getrepairinfolike(String ownerName, String buildNumber, String houseUnit, String houseNumber,
			String repairGoods) {
		List<Repair> listrepair = repairdao.getrepairinfolike(ownerName, buildNumber, houseUnit, houseNumber, repairGoods);
		return listrepair;
	}
	/**
	 * 批量删除报修信息
	 */
	@Override
	public void checkdelete(int[] listrepairId) {
		repairdao.checkdelete(listrepairId);
	}
}
