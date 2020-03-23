package com.peizhiwei.community.dao;

import java.util.List;

import com.peizhiwei.community.entity.PayType;

public interface PayTypeDao {
	/**
	 * ��ȡ�����շ�����
	 * @return
	 */
	List<PayType> getallpaytype();
	/**
	 * �޸������Ϣ
	 * @param paytype
	 */
	void updatepaytypeinfo(PayType paytype);
	/**
	 * ɾ�������Ϣ
	 * @param payTypeId
	 */
	void deletepaytypeinfo(int payTypeId);
	/**
	 * �����շ����
	 * @param paytype
	 */
	void insertpaytypeinfo(PayType paytype);
	/**
	 * �����շ�������Ʋ�ѯ�����Ƿ��Ѵ���
	 * @param payTypeName
	 * @return
	 */
	boolean selectpaytypename(String payTypeName);
}
