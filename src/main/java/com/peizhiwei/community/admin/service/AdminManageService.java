package com.peizhiwei.community.admin.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.Admin;

public interface AdminManageService {

	/**
	 * 获取所有管理员信息
	 * @return
	 */
	List<Admin> getalladmininfo();
	
	/**
	 * 修改管理员信息
	 * @param admin
	 */
	void updateadmininfo(Admin admin);
	
	/**
	 * 查询输入的管理员编号是否存在,存在返回true,不存在返回false
	 * @param adminNumber
	 * @return
	 */
	boolean selectnumberofadmin(String adminNumber);
	/**
	 * 新增管理员
	 * @param admin
	 */
	void insertadmininfo(Admin admin);
	/**
	 * 查询管理员编号最大的一个
	 * @return
	 */
	String selectmaxadminnumber();
	/**
	 * 删除管理员
	 * @param adminId
	 */
	void deleteadmininfo(int adminId);
}
