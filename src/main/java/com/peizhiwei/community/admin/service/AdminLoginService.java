package com.peizhiwei.community.admin.service;

import com.peizhiwei.community.admin.entity.Admin;

public interface AdminLoginService {
	Admin checkAdminLogin(String username,String password);
}
