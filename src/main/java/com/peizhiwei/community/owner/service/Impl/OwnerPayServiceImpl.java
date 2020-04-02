package com.peizhiwei.community.owner.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.admin.entity.PayInfoDetails;
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

}
