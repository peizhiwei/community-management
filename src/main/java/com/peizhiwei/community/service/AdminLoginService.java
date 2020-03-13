package com.peizhiwei.community.service;

import com.peizhiwei.community.entity.Admin;

public interface AdminLoginService {
	Admin checkAdminLogin(String username,String password);
}
