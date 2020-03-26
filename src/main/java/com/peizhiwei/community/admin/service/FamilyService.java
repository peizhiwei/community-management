package com.peizhiwei.community.admin.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.Family;

public interface FamilyService {
	/**
	 * 查询所有家庭成员信息
	 */
	List<Family> getallfamilyinfo();
	/**
	 * 更新家庭成员信息
	 */
	void updatefamilyinfo(Family family);
	/**
	 * 新增家庭成员
	 */
	void insertfamilyinfo(Family family);
	/**
	 * 删除家庭成员
	 */
	void deletefamilyinfo(int familyId);
	/**
	 * 业主迁出时删除家庭成员信息
	 * @param ownerId
	 */
	void deletefamilyofowner(int ownerId);
}
