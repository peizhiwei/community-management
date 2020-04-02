package com.peizhiwei.community.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.admin.dao.ComplaintDao;
import com.peizhiwei.community.admin.entity.Complaint;
import com.peizhiwei.community.admin.service.ComplaintService;

@Service
public class ComplaintServiceImpl implements ComplaintService {
	@Autowired
	ComplaintDao complaintdao;
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
	
}
