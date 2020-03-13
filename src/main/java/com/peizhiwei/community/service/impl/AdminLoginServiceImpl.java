package com.peizhiwei.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.dao.AdminLoginDao;
import com.peizhiwei.community.entity.Admin;
import com.peizhiwei.community.service.AdminLoginService;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {
	@Autowired
	AdminLoginDao adminLoginDao;

	@Override
	public Admin checkAdminLogin(String username, String password) {
		Admin admin = new Admin();
		admin = adminLoginDao.selectUsername(username);
		if (admin != null && admin.getPassword().equals(password)) {
			return admin;
		} else {
			return null;
		}
	}

}
