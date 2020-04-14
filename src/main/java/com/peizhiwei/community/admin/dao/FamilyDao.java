package com.peizhiwei.community.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.peizhiwei.community.admin.entity.Family;

public interface FamilyDao {
	/**
	 * ��ѯ���м�ͥ��Ա����
	 */
	List<Family> getallfamilyinfo();
	/**
	 * ���ļ�ͥ��Ա��Ϣ
	 */
	void updatefamilyinfo(Family family);
	/**
	 * ������ͥ��Ա
	 */
	void insertfamilyinfo(Family family);
	/**
	 * ɾ����ͥ��Ա��Ϣ
	 */
	void deletefamilyinfo(int familyId);
	/**
	 * ҵ��Ǩ��ʱɾ����ͥ��Ա��Ϣ
	 * @param ownerId
	 */
	void deletefamilyofowner(int ownerId);
	/**
	 * ��ѯ������ס����¥�����
	 * @param ownerName
	 * @return
	 */
	List<String> gethaveownerbuildnumber();
	/**
	 * ����¥����Ų�ѯ�ö�¥����ס���ĵ�Ԫ��
	 * @param buildNumber
	 * @return
	 */
	List<Integer> gethaveownerhouseunitaccordingbuildnumber(String buildNumber);
	/**
	 * ����¥���ţ���Ԫ�ţ���ѯ������ס���ķ����
	 * @param buildNumber
	 * @param houseUnit
	 * @return
	 */
	List<String> gethaveownerhousenumber(@Param("buildNumber")String buildNumber,@Param("houseUnit")int houseUnit);
	/**
	 * ����¥����ţ���Ԫ�ţ�����Ų�ѯҵ������
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @return
	 */
	String getownername(@Param("buildNumber")String buildNumber,@Param("houseUnit")int houseUnit,@Param("houseNumber")String houseNumber);
	/**
	 * ����¥����ţ���Ԫ�ţ�����ţ���ѯҵ��id
	 * @param ownerName
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @return
	 */
	int selectowneridaccording_bn_hu_hn(@Param("buildNumber")String buildNumber,@Param("houseUnit")int houseUnit,@Param("houseNumber")String houseNumber);
	/**
	 * ģ����ѯ��ͥ��Ա��Ϣ(¥����ţ���Ԫ�ţ�����ţ�ҵ����������Ա����)
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @param ownerName
	 * @param familyName
	 * @return
	 */
	List<Family> getfamilyinfolike(@Param("buildNumber")String buildNumber,@Param("houseUnit")String houseUnit,@Param("houseNumber")String houseNumber,@Param("ownerName")String ownerName,@Param("familyName")String familyName);
}
