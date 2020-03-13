package com.peizhiwei.community.service;
/**
 * 房间信息
 * @author PEIZHIWEI
 *
 */

import java.util.List;

import com.peizhiwei.community.entity.HouseInfo;
import com.peizhiwei.community.entity.HouseType;

public interface HouseInfoService {
	/**
	 * 获取所有房间信息
	 * @return
	 */
	List<HouseInfo> getallhouseinfo();
	/**
	 * 获取所有房型信息
	 * @return
	 */
	List<HouseType> getallhousetype();
	/**
	 * 修改房间信息
	 * @param houseinfo
	 * @return
	 */
	void updatehouseinfo(HouseInfo houseinfo);
}
