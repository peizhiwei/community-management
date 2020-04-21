package com.peizhiwei.community.admin.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.Parking;
import com.peizhiwei.community.util.Pager;

public interface ParkingService {
	/**
	 * ��ҳ��ѯ����ͣ��λ��Ϣ
	 */
	Pager<Parking> pagegetallparkinginfo(int page,int size);
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
	 * ��ҳģ����ѯ��(��λ�ţ�ҵ������¥���ţ���Ԫ�ţ������)
	 * @param parkingNumber
	 * @param ownerName
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @return
	 */
	Pager<Parking> getparkinginfolike(String parkingNumber,String ownerName,String buildNumber,String houseUnit,String houseNumber,int page,int size);
}
