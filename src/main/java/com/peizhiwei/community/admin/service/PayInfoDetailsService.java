package com.peizhiwei.community.admin.service;

import java.math.BigDecimal;
import java.util.List;

import com.peizhiwei.community.admin.entity.PayInfoDetails;

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
	/**
	 * ��ȡҵ��Ӧ�ɽ���ܺ�
	 * @param ownerId
	 * @return
	 */
	BigDecimal getsumpaymoney(int ownerId);
	/**
	 * ��ȡҵ���ѽɷѽ��
	 * @param ownerId
	 * @return
	 */
	BigDecimal getpaysumpid(int ownerId);
	/**
	 * ����ҵ��idɾ����ҵ�������нɷ�����
	 * @param ownerId
	 */
	void deletepayinfodetailsofowner(int ownerId);
}
