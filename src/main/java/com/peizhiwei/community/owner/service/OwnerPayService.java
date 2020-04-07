package com.peizhiwei.community.owner.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.admin.entity.PayMethod;

public interface OwnerPayService {
	/**
	 * 根据业主id查询该业主的缴费情况
	 * @param ownerId
	 * @return
	 */
	List<PayInfoDetails> getpayinfodetails(int ownerId);
	/**
	 * 查询除了现金以外的支付方式
	 * @return
	 */
	List<PayMethod> getallpaymethod();
	/**
	 * 缴费，修改基本信息
	 * @param payinfodetails
	 */
	void paid(PayInfoDetails payinfodetails);
}
