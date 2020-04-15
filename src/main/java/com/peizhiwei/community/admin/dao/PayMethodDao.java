package com.peizhiwei.community.admin.dao;

import java.util.List;

import com.peizhiwei.community.admin.entity.PayMethod;

public interface PayMethodDao {
	/**
	 * 获取所有的缴费方式
	 * @return
	 */
	List<PayMethod> getallpaymethodinfo();
}
