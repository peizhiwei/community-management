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
	 * 获取所有类别信息
	 */
	@Override
	public List<PayType> getallpaytype() {
		List<PayType> listtype = paytypedao.getallpaytype();
		return listtype;
	}
	/**
	 * 修改类别信息
	 */
	@Override
	public void updatepaytypeinfo(PayType paytype) {
		paytypedao.updatepaytypeinfo(paytype);	
	}
	/**
	 * 删除收费类别信息
	 */
	@Override
	public void deletepaytypeinfo(int payTypeId) {
		paytypedao.deletepaytypeinfo(payTypeId);
	}
	/**
	 * 新增收费类别
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
	 * 根据收费类别名称查询id
	 */
	@Override
	public int selectpaytypeid(String payTypeName) {
		int payTypeId = paytypedao.selectpaytypeid(payTypeName);
		return payTypeId;
	}
	/**
	 * 批量删除缴费类别
	 */
	@Override
	public void checkdelete(List<Integer> listpayTypeId) {
		paytypedao.checkdelete(listpayTypeId);
	}
	/**
	 * 根据缴费类别id查询缴费信息中是否已存在该类别的缴费
	 */
	@Override
	public boolean selectpaytypeofpayinfo(int payTypeId) {
		return paytypedao.selectpaytypeofpayinfo(payTypeId);
	}
	/**
	 * 根据缴费类别id查询类别名称
	 */
	@Override
	public String selectpaytypenameaccordingtypeid(int payTypeId) {
		return paytypedao.selectpaytypenameaccordingtypeid(payTypeId);
	}
}
