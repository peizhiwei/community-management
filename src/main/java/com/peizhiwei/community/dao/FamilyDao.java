package com.peizhiwei.community.dao;

import java.util.List;

import com.peizhiwei.community.entity.Family;

public interface FamilyDao {
	/**
	 * ��ѯ���м�ͥ��Ա����
	 */
	List<Family> getallfamilyinfo();
	/**
	 * ���ļ�ͥ��Ա��Ϣ
	 */
	void updatefamilyinfo(Family family);
	/**
	 * ������ͥ��Ա
	 */
	void insertfamilyinfo(Family family);
	/**
	 * ɾ����ͥ��Ա��Ϣ
	 */
	void deletefamilyinfo(int familyId);
}
