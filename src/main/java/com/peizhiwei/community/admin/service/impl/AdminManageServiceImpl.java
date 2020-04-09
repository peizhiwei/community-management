package com.peizhiwei.community.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peizhiwei.community.admin.dao.AdminManageDao;
import com.peizhiwei.community.admin.entity.Admin;
import com.peizhiwei.community.admin.service.AdminManageService;
@Service
@Transactional(rollbackFor = Exception.class)
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
	/**
	 * 修改管理员信息
	 */
	@Override
	public void updateadmininfo(Admin admin) {
		adminmanagedao.updateadmininfo(admin);
	}
	/**
	 * 查询输入的管理员编号是否存在，存在返回true,不存在返回false
	 */
	@Override
	public boolean selectnumberofadmin(String adminNumber) {
		Admin admin = new Admin();
		admin = adminmanagedao.selectnumberofadmin(adminNumber);
		if(admin!=null) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 新增管理员
	 */
	@Override
	public void insertadmininfo(Admin admin) {
		adminmanagedao.insertadmininfo(admin);
	}
	/**
	 * 查询管理员编号最大的一个
	 */
	@Override
	public String selectmaxadminnumber() {
		String maxadminnumber = adminmanagedao.selectmaxadminnumber();
		return maxadminnumber;
	}
	/**
	 * 删除管理员
	 */
	@Override
	public void deleteadmininfo(int adminId) {
		adminmanagedao.deleteadmininfo(adminId);
	}
	

}
