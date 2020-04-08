package com.peizhiwei.community.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.admin.dao.AdminManageDao;
import com.peizhiwei.community.admin.entity.Admin;
import com.peizhiwei.community.admin.service.AdminManageService;
@Service
public class AdminManageServiceImpl implements AdminManageService {

	@Autowired
	AdminManageDao adminmanagedao;
	
	/**
	 * 获取所有管理员信息
	 */
	@Override
	public List<Admin> getalladmininfo() {
		List<Admin> listadmininfo = adminmanagedao.getalladmininfo();
		return listadmininfo;
	}

}
