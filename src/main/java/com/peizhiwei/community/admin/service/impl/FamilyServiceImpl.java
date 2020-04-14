package com.peizhiwei.community.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peizhiwei.community.admin.dao.FamilyDao;
import com.peizhiwei.community.admin.entity.Family;
import com.peizhiwei.community.admin.service.FamilyService;
@Service
@Transactional(rollbackFor = Exception.class)
public class FamilyServiceImpl implements FamilyService {
	@Autowired
	FamilyDao familydao;
	/**
	 * ��ѯ���м�ͥ��Ա��Ϣ
	 */
	@Override
	public List<Family> getallfamilyinfo() {
		List<Family> listfamilyinfo = familydao.getallfamilyinfo();
		return listfamilyinfo;
	}
	/**
	 * ���¼�ͥ��Ա��Ϣ
	 */
	@Override
	public void updatefamilyinfo(Family family) {
		familydao.updatefamilyinfo(family);
	}
	/**
	 * ��Ӽ�ͥ��Ա��Ϣ
	 */
	@Override
	public void insertfamilyinfo(Family family) {
		familydao.insertfamilyinfo(family);		
	}
	/**
	 * ɱ����ͥ��Ա��Ϣ
	 */
	@Override
	public void deletefamilyinfo(int familyId) {
		familydao.deletefamilyinfo(familyId);		
	}
	/**
	 * ҵ��Ǩ��ʱ��ɾ����ͥ��Ա��Ϣ
	 */
	@Override
	public void deletefamilyofowner(int ownerId) {
		familydao.deletefamilyofowner(ownerId);
	}
	/**
	 * ��ѯ������ס����¥�����
	 */
	@Override
	public List<String> gethaveownerbuildnumber() {
		List<String> listbuildnumber = familydao.gethaveownerbuildnumber();
		return listbuildnumber;
	}
	/**
	 * ����¥����Ų�ѯ�ö�¥����ס���ĵ�Ԫ��
	 */
	@Override
	public List<Integer> gethaveownerhouseunitaccordingbuildnumber(String buildNumber) {
		List<Integer> listhouseunit = familydao.gethaveownerhouseunitaccordingbuildnumber(buildNumber);
		return listhouseunit;
	}
	/**
	 * ����ҵ������¥����ţ���Ԫ�ţ���ѯ�����
	 */
	@Override
	public List<String> gethaveownerhousenumber(String buildNumber,	int houseUnit) {
		List<String> listhousenumber = familydao.gethaveownerhousenumber(buildNumber, houseUnit);
		return listhousenumber;
	}
	/**
	 * ����¥����ţ���Ԫ�ţ�����ţ���ѯҵ����
	 */
	@Override
	public String getownername(String buildNumber,int houseUnit,String houseNumber){
		String ownername = familydao.getownername(buildNumber, houseUnit, houseNumber);
		return ownername;
	}
	/**
	 * ����¥����ţ���Ԫ�ţ�����ţ���ѯҵ��id
	 */
	@Override
	public int selectowneridaccording_bn_hu_hn(String buildNumber, int houseUnit,String houseNumber) {
		return familydao.selectowneridaccording_bn_hu_hn(buildNumber, houseUnit, houseNumber);
	}
	/**
	 * ģ����ѯ��ͥ��Ա��Ϣ(¥���ţ���Ԫ�ţ�����ţ�ҵ����������Ա����)
	 */
	@Override
	public List<Family> getfamilyinfolike(String buildNumber, String houseUnit, String houseNumber, String ownerName,
			String familyName) {
		List<Family> listfamily = familydao.getfamilyinfolike(buildNumber, houseUnit, houseNumber, ownerName, familyName);
		return listfamily;
	}

}
