package com.peizhiwei.community.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peizhiwei.community.admin.dao.BuildingInfoDao;
import com.peizhiwei.community.admin.entity.BuildingInfo;
import com.peizhiwei.community.admin.entity.HouseInfo;
import com.peizhiwei.community.admin.service.BuildingInfoService;
import com.peizhiwei.community.util.Pager;

@Service
@Transactional(rollbackFor = Exception.class)
public class BuildingInfoServiceImpl implements BuildingInfoService {
	@Autowired
	private BuildingInfoDao buildinginfodao;
	/**
	 * 分页查询所有楼栋信息
	 */
	@Override
	public Pager<BuildingInfo> pagegetallbuildinginfo(int page,int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size", size);
		List<BuildingInfo> buildinginfolist = buildinginfodao.pagegetallbuildinginfo(params);
		Pager<BuildingInfo> pager = new Pager<BuildingInfo>();
		pager.setRows(buildinginfolist);
		pager.setTotal(buildinginfodao.count());
		return pager;
	}
	/**
	 * 查询所有楼栋信息
	 */
	@Override
	public List<BuildingInfo> getallbuildinginfo() {
		List<BuildingInfo> listbuildinginfo = buildinginfodao.getallbuildinginfo();
		return listbuildinginfo;
	}
	/**
	 * 根据楼房编号判断数据库中是否已存在该编号,存在返回true,不存在返回false
	 */
	@Override
	public boolean selectnumberisnull(String buildNumber) {
		if(buildinginfodao.selectnumberisnull(buildNumber)==1) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 修改楼栋信息
	 */
	@Override
	public void changebuildinfo(BuildingInfo buildinginfo) {
		buildinginfodao.changebuildinfo(buildinginfo);
	}
	/**
	 * 删除一条楼栋信息,如果楼中有住户，则不能删除，如果没有住户，则同时删除所有属于盖楼的房间信息
	 */
	@Override
	public boolean deletebuildinfoishouseowner(int buildId) {
		boolean flag = true;
		try {
			if (buildinginfodao.selectbuildhouseisowner(buildId)) {
				// 有住户，不能删除
				flag = false;
			} else {
				// 没有住户，可以删除
				flag = true;
				buildinginfodao.deletehouseinfoofbuild(buildId);
				buildinginfodao.deletebuildinfo(buildId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 添加楼栋信息 如果要添加的楼栋编号已存在，则添加失败
	 */
	@Override
	public boolean insertbuildinginfo(BuildingInfo buildinginfo) {
		boolean flag = true;
		try {
			List<BuildingInfo> listbuildinfo = buildinginfodao.getallbuildinginfo();
			int list = listbuildinfo.size();
			int i;
			for (i = 0; i < list; i++) {
				// 如果要添加的楼栋编号已存在，则添加失败
				if (listbuildinfo.get(i).getBuildNumber().equals(buildinginfo.getBuildNumber())) {
					flag = false;
					break;
				}
			}
			if (i == list) {
				flag = true;
				buildinginfodao.insertbuildinfo(buildinginfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 批量增加房间信息
	 */
	@Override
	public void inserthouseinfolist(List<HouseInfo> houseinfolist) {
		buildinginfodao.inserthouseinfolist(houseinfolist);		
	}
	/**
	 * 根据楼栋id查找楼栋编号
	 */
	@Override
	public String selectbuildnumber(int buildId) {
		String number = buildinginfodao.selectbuildnumber(buildId);
		return number;
	}
	/**
	 * 根据楼栋编号模糊查询楼栋信息
	 */
	@Override
	public Pager<BuildingInfo> selectlikebuildinginfo(String buildNumber,int page,int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size", size);
		List<BuildingInfo> listbuildinginfo = buildinginfodao.selectlikebuildinginfo(buildNumber,params);
		Pager<BuildingInfo> pager = new Pager<BuildingInfo>();
		pager.setRows(listbuildinginfo);
		pager.setTotal(buildinginfodao.likecount(buildNumber));
		return pager;
	}
}
