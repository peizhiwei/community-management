package com.peizhiwei.community.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peizhiwei.community.admin.dao.AdminLoginDao;
import com.peizhiwei.community.admin.entity.Admin;
import com.peizhiwei.community.admin.service.AdminLoginService;

@Service
@Transactional(rollbackFor = Exception.class)
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
	/**
	 * 查询数据库中是否存在管理员，存在返回true,不存在返回false
	 */
	@Override
	public boolean checkadminexit() {
		List<Admin> admin = adminLoginDao.checkadminexit();
		if(admin.size()==0) {
			return false;
		}else {
			return true;
		}
	}

}
