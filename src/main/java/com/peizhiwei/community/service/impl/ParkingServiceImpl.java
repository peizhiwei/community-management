package com.peizhiwei.community.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.dao.ParkingDao;
import com.peizhiwei.community.entity.Parking;
import com.peizhiwei.community.service.ParkingService;
@Service
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
	 * ����ͣ��λҵ��
	 */
	@Override
	public void updateparkinginfo(Parking parking) {
		parkingdao.updateparkinginfo(parking);		
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
	
}
