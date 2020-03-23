package com.peizhiwei.community.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.dao.PayMethodDao;
import com.peizhiwei.community.entity.PayMethod;
import com.peizhiwei.community.service.PayMethodService;

@Service
public class PayMethodServiceImpl implements PayMethodService {
	@Autowired
	PayMethodDao paymethoddao;
	
	/**
	 * ��ȡ���е�֧����ʽ
	 */
	@Override
	public List<PayMethod> getallpaymethodinfo() {
		List<PayMethod> listmethod = paymethoddao.getallpaymethodinfo();
		return listmethod;
	}
	/**
	 * �޸�֧����ʽ
	 */
	@Override
	public void updatepaymethodinfo(PayMethod paymethod) {
		paymethoddao.updatepaymethodinfo(paymethod);
	}
	/**
	 * ɾ��֧����ʽ
	 */
	@Override
	public void deletepaymethodinfo(int methodId) {
		paymethoddao.deletepaymethodinfo(methodId);
	}
	/**
	 * ����֧����ʽ
	 */
	@Override
	public void insertpaymethodinfo(PayMethod paymethod) {
		paymethoddao.insertpaymethodinfo(paymethod);
	}
	/**
	 * ��ѯ��֧����ʽ�ڱ����Ƿ��Ѵ���
	 */
	@Override
	public boolean selectpaymethodname(String methodName) {
		boolean flag = paymethoddao.selectpaymethodname(methodName);
		return flag;
	}
	
}
