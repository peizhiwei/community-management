package com.peizhiwei.community.service;

import java.util.List;

import com.peizhiwei.community.entity.PayInfoDetails;

public interface PayInfoDetailsService {
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
