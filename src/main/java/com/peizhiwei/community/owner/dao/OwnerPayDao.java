package com.peizhiwei.community.owner.dao;

import java.util.List;

import com.peizhiwei.community.admin.entity.PayInfoDetails;

public interface OwnerPayDao {
	/**
	 * ����ҵ��id��ѯ��ҵ���Ľɷ����
	 * @param ownerId
	 * @return
	 */
	List<PayInfoDetails> getpayinfodetails(int ownerId);
}
