package com.peizhiwei.community.admin.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.Repair;

public interface RepairService {
	/**
	 * ��ȡ���б�����Ϣ
	 * @return
	 */
	List<Repair> getallrepairinfo();
	/**
	 * ��������Ϣ����������ˣ��޸ı���״̬Ϊ1��������
	 * @param repairId
	 */
	void acceptancerepair(Repair repairId);
	/**
	 * �ѽ��������Ϣ���޸�״̬Ϊ2�ѽ��
	 * @param Repair
	 */
	void settledrepair(Repair repair);
	/**
	 * ���ݱ�����Ϣ��id��ȡ������id
	 * @param repairId
	 * @return
	 */
	int getrepairinfo(int repairId);
}
