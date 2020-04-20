package com.peizhiwei.community.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.admin.entity.HouseOwner;

public interface HouseOwnerDao {
	/**
	 * ��ҳ��ѯ����ȡ����ҵ����Ϣ
	 * @return
	 */
	List<HouseOwner> pagegetallhouseownerinfo(Map<String, Object> params);
	long count();
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
	 * ����¥����ţ���Ԫ�ţ�����ţ���ѯ�÷����Ƿ������˾�ס
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @return
	 */
	int checkhouseisnull(@Param("buildNumber")String buildNumber,@Param("houseUnit")int houseUnit,@Param("houseNumber")String houseNumber);
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
	 * ģ����ѯҵ����Ϣ,��ҳ��ѯ
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @param ownerName
	 * @param ownerPhone
	 * @return
	 */
	List<HouseOwner> gethouseownerinfolike(@Param("buildNumber")String buildNumber,@Param("houseUnit")String houseUnit,
			@Param("houseNumber")String houseNumber,@Param("ownerName")String ownerName,@Param("ownerPhone")String ownerPhone,@Param("params")Map<String, Object> params);
	/**
	 * ģ����ѯ�ܼ�¼��
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @param ownerName
	 * @param ownerPhone
	 * @return
	 */
	long likecount(@Param("buildNumber")String buildNumber,@Param("houseUnit")String houseUnit,
			@Param("houseNumber")String houseNumber,@Param("ownerName")String ownerName,@Param("ownerPhone")String ownerPhone);
}
