package com.peizhiwei.community.dao;

import com.peizhiwei.community.entity.Admin;

public interface AdminLoginDao {
	Admin selectUsername(String username);
}
