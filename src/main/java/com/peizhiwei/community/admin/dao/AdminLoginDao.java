package com.peizhiwei.community.admin.dao;

import java.util.List;

import com.peizhiwei.community.admin.entity.Admin;

public interface AdminLoginDao {
	Admin selectUsername(String username);
	/**
	 * ��ѯ���ݿ��Ƿ���ڹ���Ա
	 * @return
	 */
	List<Admin> checkadminexit();
}
