package com.peizhiwei.community.dao;

import java.util.List;

import com.peizhiwei.community.entity.HouseInfo;
import com.peizhiwei.community.entity.HouseType;

public interface HouseInfoDao {
	/**
	 * ��ȡ���з�����Ϣ
	 */
	List<HouseInfo> getallhouseinfo();
	/**
	 * ��ѯ���з���
	 */
	List<HouseType> getallhousetype();
	/**
	 * �޸ķ�����Ϣ
	 */
	void updatehouseinfo(HouseInfo houseinfo);
}
