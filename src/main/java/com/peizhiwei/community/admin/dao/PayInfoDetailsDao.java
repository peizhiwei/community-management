package com.peizhiwei.community.admin.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.admin.entity.PayInfoDetails;

public interface PayInfoDetailsDao {
	/**
	 * 分页获取所有缴费信息
	 * @return
	 */
	List<PayInfoDetails> pagegetallpayinfo(Map<String, Object> params);
	long count();
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
	/**
	 * 批量缴费
	 * @param listpayinfodetails
	 */
	void batchpaid(@Param("listpayinfodetails")List<PayInfoDetails> listpayinfodetails);
	/**
	 * 根据缴费项目查询缴费信息
	 * @param payTypeName
	 * @return
	 */
	List<PayInfoDetails> selectpayinfoaccordingpaytypename(@Param("params")Map<String, Object> params,@Param("payTypeName")String payTypeName);
	long likecount(String payTypeName);
	/**
	 * 根据年月查询缴费信息
	 * @param params
	 * @param payInfoStartTime
	 * @return
	 */
	List<PayInfoDetails> selectpayinfoaccordingpayintostarttime(@Param("params")Map<String, Object> params,@Param("year")String year,@Param("month")String month);
	long likecount2(@Param("year")String year,@Param("month")String month);
	/**
	 * 根据缴费状态查询缴费详情
	 * @return
	 */
	List<PayInfoDetails> selectpayinfoaccordingpaystate(@Param("params")Map<String, Object> params,@Param("payState")String payState);
	long likecount3(String payState);
}
