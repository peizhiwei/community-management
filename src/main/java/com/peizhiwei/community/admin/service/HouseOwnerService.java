package com.peizhiwei.community.admin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	 * 获取所有还有住户的楼栋编号
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
	List<String> getallnullhousehousenumber(String buildNumber,int houseUnit);
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
	/**
	 * 模糊查询业主信息
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @param ownerName
	 * @param ownerPhone
	 * @return
	 */
	List<HouseOwner> gethouseownerinfolike(@Param("buildNumber")String buildNumber,@Param("houseUnit")String houseUnit,
			@Param("houseNumber")String houseNumber,@Param("ownerName")String ownerName,@Param("ownerPhone")String ownerPhone);
}
