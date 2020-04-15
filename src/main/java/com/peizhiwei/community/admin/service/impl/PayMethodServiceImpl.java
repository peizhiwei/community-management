package com.peizhiwei.community.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.admin.dao.PayMethodDao;
import com.peizhiwei.community.admin.entity.PayMethod;
import com.peizhiwei.community.admin.service.PayMethodService;

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
}
