package com.peizhiwei.community.admin.service;
/**
 * ������Ϣ
 * @author PEIZHIWEI
 *
 */

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.admin.entity.HouseInfo;
import com.peizhiwei.community.admin.entity.HouseType;
import com.peizhiwei.community.util.Pager;

public interface HouseInfoService {
	/**
	 * ��ȡ���з�����Ϣ
	 * @return
	 */
	Pager<HouseInfo> getallhouseinfo(int page,int size);
	/**
	 * ��ȡ���з�����Ϣ
	 * @return
	 */
	List<HouseType> getallhousetype();
	/**
	 * �޸ķ�����Ϣ
	 * @param houseinfo
	 * @return
	 */
	void updatehouseinfo(HouseInfo houseinfo);
	/**
	 * ����¥�ţ���ѯ�ⶰ¥�����з�����Ϣ
	 * @param buildNumber
	 * @return
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
	 * ����ҵ��ʱ���ڷ�����Ϣ�������ҵ��id,��סʱ�䣨Ĭ��Ϊϵͳ��ǰʱ�䣩
	 * @param houseinfo
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
	List<HouseInfo> selecthouseinfolike(String buildNumber,String houseUnit,String houseNumber,String ownerName);

}
