package com.peizhiwei.community.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.admin.dao.ParkingDao;
import com.peizhiwei.community.admin.entity.Parking;
import com.peizhiwei.community.admin.service.ParkingService;
@Service
public class ParkingServiceImpl implements ParkingService {
	@Autowired
	ParkingDao parkingdao;
	
	/**
	 * 获取所有停车位信息
	 */
	@Override
	public List<Parking> getallparkinginfo() {
		List<Parking> listparkinginfo = parkingdao.getallparkinginfo();
		return listparkinginfo;
	}
	/**
	 * 更新停车位业主
	 */
	@Override
	public void updateparkinginfo(Parking parking) {
		parkingdao.updateparkinginfo(parking);		
	}
	/**
	 * 批量插入停车位信息
	 */
	@Override
	public void insertparkinginfo(List<Parking> parkinglist) {
		parkingdao.insertparkinginfo(parkinglist);
		
	}
	/**
	 * 获取停车位编号最大的一个
	 */
	@Override
	public String getparkinginfomaxparkingnumber() {
		String maxnumber = parkingdao.getparkinginfomaxparkingnumber();
		return maxnumber;
	}
	/**
	 * 调整停车位价格
	 */
	@Override
	public void updateparkingprice(Parking parking) {
		parkingdao.updateparkingprice(parking);
		
	}
	/**
	 * 出售停车位
	 */
	@Override
	public void sellupdateparkinginfo(Parking parking) {
		parkingdao.sellupdateparkinginfo(parking);
	}
	/**
	 * 收回停车位
	 */
	@Override
	public void takebackupdateparkinginfo(int parkingId) {
		parkingdao.takebackupdateparkinginfo(parkingId);
	}
	/**
	 * 根据业主id收回停车位
	 */
	@Override
	public void takebackownerofparking(int ownerId) {
		parkingdao.takebackownerofparking(ownerId);
	}
	
}
