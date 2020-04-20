package com.peizhiwei.community.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.admin.entity.BuildingInfo;
import com.peizhiwei.community.admin.entity.HouseInfo;

public interface BuildingInfoDao {
	/**
	 * ��ҳ��ѯ����¥����Ϣ
	 * @return
	 */
	List<BuildingInfo> pagegetallbuildinginfo(Map<String, Object> params);
	/**
	 * ��ѯ¥������
	 */
	long count();
	/**
	 * ��ѯ����¥����Ϣ
	 */
	List<BuildingInfo> getallbuildinginfo();
	/**
	 * ����¥������ж����ݿ����Ƿ��Ѵ��ڸñ��
	 * @param buildNumber
	 * @return
	 */
	int selectnumberisnull(String buildNumber);
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
	/**
	 * ͨ��id��ѯ¥�����
	 * @param buildId
	 * @return
	 */
	String selectbuildnumber(int buildId);
	/**
	 * ����¥�����ģ����ѯ¥����Ϣ
	 * @param buildNumber
	 * @return
	 */
	List<BuildingInfo> selectlikebuildinginfo(@Param("buildNumber")String buildNumber,@Param("params")Map<String, Object> params);
	/**
	 * ģ����ѯ���ܼ�¼��
	 * @return
	 */
	long likecount(@Param("buildNumber")String buildNumber);
}
