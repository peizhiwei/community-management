package com.peizhiwei.community.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.admin.entity.HouseInfo;
import com.peizhiwei.community.admin.entity.HouseType;

public interface HouseInfoDao {
	/**
	 * ��ҳ��ѯ����ȡ���з�����Ϣ
	 */
	List<HouseInfo> pagegetallhouseinfo(Map<String, Object> params);
	long count();
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
	 * ģ����ѯ������Ϣ����ҳ��ѯ
	 * @param houseinfo(¥����ţ���Ԫ�ţ�����ţ�ҵ������)
	 * @return
	 */
	List<HouseInfo> selecthouseinfolike(@Param("buildNumber")String buildNumber,@Param("houseUnit")String houseUnit,@Param("houseNumber")String houseNumber,@Param("ownerName")String ownerName,@Param("params")Map<String, Object> params);
	/**
	 * ģ����ѯ���ܼ�¼��
	 * @return
	 */
	long likecount(@Param("buildNumber")String buildNumber,@Param("houseUnit")String houseUnit,@Param("houseNumber")String houseNumber,@Param("ownerName")String ownerName);
}
