package com.peizhiwei.community.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.admin.dao.PayInfoDao;
import com.peizhiwei.community.admin.entity.PayInfo;
import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.admin.service.PayInfoService;
@Service
public class PayInfoServiceImpl implements PayInfoService {
	@Autowired
	PayInfoDao payinfodao;
	
	/**
	 * ��ȡ���еĽɷ���Ϣ
	 */
	@Override
	public List<PayInfo> getallpayinfo() {
		List<PayInfo> listpayinfo = payinfodao.getallpayinfo();
		return listpayinfo;
	}

	/**
	 * �����ɷ���Ϣ
	 */
	@Override
	public boolean insertnewpayinfo(PayInfo payinfo) {
		boolean flag = payinfodao.insertnewpayinfo(payinfo);
		return flag;
	}

	/**
	 * ɾ���ɷ���Ϣ
	 */
	@Override
	public void deletepayinfo(int payInfoId) {
		//ɾ���ɷ���Ϣ��ͬʱɾ���ɷ�����
		payinfodao.deletepaydetailsofpayinfo(payInfoId);
		payinfodao.deletepayinfo(payInfoId);
		
	}
	/**
	 * �޸Ľɷ���Ϣ
	 */
	@Override
	public void updatepayinfo(PayInfo payinfo) {
		payinfodao.updatepayinfo(payinfo);
	}
	/**
	 * �ڷ����ɷ���Ϣ��ͬʱ����������ɷ�����
	 */
	@Override
	public void insertpayinfodetailslist(List<PayInfoDetails> list) {
		payinfodao.insertpayinfodetailslist(list);
	}
	/**
	 * ����ɾ���ɷ���Ϣ
	 */
	@Override
	public void checkdelete(int[] listpayInfoId) {
		payinfodao.batchdeletepaydetailsofpayinfo(listpayInfoId);//����ɾ���ɷ�����
		payinfodao.checkdelete(listpayInfoId);//����ɾ���ɷ���Ϣ
	}

}
