package com.peizhiwei.community.admin.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.admin.entity.PayInfoDetails;

public interface PayInfoDetailsDao {
	/**
	 * ��ҳ��ȡ���нɷ���Ϣ
	 * @return
	 */
	List<PayInfoDetails> pagegetallpayinfo(Map<String, Object> params);
	long count();
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
	void batchpaid(@Param("listpayinfodetails")List<PayInfoDetails> listpayinfodetails);
	/**
	 * ���ݽɷ���Ŀ��ѯ�ɷ���Ϣ
	 * @param payTypeName
	 * @return
	 */
	List<PayInfoDetails> selectpayinfoaccordingpaytypename(@Param("params")Map<String, Object> params,@Param("payTypeName")String payTypeName);
	long likecount(String payTypeName);
	/**
	 * �������²�ѯ�ɷ���Ϣ
	 * @param params
	 * @param payInfoStartTime
	 * @return
	 */
	List<PayInfoDetails> selectpayinfoaccordingpayintostarttime(@Param("params")Map<String, Object> params,@Param("year")String year,@Param("month")String month);
	long likecount2(@Param("year")String year,@Param("month")String month);
	/**
	 * ���ݽɷ�״̬��ѯ�ɷ�����
	 * @return
	 */
	List<PayInfoDetails> selectpayinfoaccordingpaystate(@Param("params")Map<String, Object> params,@Param("payState")String payState);
	long likecount3(String payState);
}
