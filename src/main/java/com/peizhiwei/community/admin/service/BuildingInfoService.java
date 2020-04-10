package com.peizhiwei.community.admin.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.BuildingInfo;
import com.peizhiwei.community.admin.entity.HouseInfo;

public interface BuildingInfoService {
	/**
	 * ��ѯ¥����������Ϣ
	 * @return
	 */
	List<BuildingInfo> getallbuildinginfo();
	/**
	 * ����¥������ж����ݿ����Ƿ��Ѵ��ڸñ��,���ڷ���true,�����ڷ���false
	 * @param buildNumber
	 * @return
	 */
	boolean selectnumberisnull(String buildNumber);

	/**
	 * �޸�¥����Ϣ
	 */
	void changebuildinfo(BuildingInfo buildinginfo);

	/**
	 * ���ж�¥�����Ƿ���ס���������ס������ɾ��,true��ɾ����false����ɾ��
	 * @param buildId
	 * @return
	 */
	boolean deletebuildinfoishouseowner(int buildId);

	/**
	 * ����һ��¥����Ϣ
	 * @param buildinginfo
	 * @return
	 */
	boolean insertbuildinginfo(BuildingInfo buildinginfo);
	/**
	 * ����¥����Ϣ��ͬʱ�������뷿����Ϣ
	 * @param houseinfolist
	 */
	void inserthouseinfolist(List<HouseInfo> houseinfolist);
	/**
	 * ͨ��id��ѯ¥�����
	 * @param buildId
	 * @return
	 */
	String selectbuildnumber(int buildId);
}
