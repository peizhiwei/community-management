package com.peizhiwei.community.owner.service;

import com.peizhiwei.community.admin.entity.HouseOwner;

public interface OwnerLoginService {
	/**
	 * ��֤��¼
	 * @param username
	 * @param password
	 * @return
	 */
	HouseOwner checkownerlogin(String username,String password);
}
