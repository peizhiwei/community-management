package com.peizhiwei.community.admin.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.BuildingInfo;
import com.peizhiwei.community.admin.entity.HouseInfo;

public interface BuildingInfoService {
	/**
	 * 查询楼栋的所有信息
	 * @return
	 */
	List<BuildingInfo> getallbuildinginfo();
	/**
	 * 根据楼房编号判断数据库中是否已存在该编号,存在返回true,不存在返回false
	 * @param buildNumber
	 * @return
	 */
	boolean selectnumberisnull(String buildNumber);

	/**
	 * 修改楼栋信息
	 */
	void changebuildinfo(BuildingInfo buildinginfo);

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
	/**
	 * 通过id查询楼栋编号
	 * @param buildId
	 * @return
	 */
	String selectbuildnumber(int buildId);
}
