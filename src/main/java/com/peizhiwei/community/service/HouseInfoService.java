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
}
