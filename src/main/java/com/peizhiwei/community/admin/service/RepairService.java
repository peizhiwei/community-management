package com.peizhiwei.community.admin.service;

import java.util.List;


import com.peizhiwei.community.admin.entity.Repair;
import com.peizhiwei.community.util.Pager;

public interface RepairService {
	/**
	 * ��ҳ��ѯ���б�����Ϣ
	 * @param page
	 * @param size
	 * @return
	 */
	Pager<Repair> pagegetallrepairinfo(int page,int size);
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
	/**
	 * ���ݱ�����Ϣ��idɾ��������Ϣ
	 * @param repairId
	 */
	void deleterepair(int repairId);
	/**
	 * ����ҵ��idɾ����ҵ�������б�����Ϣ
	 * @param ownerId
	 */
	void deleterepairofowner(int ownerId);
	
	/**
	 * ģ����ѯ������Ϣ��(�����ˣ�¥����ţ���Ԫ�ţ�����ţ�������Ʒ)
	 * @param ownerName
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @param repairGoods
	 * @return
	 */
	Pager<Repair> getrepairinfolike(String ownerName,String buildNumber,String houseUnit,String houseNumber,String repairGoods,int page,int size);
	/**
	 * ����ɾ��������Ϣ
	 * @param listrepairId
	 */
	void checkdelete(int[] listrepairId);
}
