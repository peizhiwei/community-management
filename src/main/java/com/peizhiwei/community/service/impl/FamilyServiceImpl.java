package com.peizhiwei.community.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.dao.FamilyDao;
import com.peizhiwei.community.entity.Family;
import com.peizhiwei.community.service.FamilyService;
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
	@Override
	public void insertfamilyinfo(Family family) {
		familydao.insertfamilyinfo(family);		
	}
	@Override
	public void deletefamilyinfo(int familyId) {
		familydao.deletefamilyinfo(familyId);		
	}

}
