package com.peizhiwei.community.dao;

import java.util.Date;
import java.util.List;

import com.peizhiwei.community.entity.HouseInfo;
import com.peizhiwei.community.entity.HouseType;

public interface HouseInfoDao {
	/**
	 * 获取所有房间信息
	 */
	List<HouseInfo> getallhouseinfo();
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
}
