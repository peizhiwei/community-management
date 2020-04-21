package com.peizhiwei.community.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peizhiwei.community.admin.dao.ComplaintDao;
import com.peizhiwei.community.admin.entity.Complaint;
import com.peizhiwei.community.admin.entity.Parking;
import com.peizhiwei.community.admin.service.ComplaintService;
import com.peizhiwei.community.util.Pager;

@Service
@Transactional(rollbackFor = Exception.class)
public class ComplaintServiceImpl implements ComplaintService {
	@Autowired
	ComplaintDao complaintdao;
	/**
	 * ��ҳ��ѯ����Ͷ����Ϣ
	 */
	@Override
	public Pager<Complaint> pagegetallcomplaintinfo(int page, int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size", size);
		List<Complaint> listcomplaint = complaintdao.pagegetallcomplaintinfo(params);
		Pager<Complaint> pager = new Pager<Complaint>();
		pager.setRows(listcomplaint);
		pager.setTotal(complaintdao.count());
		return pager;
	}
	/**
	 * ��ȡ����Ͷ����Ϣ
	 */
	@Override
	public List<Complaint> getallcomplaintinfo() {
		List<Complaint> listcomplaint = complaintdao.getallcomplaintinfo();
		return listcomplaint;
	}
	/**
	 * ����Ͷ����Ϣ����������ˣ��޸�Ͷ��״̬Ϊ1������
	 */
	@Override
	public void acceptancecomplaint(Complaint complaintId) {
		complaintdao.acceptancecomplaint(complaintId);		
	}
	/**
	 * �ѽ��Ͷ����Ϣ���޸�Ͷ��״̬Ϊ2�ѽ��
	 */
	@Override
	public void settledcomplaint(Complaint complaint) {
		complaintdao.settledcomplaint(complaint);
	}
	/**
	 * ����Ͷ����Ϣ��id��ȡ������id
	 */
	@Override
	public int getcomplaintinfo(int complaintId) {
		int complaint = complaintdao.getcomplaintinfo(complaintId);
		return complaint;
	}
	/**
	 * ����Ͷ����Ϣidɾ��Ͷ����Ϣ
	 */
	@Override
	public void deletecomplaint(int complaintId) {
		complaintdao.deletecomplaint(complaintId);
	}
	/**
	 * ����ҵ��idɾ����ҵ��������Ͷ����Ϣ
	 */
	@Override
	public void deletecomplaintofowner(int ownerId) {
		complaintdao.deletecomplaintofowner(ownerId);
	}
	/**
	 * ģ����ѯͶ����Ϣ��(Ͷ���ˣ�¥����ţ���Ԫ�ţ�����ţ�Ͷ��ԭ��)
	 */
	@Override
	public Pager<Complaint> getcomplaintinfolike(String ownerName, String buildNumber, String houseUnit,
			String houseNumber, String complaintReason,int page,int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size",size);
		List<Complaint> listcomplaint = complaintdao.getcomplaintinfolike(ownerName, buildNumber, houseUnit, houseNumber, complaintReason, params);
 		Pager<Complaint> pagecomplaint = new Pager<Complaint>();
 		pagecomplaint.setRows(listcomplaint);
 		pagecomplaint.setTotal(complaintdao.likecount(ownerName, buildNumber, houseUnit, houseNumber, complaintReason));
		return pagecomplaint;
	}
	/**
	 * ����ɾ��Ͷ����Ϣ
	 */
	@Override
	public void checkdelete(String[] listcomplaint) {
		complaintdao.checkdelete(listcomplaint);
	}
}
