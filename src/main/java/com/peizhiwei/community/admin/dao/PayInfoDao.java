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
}
