package com.peizhiwei.community.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.admin.entity.BuildingInfo;
import com.peizhiwei.community.admin.entity.HouseInfo;

public interface BuildingInfoDao {
	/**
	 * ��ѯ����¥����Ϣ
	 * @return
	 */
	List<BuildingInfo> getallbuildinginfo();
	/**
	 * �޸�¥����Ϣ
	 * @param buildinginfo
	 */
	void changebuildinfo(BuildingInfo buildinginfo);
	/**
	 * ��ѯ�ⶰ¥�Ƿ���ס��
	 * @param buildId
	 * @return
	 */
	boolean selectbuildhouseisowner(int buildId);
	/**
	 * ɾ���ⶰ¥����Ϣ
	 * @param buildId
	 */
	void deletebuildinfo(int buildId);
	/**
	 * ��ɾ��¥����Ϣ��ͬʱ������¥����û��ס���������¥����Ϣ��idɾ����¥�������з���
	 * @param buildId
	 */
	void deletehouseinfoofbuild(int buildId);
	/**
	 * ����һ��¥����Ϣ
	 * @param buildinginfo
	 */
	void insertbuildinfo(BuildingInfo buildinginfo);
	/**
	 * ������¥����Ϣ��ͬʱ�������뷿����Ϣ
	 * @param houseinfolist
	 */
	void inserthouseinfolist(@Param("houseinfolist")List<HouseInfo> houseinfolist);
}
