package com.peizhiwei.community.admin.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.Admin;

public interface AdminManageService {

	/**
	 * ��ȡ���й���Ա��Ϣ
	 * @return
	 */
	List<Admin> getalladmininfo();
	
	/**
	 * �޸Ĺ���Ա��Ϣ
	 * @param admin
	 */
	void updateadmininfo(Admin admin);
	
	/**
	 * ��ѯ����Ĺ���Ա����Ƿ����,���ڷ���true,�����ڷ���false
	 * @param adminNumber
	 * @return
	 */
	boolean selectnumberofadmin(String adminNumber);
	/**
	 * ��������Ա
	 * @param admin
	 */
	void insertadmininfo(Admin admin);
	/**
	 * ��ѯ����Ա�������һ��
	 * @return
	 */
	String selectmaxadminnumber();
	/**
	 * ɾ������Ա
	 * @param adminId
	 */
	void deleteadmininfo(int adminId);
}
