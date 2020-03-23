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
	 * 获取所有的支付方式
	 */
	@Override
	public List<PayMethod> getallpaymethodinfo() {
		List<PayMethod> listmethod = paymethoddao.getallpaymethodinfo();
		return listmethod;
	}
	/**
	 * 修改支付方式
	 */
	@Override
	public void updatepaymethodinfo(PayMethod paymethod) {
		paymethoddao.updatepaymethodinfo(paymethod);
	}
	/**
	 * 删除支付方式
	 */
	@Override
	public void deletepaymethodinfo(int methodId) {
		paymethoddao.deletepaymethodinfo(methodId);
	}
	/**
	 * 新增支付方式
	 */
	@Override
	public void insertpaymethodinfo(PayMethod paymethod) {
		paymethoddao.insertpaymethodinfo(paymethod);
	}
	/**
	 * 查询该支付方式在表中是否已存在
	 */
	@Override
	public boolean selectpaymethodname(String methodName) {
		boolean flag = paymethoddao.selectpaymethodname(methodName);
		return flag;
	}
	
}
