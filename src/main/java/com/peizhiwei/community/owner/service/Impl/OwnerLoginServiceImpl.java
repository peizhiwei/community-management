package com.peizhiwei.community.owner.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.admin.entity.HouseOwner;
import com.peizhiwei.community.owner.dao.OwnerLoginDao;
import com.peizhiwei.community.owner.service.OwnerLoginService;
@Service
public class OwnerLoginServiceImpl implements OwnerLoginService {
	@Autowired
	OwnerLoginDao ownerlogindao;

	/**
	 * ÑéÖ¤µÇÂ¼
	 */
	@Override
	public HouseOwner checkownerlogin(String username, String password) {
		HouseOwner owner = new HouseOwner();
		owner = ownerlogindao.selectownerusername(username);
		if(owner!=null && owner.getOwnerPassword().equals(password)) {
			return owner;
		}else {
			return null;
		}
	}

}
