package com.peizhiwei.community.admin.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peizhiwei.community.admin.dao.PayInfoDetailsDao;
import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.admin.service.PayInfoDetailsService;
@Service
@Transactional(rollbackFor = Exception.class)
public class PayInfoDetailsServiceImpl implements PayInfoDetailsService {
	@Autowired
	PayInfoDetailsDao payinfodetailsfodao;
	
	/**
	 * ��ȡ���нɷ���Ϣ
	 */
	@Override
	public List<PayInfoDetails> getallpayinfo() {
		List<PayInfoDetails> listpayinfo = payinfodetailsfodao.getallpayinfo();
		return listpayinfo;
	}
	/**
	 * �ѽɷѣ��޸Ľɷ�״̬Ϊ�ѽɷѣ��ɹ���Ա�����Ľɷѷ�ʽĬ��Ϊ�ֽ�
	 */
	@Override
	public void updatepaystate(PayInfoDetails payinfo) {
		payinfodetailsfodao.updatepaystate(payinfo);
	}
	/**
	 * ���ݽɷѷ�ʽ���Ʋ�ѯ��Ӧ��id
	 */
	@Override
	public int getpaymethodid(String methodName) {
		int methodid = payinfodetailsfodao.getpaymethodid(methodName);
		return methodid;
	}
	/**
	 * ��ȡҵ��Ӧ�ɽ���ܺ�
	 */
	@Override
	public BigDecimal getsumpaymoney(int ownerId) {
		BigDecimal payinfosumpayable = payinfodetailsfodao.getsumpaymoney(ownerId);
		return payinfosumpayable;
	}
	/**
	 * ��ȡҵ���ѽɷѽ��
	 */
	@Override
	public BigDecimal getpaysumpid(int ownerId) {
		BigDecimal paysumpid = payinfodetailsfodao.getpaysumpid(ownerId);
		return paysumpid;
	}
	/**
	 * ����ҵ��idɾ����ҵ�������нɷ�������Ϣ
	 */
	@Override
	public void deletepayinfodetailsofowner(int ownerId) {
		payinfodetailsfodao.deletepayinfodetailsofowner(ownerId);
	}
	/**
	 * �����ɷ�
	 */
	@Override
	public void batchpaid(List<PayInfoDetails> listpayinfodetails) {
		payinfodetailsfodao.batchpaid(listpayinfodetails);
	}
	
}
