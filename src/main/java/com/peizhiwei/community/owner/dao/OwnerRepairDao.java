package com.peizhiwei.community.owner.dao;

import java.util.List;

import com.peizhiwei.community.admin.entity.Repair;

public interface OwnerRepairDao {
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
