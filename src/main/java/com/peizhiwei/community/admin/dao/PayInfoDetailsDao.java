package com.peizhiwei.community.admin.dao;

import java.util.List;

import com.peizhiwei.community.admin.entity.PayInfoDetails;

public interface PayInfoDetailsDao {
	/**
	 * ��ȡ���нɷ���Ϣ
	 * @return
	 */
	List<PayInfoDetails> getallpayinfo();
	/**
	 * �ѽɷѣ��޸Ľɷ�״̬Ϊ�ѽɷѣ��ɹ���Ա�����Ľɷѷ�ʽĬ��Ϊ�ֽ�
	 * @param payinfo
	 */
	void updatepaystate(PayInfoDetails payinfo);
	/**
	 * ���ݽɷѷ�ʽ���Ʋ��Ҷ�Ӧ��id
	 * @param methodName
	 * @return
	 */
	int getpaymethodid(String methodName);
}
