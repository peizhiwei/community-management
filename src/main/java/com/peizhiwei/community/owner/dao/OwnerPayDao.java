package com.peizhiwei.community.owner.dao;

import java.util.List;

import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.admin.entity.PayMethod;

public interface OwnerPayDao {
	/**
	 * ����ҵ��id��ѯ��ҵ���Ľɷ����
	 * @param ownerId
	 * @return
	 */
	List<PayInfoDetails> getpayinfodetails(int ownerId);
	/**
	 * ��ѯ�����ֽ������֧����ʽ
	 * @return
	 */
	List<PayMethod> getallpaymethod();
	/**
	 * �ɷѣ��޸Ļ�����Ϣ
	 * @param payinfodetails
	 */
	void paid(PayInfoDetails payinfodetails);
}
