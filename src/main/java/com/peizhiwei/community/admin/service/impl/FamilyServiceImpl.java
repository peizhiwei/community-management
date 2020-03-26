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
	/**
	 * 添加家庭成员信息
	 */
	@Override
	public void insertfamilyinfo(Family family) {
		familydao.insertfamilyinfo(family);		
	}
	/**
	 * 杀出家庭成员信息
	 */
	@Override
	public void deletefamilyinfo(int familyId) {
		familydao.deletefamilyinfo(familyId);		
	}
	/**
	 * 业主迁出时，删除家庭成员信息
	 */
	@Override
	public void deletefamilyofowner(int ownerId) {
		familydao.deletefamilyofowner(ownerId);
	}

}
