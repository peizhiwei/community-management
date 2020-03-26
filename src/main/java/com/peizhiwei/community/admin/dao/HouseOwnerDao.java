package com.peizhiwei.community.admin.dao;

import java.util.List;

import com.peizhiwei.community.admin.entity.HouseOwner;

public interface HouseOwnerDao {
	/**
	 * 获取所有业主信息
	 * @return
	 */
	List<HouseOwner> getallhouseownerinfo();
	/**
	 * 获取所有业主名称，且名称相同的只显示一个
	 */
	List<HouseOwner> getallonlyhouseownername();
	/**
	 * 根据房间号查询业主信息
	 */
	HouseOwner gethouseownerinfoaccordinghousenumber(String houseNumber);
	/**
	 * 更新业主信息
	 * @param houseowner
	 */
	void updatehouseownerinfo(HouseOwner houseowner);

	/**
	 * 新增业主信息
	 * @param houseownerifo
	 */
	void inserthouseownerinfo(HouseOwner houseownerifo);
	/**
	 * 业主迁出
	 * @param ownerId
	 */
	void deleteowner(int ownerId);
}
