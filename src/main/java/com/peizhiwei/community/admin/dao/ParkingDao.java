package com.peizhiwei.community.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

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
	/**
	 * ģ����ѯ��(��λ�ţ�ҵ������¥���ţ���Ԫ�ţ������)
	 * @param parkingNumber
	 * @param ownerName
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @return
	 */
	List<Parking> getparkinginfolike(@Param("parkingNumber")String parkingNumber,@Param("ownerName")String ownerName,
			@Param("buildNumber")String buildNumber,@Param("houseUnit")String houseUnit,@Param("houseNumber")String houseNumber);
}
