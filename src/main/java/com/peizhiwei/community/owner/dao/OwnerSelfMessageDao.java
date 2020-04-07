package com.peizhiwei.community.owner.dao;

import com.peizhiwei.community.admin.entity.HouseOwner;

public interface OwnerSelfMessageDao {
	/**
	 * 获取业主的所有个人信息
	 * @return
	 */
	HouseOwner getmessageofowner(int ownerId);
	/**
	 * 业主修改个人信息
	 * @param owner
	 */
	void changeownerinfo(HouseOwner owner);
	/**
	 * 用户修改修改密码
	 * @param oldPassword
	 * @param ownerId
	 */
	void updateownerpassword(HouseOwner owner);
	/**
	 * 更换手机号
	 * @param owner
	 */
	void updateownerphone(HouseOwner owner);
	/**
	 * 查询输入的手机号是否已存在
	 * @param ownerPhone
	 * @return
	 */
	HouseOwner selectownerphone(String ownerPhone);
}
