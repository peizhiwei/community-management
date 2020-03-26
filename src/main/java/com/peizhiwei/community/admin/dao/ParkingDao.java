package com.peizhiwei.community.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.admin.entity.Parking;

public interface ParkingDao {
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
	 * ����ҵ��id�ջ�ͣ��λ��Ϣ
	 * @param ownerId
	 */
	void takebackownerofparking(int ownerId);
	/**
	 * ��������ͣ��λ��Ϣ
	 */
	void insertparkinginfo(@Param("parkinglist")List<Parking> parkinglist);
}
