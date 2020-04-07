package com.peizhiwei.community.owner.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.admin.entity.HouseOwner;
import com.peizhiwei.community.owner.dao.OwnerSelfMessageDao;
import com.peizhiwei.community.owner.service.OwnerSelfMessageService;

@Service
public class OwnerSelfMessageServiceImpl implements OwnerSelfMessageService {
	@Autowired
	OwnerSelfMessageDao ownerselfmessagedao;
	
	
	/**
	 * ��ȡҵ�������и�����Ϣ
	 */
	@Override
	public HouseOwner getmessageofowner(int ownerId) {
		HouseOwner owner = new HouseOwner();
		owner = ownerselfmessagedao.getmessageofowner(ownerId);
		return owner;
	}

	/**
	 * ҵ���޸ĸ�����Ϣ
	 */
	@Override
	public void changeownerinfo(HouseOwner owner) {
		ownerselfmessagedao.changeownerinfo(owner);
	}
	/**
	 * �û��޸�����
	 */
	@Override
	public void updateownerpassword(HouseOwner owner) {
		ownerselfmessagedao.updateownerpassword(owner);
	}
	/**
	 * �����ֻ���
	 */
	@Override
	public void updateownerphone(HouseOwner owner) {
		ownerselfmessagedao.updateownerphone(owner);
	}
}
