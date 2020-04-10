package com.peizhiwei.community.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.admin.dao.AdminIndexDao;
import com.peizhiwei.community.admin.service.AdminIndexService;

@Service
public class AdminIndexServiceImpl implements AdminIndexService {
	@Autowired
	AdminIndexDao adminindexdao;
	/**
	 * 获取房间总数
	 * @return
	 */
	@Override
	public int selecthousesum() {
		return adminindexdao.selecthousesum();
	}
	/**
	 * 获取房间空闲数
	 * @return
	 */
	@Override
	public int selectnullhouse() {
		return adminindexdao.selectnullhouse();
	}
	/**
	 * 获取业主总数
	 * @return
	 */
	@Override
	public int selectownersum() {
		return adminindexdao.selectownersum();
	}
	/**
	 * 获取男业主数
	 * @return
	 */
	@Override
	public int selectmanofowner() {
		return adminindexdao.selectmanofowner();
	}
	/**
	 * 获取住户总人数，包括业主
	 * @return
	 */
	@Override
	public int selectresidentsum() {
		return adminindexdao.selectresidentsum();
	}
	/**
	 * 获取所有男住户人数
	 * @return
	 */
	@Override
	public int selectmanresidentsum() {
		return adminindexdao.selectmanresidentsum();
	}
	/**
	 * 获取所有停车位数
	 * @return
	 */
	@Override
	public int selectparkingsum() {
		return adminindexdao.selectparkingsum();
	}
	/**
	 * 获取所有空闲车位数
	 * @return
	 */
	@Override
	public int selectnullparkingsum() {
		return adminindexdao.selectnullparkingsum();
	}

}
