package com.peizhiwei.community.admin.service;

import java.math.BigDecimal;
import java.util.List;

import com.peizhiwei.community.admin.entity.PayInfoDetails;

public interface PayInfoDetailsService {
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
	/**
	 * 获取业主应缴金额总和
	 * @param ownerId
	 * @return
	 */
	BigDecimal getsumpaymoney(int ownerId);
	/**
	 * 获取业主已缴费金额
	 * @param ownerId
	 * @return
	 */
	BigDecimal getpaysumpid(int ownerId);
	/**
	 * 根据业主id删除该业主的所有缴费详情
	 * @param ownerId
	 */
	void deletepayinfodetailsofowner(int ownerId);
}
