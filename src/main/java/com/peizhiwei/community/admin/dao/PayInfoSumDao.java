package com.peizhiwei.community.admin.dao;
/**
 * �ɷ���Ϣ����dao��
 * @author PEIZHIWEI
 *
 */


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.admin.entity.PayInfoSum;

public interface PayInfoSumDao {
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
	 * �����ɷ���Ϣʱ���½ɷѻ�����Ϣ
	 * @param payinfosum
	 */
	void updatepayinfosum(@Param("list")List<PayInfoSum> list);
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
	void batchpaid(@Param("listpayinfodetails") List<PayInfoDetails> listpayinfodetails);
}
