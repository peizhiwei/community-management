package com.peizhiwei.community.owner.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.Complaint;

public interface OwnerComplaintService {
	
	/**
	 * ����ҵ��id��ѯ��ҵ����Ͷ����Ϣ
	 * @param ownerId
	 * @return
	 */
	List<Complaint> getcomplaint(int ownerId);
	/**
	 * ����Ͷ����Ϣ
	 * @param complaint
	 */
	void insertcomplaint(Complaint complaint);
	/**
	 * �޸�Ͷ����Ϣ
	 * @param complaint
	 */
	void updatecomplaint(Complaint complaint);
}
