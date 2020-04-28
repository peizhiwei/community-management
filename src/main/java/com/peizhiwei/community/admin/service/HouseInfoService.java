package com.peizhiwei.community.admin.service;
/**
 * ������Ϣ
 * @author PEIZHIWEI
 *
 */

import java.util.List;

import com.peizhiwei.community.admin.entity.HouseInfo;
import com.peizhiwei.community.admin.entity.HouseType;
import com.peizhiwei.community.util.Pager;

public interface HouseInfoService {
	/**
	 * ��ȡ���з�����Ϣ
	 * @return
	 */
	Pager<HouseInfo> pagegetallhouseinfo(int page,int size);
	/**
	 * ��ȡ���з�����Ϣ
	 */
	List<HouseInfo> getallhouseinfo();
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
	int selecthouseid(String buildNumber,int houseUnit,String houseNumber);
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
	Pager<HouseInfo> selecthouseinfolike(String buildNumber,String houseUnit,String houseNumber,String ownerName,int page,int size);

}
