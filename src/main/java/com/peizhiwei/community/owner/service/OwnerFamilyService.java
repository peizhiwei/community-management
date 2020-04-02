package com.peizhiwei.community.owner.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.Family;

public interface OwnerFamilyService {
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
