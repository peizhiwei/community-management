package com.peizhiwei.community.admin.dao;

import com.peizhiwei.community.admin.entity.Admin;

public interface AdminLoginDao {
	Admin selectUsername(String username);
}
