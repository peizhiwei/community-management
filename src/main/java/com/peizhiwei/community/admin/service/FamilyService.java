package com.peizhiwei.community.admin.service;

import java.util.List;

import com.peizhiwei.community.admin.entity.Family;

public interface FamilyService {
	/**
	 * ��ѯ���м�ͥ��Ա��Ϣ
	 */
	List<Family> getallfamilyinfo();
	/**
	 * ���¼�ͥ��Ա��Ϣ
	 */
	void updatefamilyinfo(Family family);
	/**
	 * ������ͥ��Ա
	 */
	void insertfamilyinfo(Family family);
	/**
	 * ɾ����ͥ��Ա
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
	 * ����¥����ţ���Ԫ�ţ���ѯ������ס���ķ����
	 * @param ownerName
	 * @param buildNumber
	 * @param houseUnit
	 * @return
	 */
	List<String> gethaveownerhousenumber(String buildNumber,int houseUnit);
	/**
	 * ����¥����ţ���Ԫ�ţ�����ţ���ѯҵ����
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @return
	 */
	String getownername(String buildNumber,int houseUnit,String houseNumber);
	/**
	 * ����¥����ţ���Ԫ�ţ�����ţ���ѯҵ��id
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @return
	 */
	int selectowneridaccording_bn_hu_hn(String buildNumber,int houseUnit,String houseNumber);
	/**
	 * ģ����ѯ��ͥ��Ա��Ϣ(¥����ţ���Ԫ�ţ�����ţ�ҵ����������Ա����)
	 * @param buildNumber
	 * @param houseUnit
	 * @param houseNumber
	 * @param ownerName
	 * @param familyName
	 * @return
	 */
	List<Family> getfamilyinfolike(String buildNumber,String houseUnit,String houseNumber,String ownerName,String familyName);
	/**
	 * ����ɾ����ͥ��Ա��Ϣ
	 * @param listfamilyId
	 */
	void checkdelete(String[] listfamilyId);
}
