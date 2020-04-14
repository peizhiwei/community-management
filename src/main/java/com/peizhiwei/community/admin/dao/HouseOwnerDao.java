package com.peizhiwei.community.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.admin.entity.HouseOwner;

public interface HouseOwnerDao {
	/**
	 * ��ȡ����ҵ����Ϣ
	 * @return
	 */
	List<HouseOwner> getallhouseownerinfo();
	/**
	 * ��ȡ����ҵ�����ƣ���������ͬ��ֻ��ʾһ��
	 */
	List<String> getallonlyhouseownername();
	/**
	 * ����ҵ����Ϣ
	 * @param houseowner
	 */
	void updatehouseownerinfo(HouseOwner houseowner);

	/**
	 * ��ȡ�����пշ����¥�����
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
	List<String> getallnullhousehousenumber(@Param("buildNumber")String buildNumber,@Param("houseUnit")int houseUnit);
	/**
	 * ����ҵ����Ϣ
	 * @param houseownerifo
	 */
	void inserthouseownerinfo(HouseOwner houseownerifo);
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
