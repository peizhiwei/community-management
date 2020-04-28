package com.peizhiwei.community.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.admin.dao.PayInfoDao;
import com.peizhiwei.community.admin.entity.PayInfo;
import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.admin.service.PayInfoService;
import com.peizhiwei.community.util.Pager;
@Service
public class PayInfoServiceImpl implements PayInfoService {
	@Autowired
	PayInfoDao payinfodao;
	
	/**
	 * ��ҳ��ȡ���нɷ���Ϣ
	 */
	@Override
	public Pager<PayInfo> pagegetallpayinfo(int page, int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size", size);
		List<PayInfo> listpayinfo = payinfodao.pagegetallpayinfo(params);
		Pager<PayInfo> pager = new Pager<PayInfo>();
		pager.setRows(listpayinfo);
		pager.setTotal(payinfodao.count());
		return pager;
	}
	
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
	/**
	 * ���ݽɷ���Ŀ��ѯ�ɷ���Ϣ
	 */
	@Override
	public Pager<PayInfo> selectpayinfoaccordingpaytypename(int page,int size,String payTypeName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size", size);
		List<PayInfo> listpayinfo = payinfodao.selectpayinfoaccordingpaytypename(params,payTypeName);
		Pager<PayInfo> pager = new Pager<PayInfo>();
		pager.setRows(listpayinfo);
		pager.setTotal(payinfodao.likecount(payTypeName));
		return pager;
	}
	/**
	 * �������²�ѯ�ɷ���Ϣ
	 */
	@Override
	public Pager<PayInfo> selectpayinfoaccordingpayintostarttime(int page, int size, String year,String month) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size", size);
		List<PayInfo> listpayinfo = payinfodao.selectpayinfoaccordingpayintostarttime(params, year, month);
		Pager<PayInfo> pager = new Pager<PayInfo>();
		pager.setRows(listpayinfo);
		pager.setTotal(payinfodao.likecount2(year, month));
		return pager;
	}
}
