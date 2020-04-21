package com.peizhiwei.community.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.peizhiwei.community.admin.entity.Repair;
import com.peizhiwei.community.util.Pager;

public interface RepairDao {
	/**
	 * ��ҳ��ѯ���б�����Ϣ
	 * @param params
	 * @return
	 */
	List<Repair> pagegetallrepairinfo(Map<String, Object>params);
	long count();
	/**
	 * ��ȡ���б�����Ϣ
	 * @return
	 */
	List<Repair> getallrepairinfo();
	/**
	 * ��������Ϣ����������ˣ��޸ı���״̬Ϊ1��������
	 * @param repairId
	 */
	void acceptancerepair(Repair repairId);
	/**
	 * �ѽ��������Ϣ���޸�״̬Ϊ2�ѽ��
	 * @param Repair
	 */
	void settledrepair(Repair repair);
	/**
	 * ���ݱ�����Ϣ��id��ȡ������id
	 * @param repairId
	 * @return
	 */
	int getrepairinfo(int repairId);
	/**
	 * ���ݱ�����Ϣ��idɾ��������Ϣ
	 * @param repairId
	 */
	void deleterepair(int repairId);
	/**
	 * ����ҵ��idɾ����ҵ�������б�����Ϣ
	 * @param ownerId
	 */
	void deleterepairofowner(int ownerId);
	/**
	 * ģ����ѯ������Ϣ��(�����ˣ�¥����ţ���Ԫ�ţ�����ţ�������Ʒ)
	 * @param ownerName
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @param repairGoods
	 * @return
	 */
	List<Repair> getrepairinfolike(@Param("ownerName")String ownerName,@Param("buildNumber")String buildNumber,@Param("houseUnit")String houseUnit,@Param("houseNumber")String houseNumber,@Param("repairGoods")String repairGoods,@Param("params")Map<String, Object>params);
	long likecount(@Param("ownerName")String ownerName,@Param("buildNumber")String buildNumber,@Param("houseUnit")String houseUnit,@Param("houseNumber")String houseNumber,@Param("repairGoods")String repairGoods);
	/**
	 * ����ɾ��������Ϣ
	 * @param listrepairId
	 */
	void checkdelete(int[] listrepairId);
}
