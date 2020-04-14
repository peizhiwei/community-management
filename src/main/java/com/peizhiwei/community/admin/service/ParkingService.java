package com.peizhiwei.community.admin.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.Parking;

public interface ParkingService {
	/**
	 * ��ȡ����ͣ��λ��Ϣ
	 * @return
	 */
	List<Parking> getallparkinginfo();
	/**
	 * ��ѯ��ǰ����������һ��
	 * @return
	 */
	String getparkinginfomaxparkingnumber();
	/**
	 * ����ҵ��id�ջ�ͣ��λ��Ϣ
	 * @param ownerId
	 */
	void takebackownerofparking(int ownerId);
	/**
	 * ����ͣ��λ�۸�
	 */
	void updateparkingprice(Parking parking);
	/**
	 * ����ͣ��λ�����¸�ͣ��λ��Ϣ
	 */
	void sellupdateparkinginfo(Parking parking);
	/**
	 * �ջ�ͣ��λ
	 */
	void takebackupdateparkinginfo(int parkingId);
	/**
	 * ��������ͣ��λ��Ϣ
	 */
	void insertparkinginfo(List<Parking> parkinglist);
	
	/**
	 * ģ����ѯ��(��λ�ţ�ҵ������¥���ţ���Ԫ�ţ������)
	 * @param parkingNumber
	 * @param ownerName
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @return
	 */
	List<Parking> getparkinginfolike(String parkingNumber,String ownerName,String buildNumber,String houseUnit,String houseNumber);
}
