package com.peizhiwei.community.admin.service;

import com.peizhiwei.community.admin.entity.Admin;

public interface AdminLoginService {
	Admin checkAdminLogin(String username,String password);
	/**
	 * 查询数据库是否存在管理员,存在返回true,不存在返回false
	 * @return
	 */
	boolean checkadminexit();
}
