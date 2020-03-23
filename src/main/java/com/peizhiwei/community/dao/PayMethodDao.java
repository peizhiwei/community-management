package com.peizhiwei.community.dao;

import java.util.List;

import com.peizhiwei.community.entity.PayMethod;

public interface PayMethodDao {
	/**
	 * ��ȡ���еĽɷѷ�ʽ
	 * @return
	 */
	List<PayMethod> getallpaymethodinfo();
	/**
	 * �޸�֧����ʽ
	 */
	void updatepaymethodinfo(PayMethod paymethod);
	/**
	 * ɾ��֧����ʽ
	 * @param methodId
	 */
	void deletepaymethodinfo(int methodId);
	/**
	 * ����֧����ʽ
	 * @param paymethod
	 */
	void insertpaymethodinfo(PayMethod paymethod);
	/**
	 * ��ѯ�����Ƿ��Ѵ��ڸ�֧����ʽ
	 * @param methodName
	 * @return
	 */
	boolean selectpaymethodname(String methodName);
}
