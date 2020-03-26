package com.peizhiwei.community.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.admin.entity.PayInfo;
import com.peizhiwei.community.admin.entity.PayInfoDetails;

public interface PayInfoDao {
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
	 * 删除缴费信息时，同时删除缴费详情
	 * @param payInfoId
	 */
	void deletepaydetailsofpayinfo(int payInfoId);
	/**
	 * 修改缴费信息
	 * @param payinfo
	 */
	void updatepayinfo(PayInfo payinfo);
}
