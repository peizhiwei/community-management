package com.peizhiwei.community.admin.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peizhiwei.community.admin.dao.PayInfoDetailsDao;
import com.peizhiwei.community.admin.entity.PayInfo;
import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.admin.service.PayInfoDetailsService;
import com.peizhiwei.community.util.Pager;
@Service
@Transactional(rollbackFor = Exception.class)
public class PayInfoDetailsServiceImpl implements PayInfoDetailsService {
	@Autowired
	PayInfoDetailsDao payinfodetailsfodao;
	
	/**
	 * 分页获取所有缴费详情
	 */
	@Override
	public Pager<PayInfoDetails> pagegetallpayinfo(int page, int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size", size);
		List<PayInfoDetails> listpayinfodetails = payinfodetailsfodao.pagegetallpayinfo(params);
		Pager<PayInfoDetails> pager = new Pager<PayInfoDetails>();
		pager.setRows(listpayinfodetails);
		pager.setTotal(payinfodetailsfodao.count());
		return pager;
	}
	/**
	 * 获取所有缴费信息
	 */
	@Override
	public List<PayInfoDetails> getallpayinfo() {
		List<PayInfoDetails> listpayinfo = payinfodetailsfodao.getallpayinfo();
		return listpayinfo;
	}
	/**
	 * 已缴费，修改缴费状态为已缴费，由管理员操作的缴费方式默认为现金
	 */
	@Override
	public void updatepaystate(PayInfoDetails payinfo) {
		payinfodetailsfodao.updatepaystate(payinfo);
	}
	/**
	 * 根据缴费方式名称查询对应的id
	 */
	@Override
	public int getpaymethodid(String methodName) {
		int methodid = payinfodetailsfodao.getpaymethodid(methodName);
		return methodid;
	}
	/**
	 * 获取业主应缴金额总和
	 */
	@Override
	public BigDecimal getsumpaymoney(int ownerId) {
		BigDecimal payinfosumpayable = payinfodetailsfodao.getsumpaymoney(ownerId);
		return payinfosumpayable;
	}
	/**
	 * 获取业主已缴费金额
	 */
	@Override
	public BigDecimal getpaysumpid(int ownerId) {
		BigDecimal paysumpid = payinfodetailsfodao.getpaysumpid(ownerId);
		return paysumpid;
	}
	/**
	 * 根据业主id删除该业主的所有缴费详情信息
	 */
	@Override
	public void deletepayinfodetailsofowner(int ownerId) {
		payinfodetailsfodao.deletepayinfodetailsofowner(ownerId);
	}
	/**
	 * 批量缴费
	 */
	@Override
	public void batchpaid(List<PayInfoDetails> listpayinfodetails) {
		payinfodetailsfodao.batchpaid(listpayinfodetails);
	}
	/**
	 * 根据缴费项目查询缴费信息
	 */
	@Override
	public Pager<PayInfoDetails> selectpayinfoaccordingpaytypename(int page,int size,String payTypeName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size", size);
		List<PayInfoDetails> listpayinfo = payinfodetailsfodao.selectpayinfoaccordingpaytypename(params,payTypeName);
		Pager<PayInfoDetails> pager = new Pager<PayInfoDetails>();
		pager.setRows(listpayinfo);
		pager.setTotal(payinfodetailsfodao.likecount(payTypeName));
		return pager;
	}
	/**
	 * 根据年月查询缴费信息
	 */
	@Override
	public Pager<PayInfoDetails> selectpayinfoaccordingpayintostarttime(int page, int size, String year,String month) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size", size);
		List<PayInfoDetails> listpayinfo = payinfodetailsfodao.selectpayinfoaccordingpayintostarttime(params, year, month);
		Pager<PayInfoDetails> pager = new Pager<PayInfoDetails>();
		pager.setRows(listpayinfo);
		pager.setTotal(payinfodetailsfodao.likecount2(year, month));
		return pager;
	}
	/**
	 * 根据缴费状态查询缴费详情
	 */
	@Override
	public Pager<PayInfoDetails> selectpayinfoaccordingpaystate(int page, int size, String payState) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size", size);
		List<PayInfoDetails> listpayinfo = payinfodetailsfodao.selectpayinfoaccordingpaystate(params, payState);
		Pager<PayInfoDetails> pager = new Pager<PayInfoDetails>();
		pager.setRows(listpayinfo);
		pager.setTotal(payinfodetailsfodao.likecount3(payState));
		return pager;
	}
}
