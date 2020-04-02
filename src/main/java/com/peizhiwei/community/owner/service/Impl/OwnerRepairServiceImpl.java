package com.peizhiwei.community.owner.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.admin.entity.Repair;
import com.peizhiwei.community.owner.dao.OwnerRepairDao;
import com.peizhiwei.community.owner.service.OwnerRepairService;
@Service
public class OwnerRepairServiceImpl implements OwnerRepairService {
	@Autowired
	OwnerRepairDao ownerrepairdao;
	
	/**
	 * ����������Ϣ
	 */
	@Override
	public void insertrepair(Repair repair) {
		ownerrepairdao.insertrepair(repair);
	}
	/**
	 * �޸ı�����Ϣ
	 */
	@Override
	public void updaterepair(Repair repair) {
		ownerrepairdao.updaterepair(repair);
	}
	/**
	 * ����ҵ��id��ѯ��ҵ���ı�����Ϣ
	 */
	@Override
	public List<Repair> getrepairinfo(int ownerId) {
		List<Repair> listrepair = ownerrepairdao.getrepairinfo(ownerId);
		return listrepair;
	}

}
