package com.peizhiwei.community.service;

import java.util.List;

import com.peizhiwei.community.entity.PayMethod;

public interface PayMethodService {
	
	/**
	 * 获取所有的缴费方式
	 * @return
	 */
	List<PayMethod> getallpaymethodinfo();
	
	/**
	 * 修改支付方式
	 */
	void updatepaymethodinfo(PayMethod paymethod);
	
	/**
	 * 删除支付方式
	 * @param methodId
	 */
	void deletepaymethodinfo(int methodId);
	/**
	 * 新增支付方式
	 * @param paymethod
	 */
	void insertpaymethodinfo(PayMethod paymethod);
	/**
	 * 查询表中是否已存在该支付方式
	 * @param methodName
	 * @return
	 */
	boolean selectpaymethodname(String methodName);
}
