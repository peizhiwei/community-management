package com.peizhiwei.community.admin.dao;

import java.util.List;

import com.peizhiwei.community.admin.entity.Admin;

public interface AdminLoginDao {
	Admin selectUsername(String username);
	/**
	 * 查询数据库是否存在管理员
	 * @return
	 */
	List<Admin> checkadminexit();
}
