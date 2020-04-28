package com.peizhiwei.community.admin.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.HouseOwner;
import com.peizhiwei.community.util.Pager;

public interface HouseOwnerService {
	/**
	 * ��ҳ��ѯ����ȡ����ҵ����Ϣ
	 * @return
	 */
	Pager<HouseOwner> pagegetallhouseownerinfo(int page,int size);
	/**
	 * ��ȡ����ҵ����Ϣ
	 * @return
	 */
	List<HouseOwner> getallhouseownerinfo();
	/**
	 * ��ȡ����ҵ�����ƣ�ȥ���ظ���
	 */
	List<String> getallonluhouseownername();
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
	 * ����¥����ţ���Ԫ�ţ�����ţ���ѯ�÷����Ƿ������˾�ס
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @return
	 */
	int checkhouseisnull(String buildNumber,int houseUnit,String houseNumber);
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
	Pager<HouseOwner> gethouseownerinfolike(String buildNumber,String houseUnit,
			String houseNumber,String ownerName,String ownerPhone,int page,int size);
}
