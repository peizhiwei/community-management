package com.peizhiwei.community.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peizhiwei.community.admin.dao.FamilyDao;
import com.peizhiwei.community.admin.dao.HouseInfoDao;
import com.peizhiwei.community.admin.dao.HouseOwnerDao;
import com.peizhiwei.community.admin.dao.ParkingDao;
import com.peizhiwei.community.admin.entity.HouseOwner;
import com.peizhiwei.community.admin.service.HouseOwnerService;
@Service
public class HouseOwnerServiceImpl implements HouseOwnerService {
	@Autowired
	HouseOwnerDao houseownerdao;
	@Autowired
	FamilyDao familydao;
	@Autowired
	ParkingDao parkingdao;
	@Autowired
	HouseInfoDao houseinfodao;
	
	/**
	 * ��ȡ����ҵ����Ϣ
	 */
	@Override
	public List<HouseOwner> getallhouseownerinfo() {
		List<HouseOwner> listhouseowner = houseownerdao.getallhouseownerinfo();
		return listhouseowner;
	}

	@Override
	public void updatehouseownerinfo(HouseOwner houseowner) {
		houseownerdao.updatehouseownerinfo(houseowner);
	}

	@Override
	public void inserthouseownerinfo(HouseOwner houseownerinfo) {
		houseownerdao.inserthouseownerinfo(houseownerinfo);
	}
	/**
	 * ��ȡ����ҵ�����ƣ�ȥ���ظ���
	 */
	@Override
	public List<HouseOwner> getallonluhouseownername() {
		List<HouseOwner> onluhouseownername = houseownerdao.getallonlyhouseownername();
		return onluhouseownername;
	}

	@Override
	public HouseOwner gethouseownerinfoaccordinghousenumber(String houseNumber) {
		HouseOwner houseonwer = houseownerdao.gethouseownerinfoaccordinghousenumber(houseNumber);
		return houseonwer;
	}
	/**
	 * ҵ��Ǩ����ͬʱɾ����ͥ��Ա��Ϣ��ͣ��λ��Ϣ
	 */
	@Override
	@Transactional
	public void deleteowner(int ownerId) {
		try {
			familydao.deletefamilyofowner(ownerId);//��ɾ����ҵ���ļ�ͥ��Ա��Ϣ
			parkingdao.takebackownerofparking(ownerId);//�ջظ�ҵ����ͣ��λ��Ϣ
			houseinfodao.updatehouseinfoofowner(ownerId);//��������Ϣ�и�ҵ����id��Ϊ�գ���סʱ��Ϊ�գ�״̬��Ϊ0����
			houseownerdao.deleteowner(ownerId);//ɾ��ҵ��
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
