package com.peizhiwei.community.admin.dao;

import java.util.List;

import com.peizhiwei.community.admin.entity.HouseInfo;
import com.peizhiwei.community.admin.entity.HouseType;

public interface HouseInfoDao {
	/**
	 * ��ȡ���з�����Ϣ
	 */
	List<HouseInfo> getallhouseinfo();
	/**
	 * ����ҵ�����Ʋ�ѯ���еķ�����Ϣ
	 */
	List<HouseInfo> getallhouseinfoaccordingownername(String ownerName);
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
	/**
	 * ҵ��Ǩ��ʱ����ҵ��id��Ϊ�գ���סʱ��Ϊ�գ�״̬��Ϊ0����
	 * @param ownerId
	 */
	void updatehouseinfoofowner(int ownerId);
	
}
