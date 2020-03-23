package com.peizhiwei.community.dao;

import java.util.List;

import com.peizhiwei.community.entity.PayType;

public interface PayTypeDao {
	/**
	 * 获取所有收费类型
	 * @return
	 */
	List<PayType> getallpaytype();
	/**
	 * 修改类别信息
	 * @param paytype
	 */
	void updatepaytypeinfo(PayType paytype);
	/**
	 * 删除类别信息
	 * @param payTypeId
	 */
	void deletepaytypeinfo(int payTypeId);
	/**
	 * 新增收费类别
	 * @param paytype
	 */
	void insertpaytypeinfo(PayType paytype);
	/**
	 * 根据收费类别名称查询表中是否已存在
	 * @param payTypeName
	 * @return
	 */
	boolean selectpaytypename(String payTypeName);
}
