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
	 * ��ҳ��ѯ����ͣ��λ��Ϣ
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
	 * ��ҳģ����ѯ��λ��Ϣ��(��λ�ţ�ҵ������¥���ţ���Ԫ�ţ������)
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
