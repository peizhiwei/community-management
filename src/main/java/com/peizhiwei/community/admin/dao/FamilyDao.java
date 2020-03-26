package com.peizhiwei.community.admin.dao;

import java.util.List;

import com.peizhiwei.community.admin.entity.Family;

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
	/**
	 * ҵ��Ǩ��ʱɾ����ͥ��Ա��Ϣ
	 * @param ownerId
	 */
	void deletefamilyofowner(int ownerId);
}
