package com.peizhiwei.community.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peizhiwei.community.admin.dao.HouseInfoDao;
import com.peizhiwei.community.admin.entity.HouseInfo;
import com.peizhiwei.community.admin.entity.HouseType;
import com.peizhiwei.community.admin.service.HouseInfoService;
import com.peizhiwei.community.util.Pager;

/**
 * ������Ϣ����ز���
 * 
 * @author PEIZHIWEI
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HouseInfoServiceImpl implements HouseInfoService {
	@Autowired
	private HouseInfoDao houseinfodao;
	/**
	 * ��ҳ��ѯ��ȡ���з�����Ϣ
	 */
	@Override
	public Pager<HouseInfo> getallhouseinfo(int page,int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size", size);
		Pager<HouseInfo> pager = new Pager<HouseInfo>();
		List<HouseInfo> listhouseinfo = houseinfodao.getallhouseinfo(params);
		pager.setRows(listhouseinfo);
		pager.setTotal(houseinfodao.count());
		return pager;
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
	 * ����¥����ţ���Ԫ�ţ�����ţ���ѯ�÷����id
	 */
	@Override
	public int selecthouseid(@Param("buildNumber")String buildNumber,@Param("houseUnit")int houseUnit,@Param("houseNumber")String houseNumber) {
		return houseinfodao.selecthouseid(buildNumber, houseUnit, houseNumber);
	}
	/**
	 * ����ҵ��ʱ���ڷ�����Ϣ�������ҵ��id,��סʱ�䣨Ĭ��Ϊ��ǰϵͳʱ�䣩
	 */
	@Override
	public void updatehouseinfoofownerid(HouseInfo houseinfo) {
		houseinfodao.updatehouseinfoofownerid(houseinfo);
	}
	/**
	 * ҵ��Ǩ��ʱ����ҵ��id��Ϊ�գ���סʱ��Ϊ�գ�״̬��Ϊ0����
	 */
	@Override
	public void updatehouseinfoofowner(int ownerId) {
		houseinfodao.updatehouseinfoofowner(ownerId);
	}
	/**
	 * ģ����ѯ������Ϣ(¥����ţ���Ԫ�ţ�����ţ�ҵ������)
	 */
	@Override
	public List<HouseInfo> selecthouseinfolike(String buildNumber,String houseUnit,String houseNumber,String ownerName) {
		List<HouseInfo> listhouseinfo = houseinfodao.selecthouseinfolike(buildNumber, houseUnit, houseNumber, ownerName);
		return listhouseinfo;
	}
}
