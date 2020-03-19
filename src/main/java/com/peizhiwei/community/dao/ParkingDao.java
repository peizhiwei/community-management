package com.peizhiwei.community.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.entity.Parking;

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
	 * ��������ͣ��λ��Ϣ
	 */
	void insertparkinginfo(@Param("parkinglist")List<Parking> parkinglist);
}
