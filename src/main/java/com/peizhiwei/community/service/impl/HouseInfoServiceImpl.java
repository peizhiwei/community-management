package com.peizhiwei.community.service.impl;

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
	/**
	 * 根据楼号，查询该楼所有的房间信息
	 */
	@Override
	public List<HouseInfo> getallhouseinfoofbuild(String buildNumber) {
		List<HouseInfo> listhouseinfoofbuild=houseinfodao.getallhouseinfoofbuild(buildNumber);
		return listhouseinfoofbuild;
	}
	/**
	 * 根据房间号查询该房间的id
	 */
	@Override
	public Integer gethouseidaccordinghousenumber(String houseNumber) {
		Integer houseId = houseinfodao.gethouseidaccordinghousenumber(houseNumber);
		return houseId;
	}
	/**
	 * 新增业主时，在房间信息表中添加业主id,入住时间（默认为当前系统时间）
	 */
	@Override
	public void updatehouseinfoofownerid(HouseInfo houseinfo) {
		houseinfodao.updatehouseinfoofownerid(houseinfo);
	}
	@Override
	public List<HouseInfo> getallhouseinfoaccordingownername(String ownerName) {
		List<HouseInfo> listhouseinfo = houseinfodao.getallhouseinfoaccordingownername(ownerName);
		return listhouseinfo;
	}

}
