package com.peizhiwei.community.admin.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.Family;

public interface FamilyService {
	/**
	 * ��ѯ���м�ͥ��Ա��Ϣ
	 */
	List<Family> getallfamilyinfo();
	/**
	 * ���¼�ͥ��Ա��Ϣ
	 */
	void updatefamilyinfo(Family family);
	/**
	 * ������ͥ��Ա
	 */
	void insertfamilyinfo(Family family);
	/**
	 * ɾ����ͥ��Ա
	 */
	void deletefamilyinfo(int familyId);
	/**
	 * ҵ��Ǩ��ʱɾ����ͥ��Ա��Ϣ
	 * @param ownerId
	 */
	void deletefamilyofowner(int ownerId);
}
