package com.peizhiwei.community.owner.dao;

import java.util.List;

import com.peizhiwei.community.admin.entity.Family;

public interface OwnerFamilyDao {
	/**
	 * ����ҵ��id��ѯҵ���ļ�ͥ��Ա
	 * @param ownerId
	 * @return
	 */
	List<Family> selectfamilyofowner(int ownerId);
	/**
	 * ������ͥ��Ա
	 * @param family
	 */
	void insertfamily(Family family);
}
