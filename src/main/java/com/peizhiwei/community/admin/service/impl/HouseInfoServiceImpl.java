package com.peizhiwei.community.admin.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peizhiwei.community.admin.dao.HouseInfoDao;
import com.peizhiwei.community.admin.entity.HouseInfo;
import com.peizhiwei.community.admin.entity.HouseType;
import com.peizhiwei.community.admin.service.HouseInfoService;

/**
 * 房间信息的相关操作
 * 
 * @author PEIZHIWEI
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
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
	 * 根据楼栋编号，单元号，房间号，查询该房间的id
	 */
	@Override
	public int selecthouseid(@Param("buildNumber")String buildNumber,@Param("houseUnit")int houseUnit,@Param("houseNumber")String houseNumber) {
		return houseinfodao.selecthouseid(buildNumber, houseUnit, houseNumber);
	}
	/**
	 * 新增业主时，在房间信息表中添加业主id,入住时间（默认为当前系统时间）
	 */
	@Override
	public void updatehouseinfoofownerid(HouseInfo houseinfo) {
		houseinfodao.updatehouseinfoofownerid(houseinfo);
	}
	/**
	 * 业主迁出时，将业主id置为空，入住时间为空，状态置为0待售
	 */
	@Override
	public void updatehouseinfoofowner(int ownerId) {
		houseinfodao.updatehouseinfoofowner(ownerId);
	}
	/**
	 * 模糊查询房间信息(楼栋编号，单元号，房间号，业主姓名)
	 */
	@Override
	public List<HouseInfo> selecthouseinfolike(String buildNumber,String houseUnit,String houseNumber,String ownerName) {
		List<HouseInfo> listhouseinfo = houseinfodao.selecthouseinfolike(buildNumber, houseUnit, houseNumber, ownerName);
		return listhouseinfo;
	}
}
