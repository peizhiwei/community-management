package com.peizhiwei.community.admin.dao;

import java.util.List;

import com.peizhiwei.community.admin.entity.Admin;

public interface AdminManageDao {
	/**
	 * 获取所有管理员信息
	 * @return
	 */
	List<Admin> getalladmininfo();
}
