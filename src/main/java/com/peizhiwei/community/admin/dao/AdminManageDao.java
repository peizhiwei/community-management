package com.peizhiwei.community.admin.dao;

import java.util.List;

import com.peizhiwei.community.admin.entity.Admin;

public interface AdminManageDao {
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
	 * ��ѯ����Ĺ���Ա����Ƿ����
	 * @param adminNumber
	 * @return
	 */
	Admin selectnumberofadmin(String adminNumber);
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
	/**
	 * ����ɾ������Ա
	 * @param listadminId
	 */
	void checkdelete(int[] listadminId);
}
