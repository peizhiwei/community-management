package com.peizhiwei.community.admin.dao;

import com.peizhiwei.community.admin.entity.Admin;

public interface AdminSelfMessageDao {
	/**
	 * 管理员修改个人信息
	 * @param admin
	 */
	void updateadmininfo(Admin admin);
}
