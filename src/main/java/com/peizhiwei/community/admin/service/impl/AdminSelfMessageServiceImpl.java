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
	/**
	 * 管理员修改密码
	 */
	@Override
	public void updateadminpassword(Admin admin) {
		adminselfmessagedao.updateadminpassword(admin);
	}
	/**
	 * 查询输入的手机号是否已存在
	 */
	@Override
	public boolean selectadminphone(String adminPhone) {
		Admin admin = adminselfmessagedao.selectadminphone(adminPhone);
		if(admin==null) {//手机号不存在
			return false;
		}else {//手机号存在
			return true;
		}
	}
	/**
	 * 更换手机号
	 */
	@Override
	public void updateadminphone(Admin admin) {
		adminselfmessagedao.updateadminphone(admin);
	}

}
