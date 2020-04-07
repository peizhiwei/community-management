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
	 * 获取业主的所有个人信息
	 */
	@Override
	public HouseOwner getmessageofowner(int ownerId) {
		HouseOwner owner = new HouseOwner();
		owner = ownerselfmessagedao.getmessageofowner(ownerId);
		return owner;
	}

	/**
	 * 业主修改个人信息
	 */
	@Override
	public void changeownerinfo(HouseOwner owner) {
		ownerselfmessagedao.changeownerinfo(owner);
	}
	/**
	 * 用户修改密码
	 */
	@Override
	public void updateownerpassword(HouseOwner owner) {
		ownerselfmessagedao.updateownerpassword(owner);
	}
	/**
	 * 更换手机号
	 */
	@Override
	public void updateownerphone(HouseOwner owner) {
		ownerselfmessagedao.updateownerphone(owner);
	}
}
