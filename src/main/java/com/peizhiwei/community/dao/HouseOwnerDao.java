package com.peizhiwei.community.dao;

import java.util.List;

import com.peizhiwei.community.entity.HouseOwner;

public interface HouseOwnerDao {
	/**
	 * 获取所有业主信息
	 * @return
	 */
	List<HouseOwner> getallhouseownerinfo();

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
}
