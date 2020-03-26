package com.peizhiwei.community.admin.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.HouseOwner;

public interface HouseOwnerService {
	/**
	 * 获取所有业主信息
	 * @return
	 */
	List<HouseOwner> getallhouseownerinfo();
	/**
	 * 获取所有业主名称，去掉重复的
	 */
	List<HouseOwner> getallonluhouseownername();
	/**
	 * 根据房间号查询业主信息
	 * @param houseNumber
	 * @return
	 */
	HouseOwner gethouseownerinfoaccordinghousenumber(String houseNumber);
	/**
	 * 修改业主信息
	 * @param houseowner
	 */
	void updatehouseownerinfo(HouseOwner houseowner);
	/**
	 * 新增业主信息
	 * @param houseownerinfo
	 */
	void inserthouseownerinfo(HouseOwner houseownerinfo);
	/**
	 * 业主迁出
	 * @param ownerId
	 */
	void deleteowner(int ownerId);
}
