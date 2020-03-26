package com.peizhiwei.community.admin.dao;

import java.util.List;

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
	List<HouseOwner> getallonlyhouseownername();
	/**
	 * ���ݷ���Ų�ѯҵ����Ϣ
	 */
	HouseOwner gethouseownerinfoaccordinghousenumber(String houseNumber);
	/**
	 * ����ҵ����Ϣ
	 * @param houseowner
	 */
	void updatehouseownerinfo(HouseOwner houseowner);

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
}
