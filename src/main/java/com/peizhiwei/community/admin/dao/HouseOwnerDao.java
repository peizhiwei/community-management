package com.peizhiwei.community.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	 * 获取所有有空房间的楼栋编号
	 * @return
	 */
	List<String> getallhavenullhousebuildNumber();
	/**
	 * 根据楼栋编号查询该楼栋中还有空房间的单元
	 */
	List<Integer> getallhavenullhousehouseunit(String buildNumber);
	/**
	 * 根据楼栋号，单元号，查询房间号
	 * @param buildNumber
	 * @param houseUnit
	 * @return
	 */
	List<String> getallnullhousehousenumber(@Param("buildNumber")String buildNumber,@Param("houseUnit")int houseUnit);
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
