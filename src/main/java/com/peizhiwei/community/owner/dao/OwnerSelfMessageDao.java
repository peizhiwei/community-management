package com.peizhiwei.community.owner.dao;

import com.peizhiwei.community.admin.entity.HouseOwner;

public interface OwnerSelfMessageDao {
	/**
	 * ��ȡҵ�������и�����Ϣ
	 * @return
	 */
	HouseOwner getmessageofowner(int ownerId);
	/**
	 * ҵ���޸ĸ�����Ϣ
	 * @param owner
	 */
	void changeownerinfo(HouseOwner owner);
	/**
	 * �û��޸��޸�����
	 * @param oldPassword
	 * @param ownerId
	 */
	void updateownerpassword(HouseOwner owner);
	/**
	 * �����ֻ���
	 * @param owner
	 */
	void updateownerphone(HouseOwner owner);
	/**
	 * ��ѯ������ֻ����Ƿ��Ѵ���
	 * @param ownerPhone
	 * @return
	 */
	HouseOwner selectownerphone(String ownerPhone);
}
