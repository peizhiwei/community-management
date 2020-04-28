package com.peizhiwei.community.admin.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.HouseOwner;
import com.peizhiwei.community.util.Pager;

public interface HouseOwnerService {
	/**
	 * 分页查询，获取所有业主信息
	 * @return
	 */
	Pager<HouseOwner> pagegetallhouseownerinfo(int page,int size);
	/**
	 * 获取所有业主信息
	 * @return
	 */
	List<HouseOwner> getallhouseownerinfo();
	/**
	 * 获取所有业主名称，去掉重复的
	 */
	List<String> getallonluhouseownername();
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
	 * 根据楼栋编号，单元号，房间号，查询该房间是否已有人居住
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @return
	 */
	int checkhouseisnull(String buildNumber,int houseUnit,String houseNumber);
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
	Pager<HouseOwner> gethouseownerinfolike(String buildNumber,String houseUnit,
			String houseNumber,String ownerName,String ownerPhone,int page,int size);
}
