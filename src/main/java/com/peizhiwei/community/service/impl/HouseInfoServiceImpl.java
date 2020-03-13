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
 * 房间信息的相关操作
 * 
 * @author PEIZHIWEI
 *
 */
@Service
public class HouseInfoServiceImpl implements HouseInfoService {
	@Autowired
	private HouseInfoDao houseinfodao;
	/**
	 * 获取所有房间信息
	 */
	@Override
	public List<HouseInfo> getallhouseinfo() {
		List<HouseInfo> listhouseinfo = houseinfodao.getallhouseinfo();
		return listhouseinfo;
	}
	/**
	 * 获取所有房型信息
	 */
	@Override
	public List<HouseType> getallhousetype() {
		List<HouseType> listhousetype=houseinfodao.getallhousetype();
		return listhousetype;
	}
	/**
	 * 修改房间信息
	 */
	@Override
	public void updatehouseinfo(HouseInfo houseinfo) {
		houseinfodao.updatehouseinfo(houseinfo);
	}

}
