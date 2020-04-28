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
	 * 分页获取所有缴费信息
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
	 * 获取所有的缴费信息
	 */
	@Override
	public List<PayInfo> getallpayinfo() {
		List<PayInfo> listpayinfo = payinfodao.getallpayinfo();
		return listpayinfo;
	}

	/**
	 * 发布缴费信息
	 */
	@Override
	public boolean insertnewpayinfo(PayInfo payinfo) {
		boolean flag = payinfodao.insertnewpayinfo(payinfo);
		return flag;
	}

	/**
	 * 删除缴费信息
	 */
	@Override
	public void deletepayinfo(int payInfoId) {
		//删除缴费信息的同时删除缴费详情
		payinfodao.deletepaydetailsofpayinfo(payInfoId);
		payinfodao.deletepayinfo(payInfoId);
		
	}
	/**
	 * 修改缴费信息
	 */
	@Override
	public void updatepayinfo(PayInfo payinfo) {
		payinfodao.updatepayinfo(payinfo);
	}
	/**
	 * 在发布缴费信息的同时，批量插入缴费详情
	 */
	@Override
	public void insertpayinfodetailslist(List<PayInfoDetails> list) {
		payinfodao.insertpayinfodetailslist(list);
	}
	/**
	 * 批量删除缴费信息
	 */
	@Override
	public void checkdelete(int[] listpayInfoId) {
		payinfodao.batchdeletepaydetailsofpayinfo(listpayInfoId);//批量删除缴费详情
		payinfodao.checkdelete(listpayInfoId);//批量删除缴费信息
	}
	/**
	 * 根据缴费项目查询缴费信息
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
	 * 根据年月查询缴费信息
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
