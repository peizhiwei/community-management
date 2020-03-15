package com.peizhiwei.community.dao;

import java.util.List;

import com.peizhiwei.community.entity.Family;

public interface FamilyDao {
	/**
	 * 查询所有家庭成员信心
	 */
	List<Family> getallfamilyinfo();
	/**
	 * 更改家庭成员信息
	 */
	void updatefamilyinfo(Family family);
	/**
	 * 新增家庭成员
	 */
	void insertfamilyinfo(Family family);
	/**
	 * 删除家庭成员信息
	 */
	void deletefamilyinfo(int familyId);
}
