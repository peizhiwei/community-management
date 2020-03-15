package com.peizhiwei.community.service;

import java.util.List;

import com.peizhiwei.community.entity.HouseOwner;

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
}
