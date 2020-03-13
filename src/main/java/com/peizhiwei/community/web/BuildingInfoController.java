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
	 * 日期格式，在每一个日期实体上添加一个注解，再在这里添加一下语句，这样可以保证后台和页面间交互的日期格式是标准的，不需要再进行格式化
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	/**
	 * 获取楼栋的所有信息
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
	 * 修改楼栋信息,如果修改的编号已存在，则修改失败
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
				rs.setMsg("修改成功");
			}else {
				rs.setFlag(false);
				rs.setMsg("该楼栋编号已存在，请重新输入！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * 删除一条楼栋信息，如果楼栋中还有住户则删除失败
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
				rs.setMsg("删除成功");
			}else {
				rs.setFlag(false);
				rs.setMsg("该栋楼中还有住户，无法删除");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * 新增一条楼栋信息，如果编号已存在，则添加失败
	 * 同时批量插入房间信息
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
				for(int i=0;i<buildSumHouse;i++) {//插入房间信息
					String num=String.format("%03d", i+1);
					String housenumber=buildNumber+num;//拼接房间号
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
				rs.setMsg("添加成功");
			}else {
				rs.setFlag(false);
				rs.setMsg("楼栋编号已存在，请重新输入！");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
}
