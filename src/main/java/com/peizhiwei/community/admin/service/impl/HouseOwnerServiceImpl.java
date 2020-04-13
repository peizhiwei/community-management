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
import com.peizhiwei.community.admin.service.ComplaintService;
import com.peizhiwei.community.admin.service.HouseOwnerService;
import com.peizhiwei.community.admin.service.PayInfoDetailsService;
import com.peizhiwei.community.admin.service.PayInfoSumService;
import com.peizhiwei.community.admin.service.RepairService;
@Service
@Transactional(rollbackFor = Exception.class)
public class HouseOwnerServiceImpl implements HouseOwnerService {
	@Autowired
	HouseOwnerDao houseownerdao;
	@Autowired
	FamilyDao familydao;
	@Autowired
	ParkingDao parkingdao;
	@Autowired
	HouseInfoDao houseinfodao;
	@Autowired
	ComplaintService complaintservice;
	@Autowired
	RepairService repairservice;
	@Autowired
	PayInfoDetailsService payinfodetailsservice;
	@Autowired
	PayInfoSumService payinfosumservice;
	
	/**
	 * ��ȡ����ҵ����Ϣ
	 */
	@Override
	public List<HouseOwner> getallhouseownerinfo() {
		List<HouseOwner> listhouseowner = houseownerdao.getallhouseownerinfo();
		return listhouseowner;
	}
	/**
	 * ����ҵ����Ϣ
	 */
	@Override
	public void updatehouseownerinfo(HouseOwner houseowner) {
		houseownerdao.updatehouseownerinfo(houseowner);
	}
	/**
	 * ��ȡ���л��пշ����¥�����
	 */
	@Override
	public List<String> getallhavenullhousebuildNumber() {
		List<String> listbuildnumber = houseownerdao.getallhavenullhousebuildNumber();
		return listbuildnumber;
	}
	/**
	 * ����¥����Ų�ѯ��¥���л��пշ���ĵ�Ԫ
	 */
	@Override
	public List<Integer> getallhavenullhousehouseunit(String buildNumber) {
		List<Integer> listhouseunit = houseownerdao.getallhavenullhousehouseunit(buildNumber);
		return listhouseunit;
	}
	/**
	 * ����¥���ţ���Ԫ�ţ���ѯ�շ���ķ����
	 */
	@Override
	public List<String> getallnullhousehousenumber(String buildNumber, int houseUnit) {
		List<String> listhousenumber = houseownerdao.getallnullhousehousenumber(buildNumber, houseUnit);
		return listhousenumber;
	}
	/**
	 * ����ҵ��
	 */
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
	/**
	 * ���ݷ���Ų�ѯҵ��
	 */
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
			complaintservice.deletecomplaintofowner(ownerId);//ɾ����ҵ��������Ͷ����Ϣ
			repairservice.deleterepairofowner(ownerId);//ɾ����ҵ����ż�б�����Ϣ
			payinfodetailsservice.deletepayinfodetailsofowner(ownerId);//ɾ����ҵ�������нɷ���Ϣ
			payinfosumservice.deletepayinfosumofowner(ownerId);//ɾ����ҵ���Ľɷѻ�����Ϣ
			houseownerdao.deleteowner(ownerId);//ɾ��ҵ��
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
