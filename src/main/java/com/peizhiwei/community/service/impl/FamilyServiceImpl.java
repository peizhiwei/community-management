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
	 * 查询所有家庭成员信息
	 */
	@Override
	public List<Family> getallfamilyinfo() {
		List<Family> listfamilyinfo = familydao.getallfamilyinfo();
		return listfamilyinfo;
	}
	/**
	 * 更新家庭成员信息
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
