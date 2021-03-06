package com.peizhiwei.community.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peizhiwei.community.admin.dao.ParkingDao;
import com.peizhiwei.community.admin.entity.Parking;
import com.peizhiwei.community.admin.service.ParkingService;
import com.peizhiwei.community.util.Pager;
@Service
@Transactional(rollbackFor = Exception.class)
public class ParkingServiceImpl implements ParkingService {
	@Autowired
	ParkingDao parkingdao;
	/**
	 * 分页查询所有停车位信息
	 */
	@Override
	public Pager<Parking> pagegetallparkinginfo(int page, int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size", size);
		List<Parking> listparking = parkingdao.pagegetallparkinginfo(params);
		Pager<Parking> pageparking = new Pager<Parking>();
		pageparking.setRows(listparking);
		pageparking.setTotal(parkingdao.count());
		return pageparking;
	}	
	/**
	 * 获取所有停车位信息
	 */
	@Override
	public List<Parking> getallparkinginfo() {
		List<Parking> listparkinginfo = parkingdao.getallparkinginfo();
		return listparkinginfo;
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
	/**
	 * 分页模糊查询车位信息，(车位号，业主名，楼栋号，单元号，房间号)
	 */
	@Override
	public Pager<Parking> getparkinginfolike(String parkingNumber, String ownerName, String buildNumber,String houseUnit, String houseNumber,int page, int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size", size);
		List<Parking> listparking = parkingdao.getparkinginfolike(parkingNumber, ownerName, buildNumber, houseUnit, houseNumber, params);
		Pager<Parking> pager = new Pager<Parking>();
		pager.setRows(listparking);
		pager.setTotal(parkingdao.likecount(parkingNumber, ownerName, buildNumber, houseUnit, houseNumber));
		return pager;
	}
	
}
