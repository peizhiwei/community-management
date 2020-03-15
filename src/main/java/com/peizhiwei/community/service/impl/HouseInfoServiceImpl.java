package com.peizhiwei.community.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peizhiwei.community.dao.HouseInfoDao;
import com.peizhiwei.community.entity.HouseInfo;
import com.peizhiwei.community.entity.HouseType;
import com.peizhiwei.community.service.HouseInfoService;

/**
 * ������Ϣ����ز���
 * 
 * @author PEIZHIWEI
 *
 */
@Service
public class HouseInfoServiceImpl implements HouseInfoService {
	@Autowired
	private HouseInfoDao houseinfodao;
	/**
	 * ��ȡ���з�����Ϣ
	 */
	@Override
	public List<HouseInfo> getallhouseinfo() {
		List<HouseInfo> listhouseinfo = houseinfodao.getallhouseinfo();
		return listhouseinfo;
	}
	/**
	 * ��ȡ���з�����Ϣ
	 */
	@Override
	public List<HouseType> getallhousetype() {
		List<HouseType> listhousetype=houseinfodao.getallhousetype();
		return listhousetype;
	}
	/**
	 * �޸ķ�����Ϣ
	 */
	@Override
	public void updatehouseinfo(HouseInfo houseinfo) {
		houseinfodao.updatehouseinfo(houseinfo);
	}
	/**
	 * ����¥�ţ���ѯ��¥���еķ�����Ϣ
	 */
	@Override
	public List<HouseInfo> getallhouseinfoofbuild(String buildNumber) {
		List<HouseInfo> listhouseinfoofbuild=houseinfodao.getallhouseinfoofbuild(buildNumber);
		return listhouseinfoofbuild;
	}
	/**
	 * ���ݷ���Ų�ѯ�÷����id
	 */
	@Override
	public Integer gethouseidaccordinghousenumber(String houseNumber) {
		Integer houseId = houseinfodao.gethouseidaccordinghousenumber(houseNumber);
		return houseId;
	}
	/**
	 * ����ҵ��ʱ���ڷ�����Ϣ�������ҵ��id,��סʱ�䣨Ĭ��Ϊ��ǰϵͳʱ�䣩
	 */
	@Override
	public void updatehouseinfoofownerid(HouseInfo houseinfo) {
		houseinfodao.updatehouseinfoofownerid(houseinfo);
	}
	@Override
	public List<HouseInfo> getallhouseinfoaccordingownername(String ownerName) {
		List<HouseInfo> listhouseinfo = houseinfodao.getallhouseinfoaccordingownername(ownerName);
		return listhouseinfo;
	}

}
