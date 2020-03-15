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
	 * 根据业主名称查询所有的房间信息
	 */
	List<HouseInfo> getallhouseinfoaccordingownername(String ownerName);
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
	/**
	 * 根据楼号，查询这栋楼的所有房间信息
	 * @param buildNumber
	 * @return
	 */
	List<HouseInfo> getallhouseinfoofbuild(String buildNumber);
	/**
	 * 根据房间号查询该房间的id
	 * @param houseNumber
	 * @return
	 */
	Integer gethouseidaccordinghousenumber(String houseNumber);
	/**
	 * 新增业主时，在房间信息表中添加业主id,入住时间（默认为系统当前时间）
	 * @param houseinfo
	 */
	void updatehouseinfoofownerid(HouseInfo houseinfo);
}
