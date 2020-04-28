package com.peizhiwei.community.admin.dao;
/**
 * �ɷ���Ϣ����dao��
 * @author PEIZHIWEI
 *
 */


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.admin.entity.PayInfoSum;

public interface PayInfoSumDao {
	/**
	 * ��ҳ��ȡ���л�����Ϣ
	 * @return
	 */
	List<PayInfoSum> pagegetallpayinfosum(Map<String, Object> params);
	long count();
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
	/**
	 * ��ѯ����Ƿ�ѵĽɷѻ�����Ϣ
	 * @return
	 */
	List<PayInfoSum> selectpayinfodetailsaccordingispaid(Map<String, Object> params);
	long likecount();
	/**
	 * ��ѯ���в�Ƿ�ѵĽɷѻ�����Ϣ
	 * @return
	 */
	List<PayInfoSum> selectpayinfodetailsaccordingnotpaid(Map<String, Object> params);
	long likecount2();
}
