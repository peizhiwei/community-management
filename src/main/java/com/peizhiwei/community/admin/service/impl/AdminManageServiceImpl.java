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
	 * ��ȡ���й���Ա��Ϣ
	 */
	@Override
	public List<Admin> getalladmininfo() {
		List<Admin> listadmininfo = adminmanagedao.getalladmininfo();
		return listadmininfo;
	}
	/**
	 * �޸Ĺ���Ա��Ϣ
	 */
	@Override
	public void updateadmininfo(Admin admin) {
		adminmanagedao.updateadmininfo(admin);
	}
	/**
	 * ��ѯ����Ĺ���Ա����Ƿ���ڣ����ڷ���true,�����ڷ���false
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
	 * ��������Ա
	 */
	@Override
	public void insertadmininfo(Admin admin) {
		adminmanagedao.insertadmininfo(admin);
	}
	/**
	 * ��ѯ����Ա�������һ��
	 */
	@Override
	public String selectmaxadminnumber() {
		String maxadminnumber = adminmanagedao.selectmaxadminnumber();
		return maxadminnumber;
	}
	/**
	 * ɾ������Ա
	 */
	@Override
	public void deleteadmininfo(int adminId) {
		adminmanagedao.deleteadmininfo(adminId);
	}
	

}
