package com.peizhiwei.community.admin.dao;

import com.peizhiwei.community.admin.entity.Admin;

public interface AdminSelfMessageDao {
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
	Admin selectadminphone(String adminPhone);
	/**
	 * �����ֻ���
	 * @param admin
	 */
	void updateadminphone(Admin admin);
	
}
