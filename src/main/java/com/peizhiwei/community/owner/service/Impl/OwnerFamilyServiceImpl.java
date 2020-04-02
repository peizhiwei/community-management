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
	 * ����ҵ��id��ȡҵ���ļ�ͥ��Ա
	 */
	@Override
	public List<Family> selectfamilyofowner(int ownerId) {
		List<Family> listfamily = ownerfamilydao.selectfamilyofowner(ownerId);
		return listfamily;
	}
	/**
	 * ������ͥ��Ա
	 */
	@Override
	public void insertfamily(Family family) {
		ownerfamilydao.insertfamily(family);
	}

}
