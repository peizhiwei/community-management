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
	 * ��ȡ��������
	 * @return
	 */
	@Override
	public int selecthousesum() {
		return adminindexdao.selecthousesum();
	}
	/**
	 * ��ȡ���������
	 * @return
	 */
	@Override
	public int selectnullhouse() {
		return adminindexdao.selectnullhouse();
	}
	/**
	 * ��ȡҵ������
	 * @return
	 */
	@Override
	public int selectownersum() {
		return adminindexdao.selectownersum();
	}
	/**
	 * ��ȡ��ҵ����
	 * @return
	 */
	@Override
	public int selectmanofowner() {
		return adminindexdao.selectmanofowner();
	}
	/**
	 * ��ȡס��������������ҵ��
	 * @return
	 */
	@Override
	public int selectresidentsum() {
		return adminindexdao.selectresidentsum();
	}
	/**
	 * ��ȡ������ס������
	 * @return
	 */
	@Override
	public int selectmanresidentsum() {
		return adminindexdao.selectmanresidentsum();
	}
	/**
	 * ��ȡ����ͣ��λ��
	 * @return
	 */
	@Override
	public int selectparkingsum() {
		return adminindexdao.selectparkingsum();
	}
	/**
	 * ��ȡ���п��г�λ��
	 * @return
	 */
	@Override
	public int selectnullparkingsum() {
		return adminindexdao.selectnullparkingsum();
	}

}
