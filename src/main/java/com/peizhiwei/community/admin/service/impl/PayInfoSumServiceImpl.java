package com.peizhiwei.community.admin.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.peizhiwei.community.admin.dao.PayInfoSumDao;
import com.peizhiwei.community.admin.entity.HouseOwner;
import com.peizhiwei.community.admin.entity.PayInfoDetails;
import com.peizhiwei.community.admin.entity.PayInfoSum;
import com.peizhiwei.community.admin.service.HouseOwnerService;
import com.peizhiwei.community.admin.service.PayInfoDetailsService;
import com.peizhiwei.community.admin.service.PayInfoSumService;
import com.peizhiwei.community.util.Pager;
@Service
@Transactional(rollbackFor = Exception.class)
public class PayInfoSumServiceImpl implements PayInfoSumService {
	@Autowired
	PayInfoSumDao payinfosumdao;
	@Autowired
	PayInfoDetailsService payinfodetailsservice;
	@Autowired
	HouseOwnerService houseownerservice;
	
	/**
	 * �����ҵ����ͬʱ���һ����ҵ���Ľɷѻ�����Ϣ
	 */
	@Override
	public void insertpayinfosum(PayInfoSum payinfosum) {
		payinfosumdao.insertpayinfosum(payinfosum);
	}
	/**
	 * ���½ɷѻ�����Ϣ
	 */
	@Override
	public void updatepayinfosum() {
		List<PayInfoSum> payinfsumlist = new ArrayList<PayInfoSum>();
		List<HouseOwner> houseownerlist = new ArrayList<HouseOwner>();
		try {
			houseownerlist = houseownerservice.getallhouseownerinfo();
			int size = houseownerlist.size();
			for(int j=0;j<size;j++) {
				PayInfoSum payinfosum = new PayInfoSum();
				HouseOwner paysumofowner = new HouseOwner();
				int ownerId = houseownerlist.get(j).getOwnerId();//ҵ��id
				paysumofowner.setOwnerId(ownerId);//ѭ�����ҵ��id
				payinfosum.setHouseOwner(paysumofowner);
				//��ȡ�ɷ������е�ÿ��ҵ��Ӧ�ɽ���ܺ�
				BigDecimal payinfosumpayable = payinfodetailsservice.getsumpaymoney(ownerId);
				payinfosum.setPayInfoSumPayable(payinfosumpayable);
				//��ȡ�ɷ������е�ÿ��ҵ���ѽɷѽ��
				BigDecimal payinfosumpaid = payinfodetailsservice.getpaysumpid(ownerId);
				payinfosum.setPayInfoSumPaid(payinfosumpaid);
				payinfsumlist.add(payinfosum);
			}
			payinfosumdao.updatepayinfosum(payinfsumlist);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}
	/**
	 * ��ҳ��ȡ���нɷѻ�����Ϣ
	 */
	@Override
	public Pager<PayInfoSum> pagegetallpayinfosum(int page, int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size", size);
		List<PayInfoSum> listpayinfosum = payinfosumdao.pagegetallpayinfosum(params);
		Pager<PayInfoSum> pager = new Pager<PayInfoSum>();
		pager.setRows(listpayinfosum);
		pager.setTotal(payinfosumdao.count());
		return pager;
	}
	/**
	 * ��ȡ���нɷѻ�����Ϣ
	 */
	@Override
	public List<PayInfoSum> getallpayinfosum() {
		List<PayInfoSum> listpayinfosum = payinfosumdao.getallpayinfosum();
		return listpayinfosum;
	}
	/**
	 * һ���ɷ�
	 */
	@Override
	public void updatepayinfodetailsstate(PayInfoDetails payinfodetails) {
		payinfosumdao.updatepayinfodetailsstate(payinfodetails);
	}
	/**
	 * ����ҵ��idɾ����ҵ���Ľɷѻ�����Ϣ
	 */
	@Override
	public void deletepayinfosumofowner(int ownerId) {
		payinfosumdao.deletepayinfosumofowner(ownerId);
	}
	/**
	 * �����ɷ�
	 */
	@Override
	public void batchpaid(List<PayInfoDetails> listpayinfodetails) {
		payinfosumdao.batchpaid(listpayinfodetails);
	}
}
