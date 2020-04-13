package com.peizhiwei.community.admin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.admin.entity.HouseOwner;

public interface HouseOwnerService {
	/**
	 * ��ȡ����ҵ����Ϣ
	 * @return
	 */
	List<HouseOwner> getallhouseownerinfo();
	/**
	 * ��ȡ����ҵ�����ƣ�ȥ���ظ���
	 */
	List<HouseOwner> getallonluhouseownername();
	/**
	 * ���ݷ���Ų�ѯҵ����Ϣ
	 * @param houseNumber
	 * @return
	 */
	HouseOwner gethouseownerinfoaccordinghousenumber(String houseNumber);
	/**
	 * �޸�ҵ����Ϣ
	 * @param houseowner
	 */
	void updatehouseownerinfo(HouseOwner houseowner);
	/**
	 * ��ȡ���л���ס����¥�����
	 * @return
	 */
	List<String> getallhavenullhousebuildNumber();
	/**
	 * ����¥����Ų�ѯ��¥���л��пշ���ĵ�Ԫ
	 */
	List<Integer> getallhavenullhousehouseunit(String buildNumber);
	/**
	 * ����¥���ţ���Ԫ�ţ���ѯ�����
	 * @param buildNumber
	 * @param houseUnit
	 * @return
	 */
	List<String> getallnullhousehousenumber(String buildNumber,int houseUnit);
	/**
	 * ����ҵ����Ϣ
	 * @param houseownerinfo
	 */
	void inserthouseownerinfo(HouseOwner houseownerinfo);
	/**
	 * ҵ��Ǩ��
	 * @param ownerId
	 */
	void deleteowner(int ownerId);
	/**
	 * ģ����ѯҵ����Ϣ
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @param ownerName
	 * @param ownerPhone
	 * @return
	 */
	List<HouseOwner> gethouseownerinfolike(@Param("buildNumber")String buildNumber,@Param("houseUnit")String houseUnit,
			@Param("houseNumber")String houseNumber,@Param("ownerName")String ownerName,@Param("ownerPhone")String ownerPhone);
}
