package com.peizhiwei.community.service;

import java.util.List;

import com.peizhiwei.community.entity.Family;

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
}
