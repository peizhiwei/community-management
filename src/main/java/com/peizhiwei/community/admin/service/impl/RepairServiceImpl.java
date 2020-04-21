package com.peizhiwei.community.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.admin.dao.RepairDao;
import com.peizhiwei.community.admin.entity.Repair;
import com.peizhiwei.community.admin.service.RepairService;
import com.peizhiwei.community.util.Pager;

@Service
public class RepairServiceImpl implements RepairService {
	@Autowired
	RepairDao repairdao;
	/**
	 * ��ҳ��ѯ���б�����Ϣ
	 */
	@Override
	public Pager<Repair> pagegetallrepairinfo(int page, int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size", size);
		List<Repair> listrepair = repairdao.pagegetallrepairinfo(params);
		Pager<Repair> pager = new Pager<Repair>();
		pager.setRows(listrepair);
		pager.setTotal(repairdao.count());
		return pager;
	}
	/**
	 * ��ȡ���б�����Ϣ
	 */
	@Override
	public List<Repair> getallrepairinfo() {
		List<Repair> listrepairinfo = repairdao.getallrepairinfo();
		return listrepairinfo;
	}
	/**
	 * ��������Ϣ����������ˣ��޸ı���״̬Ϊ1��������
	 */
	@Override
	public void acceptancerepair(Repair repairId) {
		repairdao.acceptancerepair(repairId);		
	}
	/**
	 * �ѽ��������Ϣ���޸�״̬Ϊ2�ѽ��
	 */
	@Override
	public void settledrepair(Repair repair) {
		repairdao.settledrepair(repair);
		
	}
	/**
	 *  ���ݱ�����Ϣ��id��ȡ������id
	 */
	@Override
	public int getrepairinfo(int repairId) {
		int adminid = repairdao.getrepairinfo(repairId);
		return adminid;
	}
	/**
	 * ���ݱ�����Ϣidɾ��������Ϣ
	 */
	@Override
	public void deleterepair(int repairId) {
		repairdao.deleterepair(repairId);
	}
	/**
	 * ����ҵ��idɾ����ҵ�������б�����Ϣ
	 */
	@Override
	public void deleterepairofowner(int ownerId) {
		repairdao.deleterepairofowner(ownerId);
	}
	/**
	 * ��ҳ��ģ����ѯ������Ϣ��(�����ˣ�¥����ţ���Ԫ�ţ�����ţ�������Ʒ)
	 */
	@Override
	public Pager<Repair> getrepairinfolike(String ownerName, String buildNumber, String houseUnit, String houseNumber,
			String repairGoods,int page,int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size", size);
		List<Repair> listrepair = repairdao.getrepairinfolike(ownerName, buildNumber, houseUnit, houseNumber, repairGoods, params);
		Pager<Repair> pager = new Pager<Repair>();
		pager.setRows(listrepair);
		pager.setTotal(repairdao.likecount(ownerName, buildNumber, houseUnit, houseNumber, repairGoods));
		return pager;
	}
	/**
	 * ����ɾ��������Ϣ
	 */
	@Override
	public void checkdelete(int[] listrepairId) {
		repairdao.checkdelete(listrepairId);
	}
	
}
