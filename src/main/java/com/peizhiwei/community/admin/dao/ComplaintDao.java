package com.peizhiwei.community.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.peizhiwei.community.admin.entity.Complaint;

public interface ComplaintDao {
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
	List<Complaint> getcomplaintinfolike(@Param("ownerName")String ownerName,@Param("buildNumber")String buildNumber,
			@Param("houseUnit")String houseUnit,@Param("houseNumber")String houseNumber,@Param("complaintReason")String complaintReason);
	/**
	 * ����ɾ��Ͷ����Ϣ
	 * @param listcomplaint
	 */
	void checkdelete(String[] listcomplaint);
}
