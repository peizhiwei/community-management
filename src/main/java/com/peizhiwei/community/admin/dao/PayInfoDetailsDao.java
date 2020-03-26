package com.peizhiwei.community.admin.dao;

import java.util.List;

import com.peizhiwei.community.admin.entity.PayInfoDetails;

public interface PayInfoDetailsDao {
	/**
	 * 获取所有缴费信息
	 * @return
	 */
	List<PayInfoDetails> getallpayinfo();
	/**
	 * 已缴费，修改缴费状态为已缴费，由管理员操作的缴费方式默认为现金
	 * @param payinfo
	 */
	void updatepaystate(PayInfoDetails payinfo);
	/**
	 * 根据缴费方式名称查找对应的id
	 * @param methodName
	 * @return
	 */
	int getpaymethodid(String methodName);
}
