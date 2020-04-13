package com.peizhiwei.community.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

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
	 * 根据楼栋编号，单元号，房间号，查询该房间的id
	 * @param buildNumber
	 * @param houseUnit
	 * @return
	 */
	int selecthouseid(@Param("buildNumber")String buildNumber,@Param("houseUnit")int houseUnit,@Param("houseNumber")String houseNumber);
	/**
	 * 在新增业主的时候在房间信息表中插入业主id、入住时间（默认为新增业主时间）
	 */
	void updatehouseinfoofownerid(HouseInfo houseinfo);
	/**
	 * 业主迁出时，将业主id置为空，入住时间为空，状态置为0待售
	 * @param ownerId
	 */
	void updatehouseinfoofowner(int ownerId);
	/**
	 * 模糊查询房间信息
	 * @param houseinfo(楼栋编号，单元号，房间号，业主姓名)
	 * @return
	 */
	List<HouseInfo> selecthouseinfolike(@Param("buildNumber")String buildNumber,@Param("houseUnit")String houseUnit,@Param("houseNumber")String houseNumber,@Param("ownerName")String ownerName);
	
}
