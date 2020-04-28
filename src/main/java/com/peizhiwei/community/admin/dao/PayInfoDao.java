package com.peizhiwei.community.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.admin.entity.PayInfo;
import com.peizhiwei.community.admin.entity.PayInfoDetails;

public interface PayInfoDao {
	/**
	 * 获取所有的缴费信息
	 * @return
	 */
	List<PayInfo> pagegetallpayinfo(Map<String, Object> params);
	long count();
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
	/**
	 * 批量删除缴费信息
	 * @param listpayInfoId
	 */
	void checkdelete(int[] listpayInfoId);
	/**
	 * 删除缴费信息时，批量删除缴费详情
	 * @param listpayInfoId
	 */
	void batchdeletepaydetailsofpayinfo(int[] listpayInfoId);
	/**
	 * 根据缴费项目查询缴费信息
	 * @param payTypeName
	 * @return
	 */
	List<PayInfo> selectpayinfoaccordingpaytypename(@Param("params")Map<String, Object> params,@Param("payTypeName")String payTypeName);
	long likecount(String payTypeName);
	/**
	 * 根据年月查询缴费信息
	 * @param params
	 * @param payInfoStartTime
	 * @return
	 */
	List<PayInfo> selectpayinfoaccordingpayintostarttime(@Param("params")Map<String, Object> params,@Param("year")String year,@Param("month")String month);
	long likecount2(@Param("year")String year,@Param("month")String month);
}
