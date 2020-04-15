package com.peizhiwei.community.admin.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.PayType;

public interface PayTypeService {
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
	 * ���ݽɷ����id��ѯ�ɷ���Ϣ���Ƿ���ڸ����Ľɷ�
	 * @param payTypeId
	 * @return
	 */
	boolean selectpaytypeofpayinfo(int payTypeId);
	/**
	 * ���ݽɷ����id��ѯ�ɷ��������
	 * @param payTypeId
	 * @return
	 */
	String selectpaytypenameaccordingtypeid(int payTypeId);
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
	/**
	 * �����շ�����ѯid
	 * @param payTypeName
	 * @return
	 */
	int selectpaytypeid(String payTypeName);
	/**
	 * ����ɾ���ɷ����
	 * @param listpayinfonullpaytypeid
	 */
	void checkdelete(List<Integer> listpayinfonullpaytypeid);
}
