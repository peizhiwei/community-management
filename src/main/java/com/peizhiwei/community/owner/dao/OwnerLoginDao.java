package com.peizhiwei.community.owner.dao;

import com.peizhiwei.community.admin.entity.HouseOwner;

public interface OwnerLoginDao {
	/**
	 * 根据账号查询用户
	 * @param username
	 * @return
	 */
	HouseOwner selectownerusername(String username);
}
