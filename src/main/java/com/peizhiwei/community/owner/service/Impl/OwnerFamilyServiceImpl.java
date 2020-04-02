package com.peizhiwei.community.owner.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.admin.entity.Family;
import com.peizhiwei.community.owner.dao.OwnerFamilyDao;
import com.peizhiwei.community.owner.service.OwnerFamilyService;
@Service
public class OwnerFamilyServiceImpl implements OwnerFamilyService {
	@Autowired
	OwnerFamilyDao ownerfamilydao;
	
	/**
	 * 根据业主id获取业主的家庭成员
	 */
	@Override
	public List<Family> selectfamilyofowner(int ownerId) {
		List<Family> listfamily = ownerfamilydao.selectfamilyofowner(ownerId);
		return listfamily;
	}
	/**
	 * 新增家庭成员
	 */
	@Override
	public void insertfamily(Family family) {
		ownerfamilydao.insertfamily(family);
	}

}
