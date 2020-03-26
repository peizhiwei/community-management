package com.peizhiwei.community.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.admin.dao.PayInfoDetailsDao;
import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.admin.service.PayInfoDetailsService;
@Service
public class PayInfoDetailsServiceImpl implements PayInfoDetailsService {
	@Autowired
	PayInfoDetailsDao payindetailsfodao;
	
	/**
	 * 获取所有缴费信息
	 */
	@Override
	public List<PayInfoDetails> getallpayinfo() {
		List<PayInfoDetails> listpayinfo = payindetailsfodao.getallpayinfo();
		return listpayinfo;
	}
	/**
	 * 已缴费，修改缴费状态为已缴费，由管理员操作的缴费方式默认为现金
	 */
	@Override
	public void updatepaystate(PayInfoDetails payinfo) {
		payindetailsfodao.updatepaystate(payinfo);
	}
	/**
	 * 根据缴费方式名称查询对应的id
	 */
	@Override
	public int getpaymethodid(String methodName) {
		int methodid = payindetailsfodao.getpaymethodid(methodName);
		return methodid;
	}
	
}
