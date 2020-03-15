package com.peizhiwei.community.service;
/**
 * ������Ϣ
 * @author PEIZHIWEI
 *
 */

import java.util.List;

import com.peizhiwei.community.entity.HouseInfo;
import com.peizhiwei.community.entity.HouseType;

public interface HouseInfoService {
	/**
	 * ��ȡ���з�����Ϣ
	 * @return
	 */
	List<HouseInfo> getallhouseinfo();
	/**
	 * ����ҵ�����Ʋ�ѯ���еķ�����Ϣ
	 */
	List<HouseInfo> getallhouseinfoaccordingownername(String ownerName);
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
	 * ���ݷ���Ų�ѯ�÷����id
	 * @param houseNumber
	 * @return
	 */
	Integer gethouseidaccordinghousenumber(String houseNumber);
	/**
	 * ����ҵ��ʱ���ڷ�����Ϣ�������ҵ��id,��סʱ�䣨Ĭ��Ϊϵͳ��ǰʱ�䣩
	 * @param houseinfo
	 */
	void updatehouseinfoofownerid(HouseInfo houseinfo);
}
