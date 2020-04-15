package com.peizhiwei.community.admin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.admin.entity.PayInfoSum;

public interface PayInfoSumService {
	/**
	 * ��ȡ���л�����Ϣ
	 * @return
	 */
	List<PayInfoSum> getallpayinfosum();
	/**
	 * �ڲ���ҵ����ͬʱ����ҵ��������Ϣ
	 * @param payinfosum
	 */
	void insertpayinfosum(PayInfoSum payinfosum);
	/**
	 * ���½ɷѻ�����Ϣ
	 * @param payinfosum
	 */
	void updatepayinfosum();
	/**
	 * һ���ɷ�
	 * @param payinfodetails
	 */
	void updatepayinfodetailsstate(PayInfoDetails payinfodetails);
	/**
	 * ����ҵ��idɾ����ҵ���Ľɷѻ�����Ϣ
	 * @param ownerId
	 */
	void deletepayinfosumofowner(int ownerId);
	/**
	 * �����ɷ�
	 * @param listpayinfodetails
	 */
	void batchpaid(List<PayInfoDetails> listpayinfodetails);
}
