package com.peizhiwei.community.admin.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.Complaint;


public interface ComplaintService {
	/**
	 * ��ȡ����Ͷ����Ϣ
	 * @return
	 */
	List<Complaint> getallcomplaintinfo();
	/**
	 * ����Ͷ����Ϣ����������ˣ��޸�Ͷ��״̬Ϊ1��������
	 * @param complaintid
	 */
	void acceptancecomplaint(Complaint complaintId);
	
	/**
	 * �ѽ��Ͷ����Ϣ���޸�Ͷ��״̬Ϊ2�ѽ��
	 * @param complaint
	 */
	void settledcomplaint(Complaint complaint);
	/**
	 * ����Ͷ����Ϣ��id��ȡ������id
	 * @param complaintId
	 * @return
	 */
	int getcomplaintinfo(int complaintId);
}
