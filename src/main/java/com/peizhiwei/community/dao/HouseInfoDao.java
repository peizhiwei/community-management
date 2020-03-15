package com.peizhiwei.community.dao;

import java.util.Date;
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
	/**
	 * ��ѯ¥�������������з�����Ϣ
	 */
	List<HouseInfo> getallhouseinfoofbuild(String buildNumber);
	/**
	 * ���ݷ���Ų�ѯ�÷���id
	 */
	Integer gethouseidaccordinghousenumber(String houseNumber);
	/**
	 * ������ҵ����ʱ���ڷ�����Ϣ���в���ҵ��id����סʱ�䣨Ĭ��Ϊ����ҵ��ʱ�䣩
	 */
	void updatehouseinfoofownerid(HouseInfo houseinfo);
}
