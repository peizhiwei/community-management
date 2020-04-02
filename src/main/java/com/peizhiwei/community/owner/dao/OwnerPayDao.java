package com.peizhiwei.community.owner.dao;

import java.util.List;

import com.peizhiwei.community.admin.entity.PayInfoDetails;

public interface OwnerPayDao {
	/**
	 * 根据业主id查询该业主的缴费情况
	 * @param ownerId
	 * @return
	 */
	List<PayInfoDetails> getpayinfodetails(int ownerId);
}
