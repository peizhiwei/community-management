package com.peizhiwei.community.admin.dao;
/**
 * 缴费信息汇总dao层
 * @author PEIZHIWEI
 *
 */


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.admin.entity.PayInfoSum;

public interface PayInfoSumDao {
	/**
	 * 分页获取所有汇总信息
	 * @return
	 */
	List<PayInfoSum> pagegetallpayinfosum(Map<String, Object> params);
	long count();
	/**
	 * 获取所有汇总信息
	 * @return
	 */
	List<PayInfoSum> getallpayinfosum();
	/**
	 * 在插入业主的同时插入业主汇总信息
	 * @param payinfosum
	 */
	void insertpayinfosum(PayInfoSum payinfosum);
	/**
	 * 发布缴费信息时更新缴费汇总信息
	 * @param payinfosum
	 */
	void updatepayinfosum(@Param("list")List<PayInfoSum> list);
	/**
	 * 一键缴费
	 * @param payinfodetails
	 */
	void updatepayinfodetailsstate(PayInfoDetails payinfodetails);
	/**
	 * 根据业主id删除该业主的缴费汇总信息
	 * @param ownerId
	 */
	void deletepayinfosumofowner(int ownerId);
	/**
	 * 批量缴费
	 * @param listpayinfodetails
	 */
	void batchpaid(@Param("listpayinfodetails") List<PayInfoDetails> listpayinfodetails);
	/**
	 * 查询所有欠费的缴费汇总信息
	 * @return
	 */
	List<PayInfoSum> selectpayinfodetailsaccordingispaid(Map<String, Object> params);
	long likecount();
	/**
	 * 查询所有不欠费的缴费汇总信息
	 * @return
	 */
	List<PayInfoSum> selectpayinfodetailsaccordingnotpaid(Map<String, Object> params);
	long likecount2();
}
