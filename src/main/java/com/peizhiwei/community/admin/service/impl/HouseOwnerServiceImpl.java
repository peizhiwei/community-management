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
	 * 获取所有业主信息
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
	 * 获取所有业主名称，去掉重复的
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
	 * 业主迁出，同时删除家庭成员信息，停车位信息
	 */
	@Override
	@Transactional
	public void deleteowner(int ownerId) {
		try {
			familydao.deletefamilyofowner(ownerId);//先删除该业主的家庭成员信息
			parkingdao.takebackownerofparking(ownerId);//收回该业主的停车位信息
			houseinfodao.updatehouseinfoofowner(ownerId);//将房间信息中该业主的id置为空，入住时间为空，状态置为0待售
			houseownerdao.deleteowner(ownerId);//删除业主
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
