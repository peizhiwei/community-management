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
	 * ����ͣ��λҵ��
	 */
	void updateparkinginfo(Parking parking);
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
}
