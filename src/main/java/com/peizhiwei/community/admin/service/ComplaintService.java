package com.peizhiwei.community.admin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	/**
	 * ����Ͷ����Ϣ��idɾ��Ͷ����Ϣ
	 */
	void deletecomplaint(int complaintId);
	/**
	 * ����ҵ��idɾ����ҵ��������Ͷ����Ϣ
	 */
	void deletecomplaintofowner(int ownerId);
	/**
	 * ģ����ѯͶ����Ϣ(Ͷ���ˣ�¥����ţ���Ԫ�ţ�����ţ�Ͷ������)
	 * @param ownerName
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @param complaintReason
	 * @return
	 */
	List<Complaint> getcomplaintinfolike(String ownerName,String buildNumber,String houseUnit,String houseNumber,String complaintReason);
}
