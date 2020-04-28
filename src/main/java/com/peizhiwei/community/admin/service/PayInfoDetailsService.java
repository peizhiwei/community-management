package com.peizhiwei.community.admin.service;

import java.math.BigDecimal;
import java.util.List;

import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.util.Pager;

public interface PayInfoDetailsService {
	/**
	 * ��ȡ���нɷ���Ϣ
	 * @return
	 */
	Pager<PayInfoDetails> pagegetallpayinfo(int page,int size);
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
	/**
	 * �����ɷ�
	 * @param listpayinfodetails
	 */
	void batchpaid(List<PayInfoDetails> listpayinfodetails);
	/**
	 * ���ݽɷ���Ŀ��ѯ�ɷ���Ϣ
	 * @param payTypeName
	 * @return
	 */
	Pager<PayInfoDetails> selectpayinfoaccordingpaytypename(int page,int size,String payTypeName);
	/**
	 * �������²�ѯ�ɷ���Ϣ
	 * @param params
	 * @param payInfoStartTime
	 * @return
	 */
	Pager<PayInfoDetails> selectpayinfoaccordingpayintostarttime(int page,int size,String year,String month);
	/**
	 * ���ݽɷ�״̬��ѯ�ɷ�����
	 * @param params
	 * @param payState
	 * @return
	 */
	Pager<PayInfoDetails> selectpayinfoaccordingpaystate(int page,int size,String payState);
}
