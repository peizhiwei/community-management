package com.peizhiwei.community.admin.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.Family;
import com.peizhiwei.community.util.Pager;

public interface FamilyService {
	/**
	 * 分页查询家庭成员信息
	 */
	Pager<Family> pagegetallfamilyinfo(int page,int size);
	/**
	 * 查询所有家庭成员信息
	 */
	List<Family> getallfamilyinfo();
	/**
	 * 更新家庭成员信息
	 */
	void updatefamilyinfo(Family family);
	/**
	 * 新增家庭成员
	 */
	void insertfamilyinfo(Family family);
	/**
	 * 删除家庭成员
	 */
	void deletefamilyinfo(int familyId);
	/**
	 * 业主迁出时删除家庭成员信息
	 * @param ownerId
	 */
	void deletefamilyofowner(int ownerId);
	/**
	 * 查询所有有住户的楼栋编号
	 * @param ownerName
	 * @return
	 */
	List<String> gethaveownerbuildnumber();
	/**
	 * 根据楼栋编号查询该栋楼中有住户的单元号
	 * @param buildNumber
	 * @return
	 */
	List<Integer> gethaveownerhouseunitaccordingbuildnumber(String buildNumber);
	/**
	 * 根据楼栋编号，单元号，查询所有有住户的房间号
	 * @param ownerName
	 * @param buildNumber
	 * @param houseUnit
	 * @return
	 */
	List<String> gethaveownerhousenumber(String buildNumber,int houseUnit);
	/**
	 * 根据楼栋编号，单元号，房间号，查询业主名
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @return
	 */
	String getownername(String buildNumber,int houseUnit,String houseNumber);
	/**
	 * 根据楼栋编号，单元号，房间号，查询业主id
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @return
	 */
	int selectowneridaccording_bn_hu_hn(String buildNumber,int houseUnit,String houseNumber);
	/**
	 * 分页，模糊查询家庭成员信息(楼栋编号，单元号，房间号，业主姓名，成员姓名)
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @param ownerName
	 * @param familyName
	 * @return
	 */
	Pager<Family> getfamilyinfolike(String buildNumber,String houseUnit,String houseNumber,String ownerName,String familyName,int page,int size);
	/**
	 * 批量删除家庭成员信息
	 * @param listfamilyId
	 */
	void checkdelete(String[] listfamilyId);
}
