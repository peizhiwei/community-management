package com.peizhiwei.community.owner.dao;

import java.util.List;

import com.peizhiwei.community.admin.entity.Family;

public interface OwnerFamilyDao {
	/**
	 * 根据业主id查询业主的家庭成员
	 * @param ownerId
	 * @return
	 */
	List<Family> selectfamilyofowner(int ownerId);
	/**
	 * 新增家庭成员
	 * @param family
	 */
	void insertfamily(Family family);
}
