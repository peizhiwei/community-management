package com.peizhiwei.community.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peizhiwei.community.admin.dao.BuildingInfoDao;
import com.peizhiwei.community.admin.entity.BuildingInfo;
import com.peizhiwei.community.admin.entity.HouseInfo;
import com.peizhiwei.community.admin.service.BuildingInfoService;
import com.peizhiwei.community.util.Pager;

@Service
@Transactional(rollbackFor = Exception.class)
public class BuildingInfoServiceImpl implements BuildingInfoService {
	@Autowired
	private BuildingInfoDao buildinginfodao;
	/**
	 * ��ҳ��ѯ����¥����Ϣ
	 */
	@Override
	public Pager<BuildingInfo> pagegetallbuildinginfo(int page,int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size", size);
		List<BuildingInfo> buildinginfolist = buildinginfodao.pagegetallbuildinginfo(params);
		Pager<BuildingInfo> pager = new Pager<BuildingInfo>();
		pager.setRows(buildinginfolist);
		pager.setTotal(buildinginfodao.count());
		return pager;
	}
	/**
	 * ��ѯ����¥����Ϣ
	 */
	@Override
	public List<BuildingInfo> getallbuildinginfo() {
		List<BuildingInfo> listbuildinginfo = buildinginfodao.getallbuildinginfo();
		return listbuildinginfo;
	}
	/**
	 * ����¥������ж����ݿ����Ƿ��Ѵ��ڸñ��,���ڷ���true,�����ڷ���false
	 */
	@Override
	public boolean selectnumberisnull(String buildNumber) {
		if(buildinginfodao.selectnumberisnull(buildNumber)==1) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * �޸�¥����Ϣ
	 */
	@Override
	public void changebuildinfo(BuildingInfo buildinginfo) {
		buildinginfodao.changebuildinfo(buildinginfo);
	}
	/**
	 * ɾ��һ��¥����Ϣ,���¥����ס��������ɾ�������û��ס������ͬʱɾ���������ڸ�¥�ķ�����Ϣ
	 */
	@Override
	public boolean deletebuildinfoishouseowner(int buildId) {
		boolean flag = true;
		try {
			if (buildinginfodao.selectbuildhouseisowner(buildId)) {
				// ��ס��������ɾ��
				flag = false;
			} else {
				// û��ס��������ɾ��
				flag = true;
				buildinginfodao.deletehouseinfoofbuild(buildId);
				buildinginfodao.deletebuildinfo(buildId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * ���¥����Ϣ ���Ҫ��ӵ�¥������Ѵ��ڣ������ʧ��
	 */
	@Override
	public boolean insertbuildinginfo(BuildingInfo buildinginfo) {
		boolean flag = true;
		try {
			List<BuildingInfo> listbuildinfo = buildinginfodao.getallbuildinginfo();
			int list = listbuildinfo.size();
			int i;
			for (i = 0; i < list; i++) {
				// ���Ҫ��ӵ�¥������Ѵ��ڣ������ʧ��
				if (listbuildinfo.get(i).getBuildNumber().equals(buildinginfo.getBuildNumber())) {
					flag = false;
					break;
				}
			}
			if (i == list) {
				flag = true;
				buildinginfodao.insertbuildinfo(buildinginfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * �������ӷ�����Ϣ
	 */
	@Override
	public void inserthouseinfolist(List<HouseInfo> houseinfolist) {
		buildinginfodao.inserthouseinfolist(houseinfolist);		
	}
	/**
	 * ����¥��id����¥�����
	 */
	@Override
	public String selectbuildnumber(int buildId) {
		String number = buildinginfodao.selectbuildnumber(buildId);
		return number;
	}
	/**
	 * ����¥�����ģ����ѯ¥����Ϣ
	 */
	@Override
	public Pager<BuildingInfo> selectlikebuildinginfo(String buildNumber,int page,int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", (page-1)*size);
		params.put("size", size);
		List<BuildingInfo> listbuildinginfo = buildinginfodao.selectlikebuildinginfo(buildNumber,params);
		Pager<BuildingInfo> pager = new Pager<BuildingInfo>();
		pager.setRows(listbuildinginfo);
		pager.setTotal(buildinginfodao.likecount(buildNumber));
		return pager;
	}
}
