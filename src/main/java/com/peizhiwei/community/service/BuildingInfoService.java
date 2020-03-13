package com.peizhiwei.community.service;

import java.util.List;

import com.peizhiwei.community.entity.BuildingInfo;
import com.peizhiwei.community.entity.HouseInfo;

public interface BuildingInfoService {
	/**
	 * ��ѯ¥����������Ϣ
	 * @return
	 */
	List<BuildingInfo> getallbuildinginfo();

	/**
	 * �޸�¥����Ϣ������޸ĵı���Ѵ��ڣ����޸�ʧ��
	 */
	boolean changebuildinfo(BuildingInfo buildinginfo);

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
}