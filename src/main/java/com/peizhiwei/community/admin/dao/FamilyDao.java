package com.peizhiwei.community.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.peizhiwei.community.admin.entity.Family;

public interface FamilyDao {
	/**
	 * 查询所有家庭成员信心
	 */
	List<Family> getallfamilyinfo();
	/**
	 * 更改家庭成员信息
	 */
	void updatefamilyinfo(Family family);
	/**
	 * 新增家庭成员
	 */
	void insertfamilyinfo(Family family);
	/**
	 * 删除家庭成员信息
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
	 * 根据楼栋号，单元号，查询所有有住户的房间号
	 * @param buildNumber
	 * @param houseUnit
	 * @return
	 */
	List<String> gethaveownerhousenumber(@Param("buildNumber")String buildNumber,@Param("houseUnit")int houseUnit);
	/**
	 * 根据楼栋编号，单元号，房间号查询业主名称
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @return
	 */
	String getownername(@Param("buildNumber")String buildNumber,@Param("houseUnit")int houseUnit,@Param("houseNumber")String houseNumber);
	/**
	 * 根据楼栋编号，单元号，房间号，查询业主id
	 * @param ownerName
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @return
	 */
	int selectowneridaccording_bn_hu_hn(@Param("buildNumber")String buildNumber,@Param("houseUnit")int houseUnit,@Param("houseNumber")String houseNumber);
	/**
	 * 模糊查询家庭成员信息(楼栋编号，单元号，房间号，业主姓名，成员姓名)
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @param ownerName
	 * @param familyName
	 * @return
	 */
	List<Family> getfamilyinfolike(@Param("buildNumber")String buildNumber,@Param("houseUnit")String houseUnit,@Param("houseNumber")String houseNumber,@Param("ownerName")String ownerName,@Param("familyName")String familyName);
}
