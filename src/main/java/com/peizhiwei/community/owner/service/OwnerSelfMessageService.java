package com.peizhiwei.community.owner.service;

import com.peizhiwei.community.admin.entity.HouseOwner;

public interface OwnerSelfMessageService {
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
	 * @param newPassword
	 * @param ownerId
	 */
	void updateownerpassword(HouseOwner owner);
	/**
	 * ��ѯ������ֻ����Ƿ��Ѵ���
	 * @param ownerPhone
	 * @return
	 */
	boolean selectownerphone(String ownerPhone);
	/**
	 * �����ֻ���
	 * @param owner
	 */
	void updateownerphone(HouseOwner owner);
}
