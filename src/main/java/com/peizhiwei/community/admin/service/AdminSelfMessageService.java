package com.peizhiwei.community.admin.service;

import com.peizhiwei.community.admin.entity.Admin;

public interface AdminSelfMessageService {
	/**
	 * ����Ա�޸ĸ�����Ϣ
	 * @param admin
	 */
	void updateadmininfo(Admin admin);
	/**
	 * ����Ա�޸�����
	 * @param admin
	 */
	void updateadminpassword(Admin admin);
	/**
	 * ��ѯ������ֻ����Ƿ��Ѵ���
	 * @param adminPhone
	 * @return
	 */
	boolean selectadminphone(String adminPhone);
	/**
	 * �����ֻ���
	 * @param admin
	 */
	void updateadminphone(Admin admin);
}
