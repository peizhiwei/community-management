package com.peizhiwei.community.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.peizhiwei.community.util.Pager;
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
	 * ��ҳ��ѯ����ȡ����ҵ����Ϣ
	 */
	@Override
	public Pager<HouseOwner> pagegetallhouseownerinfo(int page,int size){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size", size);
		List<HouseOwner> listhouseowner = houseownerdao.pagegetallhouseownerinfo(params);
		Pager<HouseOwner> pager = new Pager<HouseOwner>();
		pager.setRows(listhouseowner);
		pager.setTotal(houseownerdao.count());
		return pager;
	}
	
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
	 * ����¥����ţ���Ԫ�ţ�����Ų�ѯ�÷����Ƿ����˾�ס
	 */
	@Override
	public int checkhouseisnull(String buildNumber, int houseUnit, String houseNumber) {
		return houseownerdao.checkhouseisnull(buildNumber, houseUnit, houseNumber);
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
	public List<String> getallonluhouseownername() {
		List<String> onluhouseownername = houseownerdao.getallonlyhouseownername();
		return onluhouseownername;
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
	/**
	 * ģ����ѯҵ����Ϣ
	 */
	@Override
	public Pager<HouseOwner> gethouseownerinfolike(String buildNumber, String houseUnit, String houseNumber,
			String ownerName, String ownerPhone,int page,int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size", size);
		List<HouseOwner> listhouseowner = houseownerdao.gethouseownerinfolike(buildNumber, houseUnit, houseNumber, ownerName, ownerPhone,params);
		Pager<HouseOwner> pager = new Pager<HouseOwner>();
		pager.setRows(listhouseowner);
		pager.setTotal(houseownerdao.likecount(buildNumber, houseUnit, houseNumber, ownerName, ownerPhone));
		return pager;
	}
	
}
