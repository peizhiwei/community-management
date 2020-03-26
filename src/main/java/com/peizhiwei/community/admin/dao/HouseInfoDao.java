package com.peizhiwei.community.admin.dao;

import java.util.List;

import com.peizhiwei.community.admin.entity.HouseInfo;
import com.peizhiwei.community.admin.entity.HouseType;

public interface HouseInfoDao {
	/**
	 * 获取所有房间信息
	 */
	List<HouseInfo> getallhouseinfo();
	/**
	 * 根据业主名称查询所有的房间信息
	 */
	List<HouseInfo> getallhouseinfoaccordingownername(String ownerName);
	/**
	 * 查询所有房型
	 */
	List<HouseType> getallhousetype();
	/**
	 * 修改房间信息
	 */
	void updatehouseinfo(HouseInfo houseinfo);
	/**
	 * 查询楼栋号所属的所有房间信息
	 */
	List<HouseInfo> getallhouseinfoofbuild(String buildNumber);
	/**
	 * 根据房间号查询该房间id
	 */
	Integer gethouseidaccordinghousenumber(String houseNumber);
	/**
	 * 在新增业主的时候在房间信息表中插入业主id、入住时间（默认为新增业主时间）
	 */
	void updatehouseinfoofownerid(HouseInfo houseinfo);
	/**
	 * 业主迁出时，将业主id置为空，入住时间为空，状态置为0待售
	 * @param ownerId
	 */
	void updatehouseinfoofowner(int ownerId);
	
}
