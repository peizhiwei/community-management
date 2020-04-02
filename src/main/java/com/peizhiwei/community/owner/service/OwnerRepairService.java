package com.peizhiwei.community.owner.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.Repair;

public interface OwnerRepairService {
	/**
	 * ����ҵ��id��ѯ��ҵ���ı�����Ϣ
	 * @param ownerId
	 * @return
	 */
	List<Repair> getrepairinfo(int ownerId);
	/**
	 * ����������Ϣ
	 * @param repair
	 */
	void insertrepair(Repair repair);
	/**
	 * �޸ı�����Ϣ
	 * @param repair
	 */
	void updaterepair(Repair repair);
}
