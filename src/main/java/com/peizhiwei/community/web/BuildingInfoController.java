package com.peizhiwei.community.web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.entity.BuildingInfo;
import com.peizhiwei.community.entity.HouseInfo;
import com.peizhiwei.community.entity.HouseType;
import com.peizhiwei.community.entity.HouseUse;
import com.peizhiwei.community.entity.JspResult;
import com.peizhiwei.community.service.BuildingInfoService;
import com.peizhiwei.community.service.HouseInfoService;

@Controller
@RequestMapping("/buildinginfo")
public class BuildingInfoController {
	@Autowired
	private BuildingInfoService buildinginfoservice;
	@Autowired
	private HouseInfoService houseinfoservice;
	/**
	 * ���ڸ�ʽ����ÿһ������ʵ��������һ��ע�⣬������������һ����䣬�������Ա�֤��̨��ҳ��佻�������ڸ�ʽ�Ǳ�׼�ģ�����Ҫ�ٽ��и�ʽ��
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	/**
	 * ��ȡ¥����������Ϣ
	 * @return
	 */
	@RequestMapping("/getallbuildinginfo")
	@ResponseBody
	private List<BuildingInfo> getallbuidinginfo() {
		List<BuildingInfo> buildinginfolist=new ArrayList<BuildingInfo>();
		buildinginfolist=buildinginfoservice.getallbuildinginfo();
		return buildinginfolist;
	}
	
	/**
	 * �޸�¥����Ϣ,����޸ĵı���Ѵ��ڣ����޸�ʧ��
	 * @return
	 */
	@RequestMapping("/changebuildinginfo")
	@ResponseBody
	private JspResult changebuildinginfo(
			@RequestParam(value = "buildId",required = false)Integer buildId,
			@RequestParam(value = "buildNumber",required = false)String buildNumber,
			@RequestParam(value = "buildLayer",required = false)int buildLayer,
			@RequestParam(value = "buildSumHouse",required = false)int buildSumHouse,
			@RequestParam(value = "buildArea",required = false)BigDecimal buildArea,
			@RequestParam(value = "buildStartTime",required = false)Date buildStartTime,
			@RequestParam(value = "buildEndTime",required = false)Date buildEndTime) {
		BuildingInfo buildinginfo=new BuildingInfo();
		JspResult rs = new JspResult();
		try {
			buildinginfo.setBuildNumber(buildNumber);
			buildinginfo.setBuildId(buildId);
			buildinginfo.setBuildLayer(buildLayer);
			buildinginfo.setBuildSumHouse(buildSumHouse);
			buildinginfo.setBuildArea(buildArea);
			buildinginfo.setBuildStartTime(buildStartTime);
			buildinginfo.setBuildEndTime(buildEndTime);
			if(buildinginfoservice.changebuildinfo(buildinginfo)==true) {
				rs.setFlag(true);
				rs.setMsg("�޸ĳɹ�");
			}else {
				rs.setFlag(false);
				rs.setMsg("��¥������Ѵ��ڣ����������룡");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * ɾ��һ��¥����Ϣ�����¥���л���ס����ɾ��ʧ��
	 * @param buildId
	 * @return
	 */
	@RequestMapping("/deletebuildinginfo")
	@ResponseBody
	private JspResult deletebuildinginfo(Integer buildId) {
		JspResult rs = new JspResult();
		try {
			if(buildinginfoservice.deletebuildinfoishouseowner(buildId)==true) {
				rs.setFlag(true);
				rs.setMsg("ɾ���ɹ�");
			}else {
				rs.setFlag(false);
				rs.setMsg("�ö�¥�л���ס�����޷�ɾ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * ����һ��¥����Ϣ���������Ѵ��ڣ�������ʧ��
	 * ͬʱ�������뷿����Ϣ
	 * @param buildNumber
	 * @param buildLayer
	 * @param buildSumHouse
	 * @param buildArea
	 * @param buildStartTime
	 * @param buildEndTime
	 * @return
	 */
	@RequestMapping("/addbuildinginfo")
	@ResponseBody
	private JspResult addbuildinginfo(
			@RequestParam(value = "houseType",required=false)String houseType,
			@RequestParam(value = "houseArea",required = false)BigDecimal houseArea,
			@RequestParam(value = "buildNumber",required = false)String buildNumber,
			@RequestParam(value = "buildLayer",required = false)int buildLayer,
			@RequestParam(value = "buildSumHouse",required = false)int buildSumHouse,
			@RequestParam(value = "buildArea",required = false)BigDecimal buildArea,
			@RequestParam(value = "buildStartTime",required = false)Date buildStartTime,
			@RequestParam(value = "buildEndTime",required = false)Date buildEndTime) {
		JspResult rs = new JspResult();
		BuildingInfo buildinginfo=new BuildingInfo();
		List<HouseInfo> houseinfolist=new ArrayList<HouseInfo>();
		HouseInfo houseinfo=new HouseInfo();
		List<HouseType> listhousetype=new ArrayList<HouseType>();
		HouseType nhousetype=new HouseType();
		HouseUse houseuse = new HouseUse();
		try {
			buildinginfo.setBuildNumber(buildNumber);
			buildinginfo.setBuildLayer(buildLayer);
			buildinginfo.setBuildSumHouse(buildSumHouse);
			buildinginfo.setBuildArea(buildArea);
			buildinginfo.setBuildStartTime(buildStartTime);
			buildinginfo.setBuildEndTime(buildEndTime);
			if(buildinginfoservice.insertbuildinginfo(buildinginfo)==true) {
				for(int i=0;i<buildSumHouse;i++) {//���뷿����Ϣ
					String num=String.format("%03d", i+1);
					String housenumber=buildNumber+num;//ƴ�ӷ����
					houseinfo.setHouseNumber(housenumber);
					BuildingInfo buildinfo = new BuildingInfo();
					buildinfo.setBuildId(buildinginfo.getBuildId());
					houseinfo.setBuildInfo(buildinfo);
					houseinfo.setHouseArea(houseArea);
					listhousetype= houseinfoservice.getallhousetype();
					for(int j=0;j<listhousetype.size();j++) {
						if(listhousetype.get(j).getHouseTypeName().equals(houseType)) {
							nhousetype.setHouseTypeId(listhousetype.get(j).getHouseTypeId());
							houseinfo.setHouseType(nhousetype);
						}
					}
					houseuse.setHouseUseId(1);
					houseinfo.setHouseUse(houseuse);
					houseinfo.setHouseState(0);
					houseinfolist.add(houseinfo);
				}
				buildinginfoservice.inserthouseinfolist(houseinfolist);
				rs.setFlag(true);
				rs.setMsg("���ӳɹ�");
			}else {
				rs.setFlag(false);
				rs.setMsg("¥������Ѵ��ڣ����������룡");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
}