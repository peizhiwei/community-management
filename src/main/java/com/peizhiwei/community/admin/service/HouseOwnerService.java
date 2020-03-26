package com.peizhiwei.community.admin.service;

import java.util.List;

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
	 * ����ҵ����Ϣ
	 * @param houseownerinfo
	 */
	void inserthouseownerinfo(HouseOwner houseownerinfo);
	/**
	 * ҵ��Ǩ��
	 * @param ownerId
	 */
	void deleteowner(int ownerId);
}
