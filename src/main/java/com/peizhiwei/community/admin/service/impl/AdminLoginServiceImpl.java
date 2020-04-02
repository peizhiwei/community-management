package com.peizhiwei.community.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.admin.dao.AdminLoginDao;
import com.peizhiwei.community.admin.entity.Admin;
import com.peizhiwei.community.admin.service.AdminLoginService;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {
	@Autowired
	AdminLoginDao adminLoginDao;

	@Override
	public Admin checkAdminLogin(String username, String password) {
		Admin admin = new Admin();
		admin = adminLoginDao.selectUsername(username);
		if (admin != null && admin.getAdminPassword().equals(password)) {
			return admin;
		} else {
			return null;
		}
	}

}
