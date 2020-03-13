package com.peizhiwei.community.service;

import java.util.List;

import com.peizhiwei.community.entity.BuildingInfo;
import com.peizhiwei.community.entity.HouseInfo;

public interface BuildingInfoService {
	/**
	 * 查询楼栋的所有信息
	 * @return
	 */
	List<BuildingInfo> getallbuildinginfo();

	/**
	 * 修改楼栋信息，如果修改的编号已存在，则修改失败
	 */
	boolean changebuildinfo(BuildingInfo buildinginfo);

	/**
	 * 先判断楼栋中是否有住户，如果有住户则不能删除,true能删除，false不能删除
	 * @param buildId
	 * @return
	 */
	boolean deletebuildinfoishouseowner(int buildId);

	/**
	 * 新增一条楼栋信息
	 * @param buildinginfo
	 * @return
	 */
	boolean insertbuildinginfo(BuildingInfo buildinginfo);
	/**
	 * 新增楼栋信息的同时批量插入房间信息
	 * @param houseinfolist
	 */
	void inserthouseinfolist(List<HouseInfo> houseinfolist);
}
