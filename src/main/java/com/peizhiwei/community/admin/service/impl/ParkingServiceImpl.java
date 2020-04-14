package com.peizhiwei.community.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peizhiwei.community.admin.dao.ParkingDao;
import com.peizhiwei.community.admin.entity.Parking;
import com.peizhiwei.community.admin.service.ParkingService;
@Service
@Transactional(rollbackFor = Exception.class)
public class ParkingServiceImpl implements ParkingService {
	@Autowired
	ParkingDao parkingdao;
	
	/**
	 * ��ȡ����ͣ��λ��Ϣ
	 */
	@Override
	public List<Parking> getallparkinginfo() {
		List<Parking> listparkinginfo = parkingdao.getallparkinginfo();
		return listparkinginfo;
	}
	/**
	 * ��������ͣ��λ��Ϣ
	 */
	@Override
	public void insertparkinginfo(List<Parking> parkinglist) {
		parkingdao.insertparkinginfo(parkinglist);
		
	}
	/**
	 * ��ȡͣ��λ�������һ��
	 */
	@Override
	public String getparkinginfomaxparkingnumber() {
		String maxnumber = parkingdao.getparkinginfomaxparkingnumber();
		return maxnumber;
	}
	/**
	 * ����ͣ��λ�۸�
	 */
	@Override
	public void updateparkingprice(Parking parking) {
		parkingdao.updateparkingprice(parking);
		
	}
	/**
	 * ����ͣ��λ
	 */
	@Override
	public void sellupdateparkinginfo(Parking parking) {
		parkingdao.sellupdateparkinginfo(parking);
	}
	/**
	 * �ջ�ͣ��λ
	 */
	@Override
	public void takebackupdateparkinginfo(int parkingId) {
		parkingdao.takebackupdateparkinginfo(parkingId);
	}
	/**
	 * ����ҵ��id�ջ�ͣ��λ
	 */
	@Override
	public void takebackownerofparking(int ownerId) {
		parkingdao.takebackownerofparking(ownerId);
	}
	/**
	 * ģ����ѯ��λ��Ϣ��(��λ�ţ�ҵ������¥���ţ���Ԫ�ţ������)
	 */
	@Override
	public List<Parking> getparkinginfolike(String parkingNumber, String ownerName, String buildNumber,String houseUnit, String houseNumber) {
		List<Parking> listparking = parkingdao.getparkinginfolike(parkingNumber, ownerName, buildNumber, houseUnit, houseNumber);
		return listparking;
	}
	
}
