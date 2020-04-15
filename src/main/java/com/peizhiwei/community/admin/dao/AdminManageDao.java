package com.peizhiwei.community.admin.dao;

import java.util.List;

import com.peizhiwei.community.admin.entity.Admin;

public interface AdminManageDao {
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
	 * 查询输入的管理员编号是否存在
	 * @param adminNumber
	 * @return
	 */
	Admin selectnumberofadmin(String adminNumber);
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
	/**
	 * 批量删除管理员
	 * @param listadminId
	 */
	void checkdelete(int[] listadminId);
}
