package com.peizhiwei.community.owner.dao;

import com.peizhiwei.community.admin.entity.HouseOwner;

public interface OwnerLoginDao {
	/**
	 * �����˺Ų�ѯ�û�
	 * @param username
	 * @return
	 */
	HouseOwner selectownerusername(String username);
}
