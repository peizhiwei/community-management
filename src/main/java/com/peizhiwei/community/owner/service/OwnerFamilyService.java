package com.peizhiwei.community.owner.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.Family;

public interface OwnerFamilyService {
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
