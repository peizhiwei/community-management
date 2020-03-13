package com.peizhiwei.community.dao;

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
}
