package com.peizhiwei.community.admin.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.PayType;

public interface PayTypeService {
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
	 * 根据缴费类别id查询缴费信息中是否存在该类别的缴费
	 * @param payTypeId
	 * @return
	 */
	boolean selectpaytypeofpayinfo(int payTypeId);
	/**
	 * 根据缴费类别id查询缴费类别名称
	 * @param payTypeId
	 * @return
	 */
	String selectpaytypenameaccordingtypeid(int payTypeId);
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
	/**
	 * 根据收费类别查询id
	 * @param payTypeName
	 * @return
	 */
	int selectpaytypeid(String payTypeName);
	/**
	 * 批量删除缴费类别
	 * @param listpayinfonullpaytypeid
	 */
	void checkdelete(List<Integer> listpayinfonullpaytypeid);
}
