package com.peizhiwei.community.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.admin.entity.HouseOwner;

public interface HouseOwnerDao {
	/**
	 * 分页查询，获取所有业主信息
	 * @return
	 */
	List<HouseOwner> pagegetallhouseownerinfo(Map<String, Object> params);
	long count();
	/**
	 * 获取所有业主信息
	 * @return
	 */
	List<HouseOwner> getallhouseownerinfo();
	/**
	 * 获取所有业主名称，且名称相同的只显示一个
	 */
	List<String> getallonlyhouseownername();
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
	 * 根据楼栋编号，单元号，房间号，查询该房间是否已有人居住
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @return
	 */
	int checkhouseisnull(@Param("buildNumber")String buildNumber,@Param("houseUnit")int houseUnit,@Param("houseNumber")String houseNumber);
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
	/**
	 * 模糊查询业主信息,分页查询
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @param ownerName
	 * @param ownerPhone
	 * @return
	 */
	List<HouseOwner> gethouseownerinfolike(@Param("buildNumber")String buildNumber,@Param("houseUnit")String houseUnit,
			@Param("houseNumber")String houseNumber,@Param("ownerName")String ownerName,@Param("ownerPhone")String ownerPhone,@Param("params")Map<String, Object> params);
	/**
	 * 模糊查询总记录数
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @param ownerName
	 * @param ownerPhone
	 * @return
	 */
	long likecount(@Param("buildNumber")String buildNumber,@Param("houseUnit")String houseUnit,
			@Param("houseNumber")String houseNumber,@Param("ownerName")String ownerName,@Param("ownerPhone")String ownerPhone);
}
