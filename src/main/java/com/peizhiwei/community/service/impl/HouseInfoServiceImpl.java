package com.peizhiwei.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.dao.HouseInfoDao;
import com.peizhiwei.community.entity.HouseInfo;
import com.peizhiwei.community.entity.HouseType;
import com.peizhiwei.community.service.HouseInfoService;

/**
 * ������Ϣ����ز���
 * 
 * @author PEIZHIWEI
 *
 */
@Service
public class HouseInfoServiceImpl implements HouseInfoService {
	@Autowired
	private HouseInfoDao houseinfodao;
	/**
	 * ��ȡ���з�����Ϣ
	 */
	@Override
	public List<HouseInfo> getallhouseinfo() {
		List<HouseInfo> listhouseinfo = houseinfodao.getallhouseinfo();
		return listhouseinfo;
	}
	/**
	 * ��ȡ���з�����Ϣ
	 */
	@Override
	public List<HouseType> getallhousetype() {
		List<HouseType> listhousetype=houseinfodao.getallhousetype();
		return listhousetype;
	}
	/**
	 * �޸ķ�����Ϣ
	 */
	@Override
	public void updatehouseinfo(HouseInfo houseinfo) {
		houseinfodao.updatehouseinfo(houseinfo);
	}

}
