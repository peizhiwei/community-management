package com.peizhiwei.community.owner.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.admin.entity.PayMethod;
import com.peizhiwei.community.owner.dao.OwnerPayDao;
import com.peizhiwei.community.owner.service.OwnerPayService;
@Service
public class OwnerPayServiceImpl implements OwnerPayService {
	@Autowired
	OwnerPayDao ownerpaydao;
	
	/**
	 * 根据业主id查询该业主缴费情况
	 */
	@Override
	public List<PayInfoDetails> getpayinfodetails(int ownerId) {
		List<PayInfoDetails> listpayinfodetails = ownerpaydao.getpayinfodetails(ownerId);
		return listpayinfodetails;
	}
	/**
	 * 查询除了现金外的支付方式
	 */
	@Override
	public List<PayMethod> getallpaymethod() {
		List<PayMethod> listpaymethod = ownerpaydao.getallpaymethod();
		return listpaymethod;
	}
	/**
	 * 缴费，修改基本信息
	 */
	@Override
	public void paid(PayInfoDetails payinfodetails) {
		ownerpaydao.paid(payinfodetails);
	}

}
