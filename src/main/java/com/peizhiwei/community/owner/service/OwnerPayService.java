package com.peizhiwei.community.owner.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.PayInfoDetails;

public interface OwnerPayService {
	/**
	 * ����ҵ��id��ѯ��ҵ���Ľɷ����
	 * @param ownerId
	 * @return
	 */
	List<PayInfoDetails> getpayinfodetails(int ownerId);
}
