package com.peizhiwei.community.admin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.admin.entity.PayInfo;
import com.peizhiwei.community.admin.entity.PayInfoDetails;

public interface PayInfoService {
	/**
	 * 获取所有的缴费信息
	 * @return
	 */
	List<PayInfo> getallpayinfo();
	/**
	 * 发布新的缴费信息
	 * @param payinfo
	 */
	boolean insertnewpayinfo(PayInfo payinfo);
	/**
	 * 在发布缴费信息的同时，批量插入缴费详情
	 * @param houseinfolist
	 */
	void insertpayinfodetailslist(@Param("list")List<PayInfoDetails> list);
	/**
	 * 删除缴费信息
	 * @param payInfoId
	 */
	void deletepayinfo(int payInfoId);
	/**
	 * 修改缴费信息
	 * @param payinfo
	 */
	void updatepayinfo(PayInfo payinfo);
	/**
	 * 批量删除缴费信息
	 * @param listpayInfoId
	 */
	void checkdelete(int[] listpayInfoId);
}
