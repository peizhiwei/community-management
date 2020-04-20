package com.peizhiwei.community.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peizhiwei.community.admin.dao.FamilyDao;
import com.peizhiwei.community.admin.entity.Family;
import com.peizhiwei.community.admin.service.FamilyService;
import com.peizhiwei.community.util.Pager;
@Service
@Transactional(rollbackFor = Exception.class)
public class FamilyServiceImpl implements FamilyService {
	@Autowired
	FamilyDao familydao;
	/**
	 * 分页查询家庭成员信息
	 */
	@Override
	public Pager<Family> pagegetallfamilyinfo(int page, int size) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size", size);
		List<Family> listfamily = familydao.pagegetallfamilyinfo(params);
		Pager<Family> pager = new Pager<Family>();
		pager.setRows(listfamily);
		pager.setTotal(familydao.count());
		return pager;
	}
	/**
	 * 查询所有家庭成员信息
	 */
	@Override
	public List<Family> getallfamilyinfo() {
		List<Family> listfamilyinfo = familydao.getallfamilyinfo();
		return listfamilyinfo;
	}
	/**
	 * 更新家庭成员信息
	 */
	@Override
	public void updatefamilyinfo(Family family) {
		familydao.updatefamilyinfo(family);
	}
	/**
	 * 添加家庭成员信息
	 */
	@Override
	public void insertfamilyinfo(Family family) {
		familydao.insertfamilyinfo(family);		
	}
	/**
	 * 杀出家庭成员信息
	 */
	@Override
	public void deletefamilyinfo(int familyId) {
		familydao.deletefamilyinfo(familyId);		
	}
	/**
	 * 业主迁出时，删除家庭成员信息
	 */
	@Override
	public void deletefamilyofowner(int ownerId) {
		familydao.deletefamilyofowner(ownerId);
	}
	/**
	 * 查询所有有住户的楼栋编号
	 */
	@Override
	public List<String> gethaveownerbuildnumber() {
		List<String> listbuildnumber = familydao.gethaveownerbuildnumber();
		return listbuildnumber;
	}
	/**
	 * 根据楼栋编号查询该栋楼中有住户的单元号
	 */
	@Override
	public List<Integer> gethaveownerhouseunitaccordingbuildnumber(String buildNumber) {
		List<Integer> listhouseunit = familydao.gethaveownerhouseunitaccordingbuildnumber(buildNumber);
		return listhouseunit;
	}
	/**
	 * 根据业主名，楼栋编号，单元号，查询房间号
	 */
	@Override
	public List<String> gethaveownerhousenumber(String buildNumber,	int houseUnit) {
		List<String> listhousenumber = familydao.gethaveownerhousenumber(buildNumber, houseUnit);
		return listhousenumber;
	}
	/**
	 * 根据楼栋编号，单元号，房间号，查询业主名
	 */
	@Override
	public String getownername(String buildNumber,int houseUnit,String houseNumber){
		String ownername = familydao.getownername(buildNumber, houseUnit, houseNumber);
		return ownername;
	}
	/**
	 * 根据楼栋编号，单元号，房间号，查询业主id
	 */
	@Override
	public int selectowneridaccording_bn_hu_hn(String buildNumber, int houseUnit,String houseNumber) {
		return familydao.selectowneridaccording_bn_hu_hn(buildNumber, houseUnit, houseNumber);
	}
	/**
	 * 模糊查询家庭成员信息(楼栋号，单元号，房间号，业主姓名，成员姓名)
	 */
	@Override
	public Pager<Family> getfamilyinfolike(String buildNumber, String houseUnit, String houseNumber, String ownerName,
			String familyName,int page,int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size", size);
		List<Family> listfamily = familydao.getfamilyinfolike(buildNumber, houseUnit, houseNumber, ownerName, familyName,params);
		Pager<Family> pager = new Pager<Family>();
		pager.setRows(listfamily);
		pager.setTotal(familydao.likecount(buildNumber, houseUnit, houseNumber, ownerName, familyName));
		return pager;
	}
	/**
	 * 批量删除家庭成员信息
	 */
	@Override
	public void checkdelete(String[] listfamilyId) {
		familydao.checkdelete(listfamilyId);
	}
}
