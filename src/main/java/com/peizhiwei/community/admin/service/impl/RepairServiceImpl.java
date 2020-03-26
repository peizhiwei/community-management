package com.peizhiwei.community.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.admin.dao.RepairDao;
import com.peizhiwei.community.admin.entity.Repair;
import com.peizhiwei.community.admin.service.RepairService;

@Service
public class RepairServiceImpl implements RepairService {
	@Autowired
	RepairDao repairdao;
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
	
	
	
}
