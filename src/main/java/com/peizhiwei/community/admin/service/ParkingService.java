package com.peizhiwei.community.admin.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.Parking;

public interface ParkingService {
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
	 * 根据业主id收回停车位信息
	 * @param ownerId
	 */
	void takebackownerofparking(int ownerId);
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
	void insertparkinginfo(List<Parking> parkinglist);
	
	/**
	 * 模糊查询，(车位号，业主名，楼栋号，单元号，房间号)
	 * @param parkingNumber
	 * @param ownerName
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @return
	 */
	List<Parking> getparkinginfolike(String parkingNumber,String ownerName,String buildNumber,String houseUnit,String houseNumber);
}
