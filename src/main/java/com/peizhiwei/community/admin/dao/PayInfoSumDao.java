package com.peizhiwei.community.admin.dao;
/**
 * 缴费信息汇总dao层
 * @author PEIZHIWEI
 *
 */


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.admin.entity.PayInfoSum;

public interface PayInfoSumDao {
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
}
