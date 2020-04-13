package com.peizhiwei.community.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

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
	 * ����¥����ţ���Ԫ�ţ�����ţ���ѯ�÷����id
	 * @param buildNumber
	 * @param houseUnit
	 * @return
	 */
	int selecthouseid(@Param("buildNumber")String buildNumber,@Param("houseUnit")int houseUnit,@Param("houseNumber")String houseNumber);
	/**
	 * ������ҵ����ʱ���ڷ�����Ϣ���в���ҵ��id����סʱ�䣨Ĭ��Ϊ����ҵ��ʱ�䣩
	 */
	void updatehouseinfoofownerid(HouseInfo houseinfo);
	/**
	 * ҵ��Ǩ��ʱ����ҵ��id��Ϊ�գ���סʱ��Ϊ�գ�״̬��Ϊ0����
	 * @param ownerId
	 */
	void updatehouseinfoofowner(int ownerId);
	/**
	 * ģ����ѯ������Ϣ
	 * @param houseinfo(¥����ţ���Ԫ�ţ�����ţ�ҵ������)
	 * @return
	 */
	List<HouseInfo> selecthouseinfolike(@Param("buildNumber")String buildNumber,@Param("houseUnit")String houseUnit,@Param("houseNumber")String houseNumber,@Param("ownerName")String ownerName);
	
}
