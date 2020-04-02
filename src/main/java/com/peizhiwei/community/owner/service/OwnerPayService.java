package com.peizhiwei.community.owner.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.PayInfoDetails;

public interface OwnerPayService {
	/**
	 * 根据业主id查询该业主的缴费情况
	 * @param ownerId
	 * @return
	 */
	List<PayInfoDetails> getpayinfodetails(int ownerId);
}
