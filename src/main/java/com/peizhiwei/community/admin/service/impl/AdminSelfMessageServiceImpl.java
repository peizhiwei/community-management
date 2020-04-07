package com.peizhiwei.community.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.admin.dao.AdminSelfMessageDao;
import com.peizhiwei.community.admin.entity.Admin;
import com.peizhiwei.community.admin.service.AdminSelfMessageService;

@Service
public class AdminSelfMessageServiceImpl implements AdminSelfMessageService {
	@Autowired
	AdminSelfMessageDao adminselfmessagedao;
	
	/**
	 * 管理员修改个人信息
	 */
	@Override
	public void updateadmininfo(Admin admin) {
		adminselfmessagedao.updateadmininfo(admin);
	}

}
