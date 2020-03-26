package com.peizhiwei.community.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.dao.PayInfoDetailsDao;
import com.peizhiwei.community.entity.PayInfoDetails;
import com.peizhiwei.community.service.PayInfoDetailsService;
@Service
public class PayInfoDetailsServiceImpl implements PayInfoDetailsService {
	@Autowired
	PayInfoDetailsDao payindetailsfodao;
	
	/**
	 * ��ȡ���нɷ���Ϣ
	 */
	@Override
	public List<PayInfoDetails> getallpayinfo() {
		List<PayInfoDetails> listpayinfo = payindetailsfodao.getallpayinfo();
		return listpayinfo;
	}
	/**
	 * �ѽɷѣ��޸Ľɷ�״̬Ϊ�ѽɷѣ��ɹ���Ա�����Ľɷѷ�ʽĬ��Ϊ�ֽ�
	 */
	@Override
	public void updatepaystate(PayInfoDetails payinfo) {
		payindetailsfodao.updatepaystate(payinfo);
	}
	/**
	 * ���ݽɷѷ�ʽ���Ʋ�ѯ��Ӧ��id
	 */
	@Override
	public int getpaymethodid(String methodName) {
		int methodid = payindetailsfodao.getpaymethodid(methodName);
		return methodid;
	}
	
}
