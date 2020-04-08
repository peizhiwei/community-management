package com.peizhiwei.community.admin.service;

import com.peizhiwei.community.admin.entity.Admin;

public interface AdminSelfMessageService {
	/**
	 * 管理员修改个人信息
	 * @param admin
	 */
	void updateadmininfo(Admin admin);
	/**
	 * 管理员修改密码
	 * @param admin
	 */
	void updateadminpassword(Admin admin);
	/**
	 * 查询输入的手机号是否已存在
	 * @param adminPhone
	 * @return
	 */
	boolean selectadminphone(String adminPhone);
	/**
	 * 更换手机号
	 * @param admin
	 */
	void updateadminphone(Admin admin);
}
