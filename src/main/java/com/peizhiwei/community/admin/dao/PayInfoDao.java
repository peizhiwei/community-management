package com.peizhiwei.community.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.admin.entity.PayInfo;
import com.peizhiwei.community.admin.entity.PayInfoDetails;

public interface PayInfoDao {
	/**
	 * ��ȡ���еĽɷ���Ϣ
	 * @return
	 */
	List<PayInfo> pagegetallpayinfo(Map<String, Object> params);
	long count();
	/**
	 * ��ȡ���еĽɷ���Ϣ
	 * @return
	 */
	List<PayInfo> getallpayinfo();
	/**
	 * �����µĽɷ���Ϣ
	 * @param payinfo
	 */
	boolean insertnewpayinfo(PayInfo payinfo);
	/**
	 * �ڷ����ɷ���Ϣ��ͬʱ����������ɷ�����
	 * @param houseinfolist
	 */
	void insertpayinfodetailslist(@Param("list")List<PayInfoDetails> list);
	
	/**
	 * ɾ���ɷ���Ϣ
	 * @param payInfoId
	 */
	void deletepayinfo(int payInfoId);
	/**
	 * ɾ���ɷ���Ϣʱ��ͬʱɾ���ɷ�����
	 * @param payInfoId
	 */
	void deletepaydetailsofpayinfo(int payInfoId);
	/**
	 * �޸Ľɷ���Ϣ
	 * @param payinfo
	 */
	void updatepayinfo(PayInfo payinfo);
	/**
	 * ����ɾ���ɷ���Ϣ
	 * @param listpayInfoId
	 */
	void checkdelete(int[] listpayInfoId);
	/**
	 * ɾ���ɷ���Ϣʱ������ɾ���ɷ�����
	 * @param listpayInfoId
	 */
	void batchdeletepaydetailsofpayinfo(int[] listpayInfoId);
	/**
	 * ���ݽɷ���Ŀ��ѯ�ɷ���Ϣ
	 * @param payTypeName
	 * @return
	 */
	List<PayInfo> selectpayinfoaccordingpaytypename(@Param("params")Map<String, Object> params,@Param("payTypeName")String payTypeName);
	long likecount(String payTypeName);
	/**
	 * �������²�ѯ�ɷ���Ϣ
	 * @param params
	 * @param payInfoStartTime
	 * @return
	 */
	List<PayInfo> selectpayinfoaccordingpayintostarttime(@Param("params")Map<String, Object> params,@Param("year")String year,@Param("month")String month);
	long likecount2(@Param("year")String year,@Param("month")String month);
}
