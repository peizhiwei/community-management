package com.peizhiwei.community.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.admin.dao.FamilyDao;
import com.peizhiwei.community.admin.entity.Family;
import com.peizhiwei.community.admin.service.FamilyService;
@Service
public class FamilyServiceImpl implements FamilyService {
	@Autowired
	FamilyDao familydao;
	/**
	 * ��ѯ���м�ͥ��Ա��Ϣ
	 */
	@Override
	public List<Family> getallfamilyinfo() {
		List<Family> listfamilyinfo = familydao.getallfamilyinfo();
		return listfamilyinfo;
	}
	/**
	 * ���¼�ͥ��Ա��Ϣ
	 */
	@Override
	public void updatefamilyinfo(Family family) {
		familydao.updatefamilyinfo(family);
	}
	/**
	 * ��Ӽ�ͥ��Ա��Ϣ
	 */
	@Override
	public void insertfamilyinfo(Family family) {
		familydao.insertfamilyinfo(family);		
	}
	/**
	 * ɱ����ͥ��Ա��Ϣ
	 */
	@Override
	public void deletefamilyinfo(int familyId) {
		familydao.deletefamilyinfo(familyId);		
	}
	/**
	 * ҵ��Ǩ��ʱ��ɾ����ͥ��Ա��Ϣ
	 */
	@Override
	public void deletefamilyofowner(int ownerId) {
		familydao.deletefamilyofowner(ownerId);
	}

}
