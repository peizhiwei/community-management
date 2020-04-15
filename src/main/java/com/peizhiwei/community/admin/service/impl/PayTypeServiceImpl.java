package com.peizhiwei.community.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peizhiwei.community.admin.dao.PayTypeDao;
import com.peizhiwei.community.admin.entity.PayType;
import com.peizhiwei.community.admin.service.PayTypeService;

@Service
@Transactional(rollbackFor = Exception.class)
public class PayTypeServiceImpl implements PayTypeService {
	@Autowired
	PayTypeDao paytypedao;
	/**
	 * ��ȡ���������Ϣ
	 */
	@Override
	public List<PayType> getallpaytype() {
		List<PayType> listtype = paytypedao.getallpaytype();
		return listtype;
	}
	/**
	 * �޸������Ϣ
	 */
	@Override
	public void updatepaytypeinfo(PayType paytype) {
		paytypedao.updatepaytypeinfo(paytype);	
	}
	/**
	 * ɾ���շ������Ϣ
	 */
	@Override
	public void deletepaytypeinfo(int payTypeId) {
		paytypedao.deletepaytypeinfo(payTypeId);
	}
	/**
	 * �����շ����
	 */
	@Override
	public void insertpaytypeinfo(PayType paytype) {
		paytypedao.insertpaytypeinfo(paytype);
	}
	@Override
	public boolean selectpaytypename(String payTypeName) {
		boolean flag = paytypedao.selectpaytypename(payTypeName);
		return flag;
	}
	/**
	 * �����շ�������Ʋ�ѯid
	 */
	@Override
	public int selectpaytypeid(String payTypeName) {
		int payTypeId = paytypedao.selectpaytypeid(payTypeName);
		return payTypeId;
	}
	/**
	 * ����ɾ���ɷ����
	 */
	@Override
	public void checkdelete(List<Integer> listpayTypeId) {
		paytypedao.checkdelete(listpayTypeId);
	}
	/**
	 * ���ݽɷ����id��ѯ�ɷ���Ϣ���Ƿ��Ѵ��ڸ����Ľɷ�
	 */
	@Override
	public boolean selectpaytypeofpayinfo(int payTypeId) {
		return paytypedao.selectpaytypeofpayinfo(payTypeId);
	}
	/**
	 * ���ݽɷ����id��ѯ�������
	 */
	@Override
	public String selectpaytypenameaccordingtypeid(int payTypeId) {
		return paytypedao.selectpaytypenameaccordingtypeid(payTypeId);
	}
}
