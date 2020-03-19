package com.peizhiwei.community.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.entity.Parking;

public interface ParkingDao {
	/**
	 * 获取所有停车位信息
	 * @return
	 */
	List<Parking> getallparkinginfo();
	/**
	 * 查询当前车库编号最大的一个
	 * @return
	 */
	String getparkinginfomaxparkingnumber();
	/**
	 * 更改停车位业主
	 */
	void updateparkinginfo(Parking parking);
	/**
	 * 调整停车位价格
	 */
	void updateparkingprice(Parking parking);
	/**
	 * 出售停车位，更新该停车位信息
	 */
	void sellupdateparkinginfo(Parking parking);
	/**
	 * 收回停车位
	 */
	void takebackupdateparkinginfo(int parkingId);
	/**
	 * 批量增加停车位信息
	 */
	void insertparkinginfo(@Param("parkinglist")List<Parking> parkinglist);
}
