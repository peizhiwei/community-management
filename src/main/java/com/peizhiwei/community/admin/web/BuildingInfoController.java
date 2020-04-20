package com.peizhiwei.community.admin.web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.admin.entity.BuildingInfo;
import com.peizhiwei.community.admin.entity.HouseInfo;
import com.peizhiwei.community.admin.entity.HouseType;
import com.peizhiwei.community.admin.entity.JspResult;
import com.peizhiwei.community.admin.service.BuildingInfoService;
import com.peizhiwei.community.admin.service.HouseInfoService;
import com.peizhiwei.community.util.Pager;

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
	 * 分页获取楼栋的所有信息
	 * @return
	 */
	@RequestMapping("/pagegetallbuildinginfo")
	@ResponseBody
	public Pager<BuildingInfo> pagegetallbuidinginfo(int page,int size) {
		Pager<BuildingInfo> pagebuildinginfo=new Pager<BuildingInfo>();
		pagebuildinginfo = buildinginfoservice.pagegetallbuildinginfo(page, size);
		return pagebuildinginfo;
	}
	/**
	 * 获取楼栋的所有信息
	 * @return
	 */
	@RequestMapping("/getallbuildinginfo")
	@ResponseBody
	public List<BuildingInfo> getallbuidinginfo() {
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
	public JspResult changebuildinginfo(
			@RequestParam(value = "buildId",required = false)Integer buildId,
			@RequestParam(value = "oldbuildNumber",required = false)String oldbuildNumber,
			@RequestParam(value = "buildNumber",required = false)String buildNumber,
			@RequestParam(value = "buildLayer",required = false)int buildLayer,
			@RequestParam(value = "buildSumHouse",required = false)int buildSumHouse,
			@RequestParam(value = "buildArea",required = false)BigDecimal buildArea,
			@RequestParam(value = "buildStartTime",required = false)Date buildStartTime,
			@RequestParam(value = "buildEndTime",required = false)Date buildEndTime) {
		BuildingInfo buildinginfo=new BuildingInfo();
		JspResult rs = new JspResult();
		try {
			if(buildinginfoservice.selectnumberisnull(buildNumber)==false||oldbuildNumber.equals(buildNumber)) {
				buildinginfo.setBuildNumber(buildNumber);
				buildinginfo.setBuildId(buildId);
				buildinginfo.setBuildLayer(buildLayer);
				buildinginfo.setBuildSumHouse(buildSumHouse);
				buildinginfo.setBuildArea(buildArea);
				buildinginfo.setBuildStartTime(buildStartTime);
				buildinginfo.setBuildEndTime(buildEndTime);
				buildinginfoservice.changebuildinfo(buildinginfo);
				rs.setFlag(true);
				rs.setMsg("修改成功");
			}else {
				rs.setFlag(false);
				rs.setMsg("该楼栋编号已存在，请重新输入！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
	public JspResult deletebuildinginfo(Integer buildId) {
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
	public JspResult addbuildinginfo(
			@RequestParam(value = "houseType",required=false)String houseType,
			@RequestParam(value = "houseArea",required = false)BigDecimal houseArea,
			@RequestParam(value = "buildNumber",required = false)String buildNumber,
			@RequestParam(value = "buildUnitSum",required = false)int buildUnitSum,
			@RequestParam(value = "buildLayer",required = false)int buildLayer,
			@RequestParam(value = "buildUnitSingleLayerRooms",required = false)int buildUnitSingleLayerRooms,
			@RequestParam(value = "buildArea",required = false)BigDecimal buildArea,
			@RequestParam(value = "buildStartTime",required = false)Date buildStartTime,
			@RequestParam(value = "buildEndTime",required = false)Date buildEndTime) {
		JspResult rs = new JspResult();
		BuildingInfo buildinginfo=new BuildingInfo();
		List<HouseInfo> houseinfolist=new ArrayList<HouseInfo>();
		
		List<HouseType> listhousetype=new ArrayList<HouseType>();
		HouseType nhousetype=new HouseType();
		try {
			buildinginfo.setBuildNumber(buildNumber);
			buildinginfo.setBuildUnitSum(buildUnitSum);
			buildinginfo.setBuildLayer(buildLayer);
			buildinginfo.setBuildUnitSingleLayerRooms(buildUnitSingleLayerRooms);
			int buildSumHouse = buildUnitSum*buildLayer*buildUnitSingleLayerRooms;
			buildinginfo.setBuildSumHouse(buildSumHouse);
			buildinginfo.setBuildArea(buildArea);
			buildinginfo.setBuildStartTime(buildStartTime);
			buildinginfo.setBuildEndTime(buildEndTime);
			if(buildinginfoservice.insertbuildinginfo(buildinginfo)==true) {
				for(int i=1;i<buildUnitSum+1;i++) {//单元数
					for(int j=1;j<buildLayer+1;j++) {//楼层数
						for(int t=1;t<buildUnitSingleLayerRooms+1;t++) {//单元单层房间数
							HouseInfo houseinfo=new HouseInfo();//每次循环的时候都要重新new一个对象，否则list.add方法会将前面插入的数据覆盖掉，只保留最后一个
							String houseNumber = String.valueOf(j)+String.valueOf(0)+String.valueOf(t);//int型转String拼接成房间号，如302
							houseinfo.setHouseNumber(houseNumber);
							houseinfo.setHouseUnit(i);
							BuildingInfo buildinfo = new BuildingInfo();
							buildinfo.setBuildId(buildinginfo.getBuildId());//获取刚插入的楼栋信息id
							houseinfo.setBuildInfo(buildinfo);
							houseinfo.setHouseArea(houseArea);
							listhousetype= houseinfoservice.getallhousetype();
							for(int z=0;z<listhousetype.size();z++) {
								if(listhousetype.get(z).getHouseTypeName().equals(houseType)) {
									nhousetype.setHouseTypeId(listhousetype.get(z).getHouseTypeId());
									houseinfo.setHouseType(nhousetype);
								}
							}
							houseinfo.setHouseUse("居住");
							houseinfo.setHouseState(0);
							houseinfolist.add(houseinfo);
						}
					}
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
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return rs;
	}
	/**
	 * 批量删除楼栋信息
	 * @param listbuildId
	 * @return
	 */
	@RequestMapping("/checkdelete")
	@ResponseBody
	public JspResult checkdelete(@RequestBody List<Integer> listbuildId) {
		JspResult rs = new JspResult();
		List<Integer> listnodeletebuildid = new ArrayList<Integer>();
		try {
			for(int i=0;i<listbuildId.size();i++) {
				if(buildinginfoservice.deletebuildinfoishouseowner(listbuildId.get(i))==false) {
					listnodeletebuildid.add(listbuildId.get(i));//存储不能删除的楼栋id
				}else {
					buildinginfoservice.deletebuildinfoishouseowner(listbuildId.get(i));
				}
			}
			if(listnodeletebuildid.size()==0) {
				rs.setMsg("删除成功");
				rs.setFlag(true);
			}else {
				String nodeletelist = new String();
				for(int j=0;j<listnodeletebuildid.size();j++) {
					String number = buildinginfoservice.selectbuildnumber(listnodeletebuildid.get(j));
					nodeletelist += number+",";
				}
				rs.setFlag(false);
				rs.setMsg(nodeletelist+"楼栋中已有住户，无法删除！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return rs;
	}
	/**
	 * 根据楼栋编号模糊查询楼栋信息
	 * @param buildNumber
	 * @return
	 */
	@RequestMapping("/selectlikebuildinginfo")
	@ResponseBody
	public Pager<BuildingInfo> selectlikebuildinginfo(@RequestParam(value = "buildNumber",required = false)String buildNumber,
			@RequestParam(value = "page",required = false)int page,
			@RequestParam(value = "size",required = false)int size){
		Pager<BuildingInfo> pagebuildinfo = buildinginfoservice.selectlikebuildinginfo(buildNumber,page,size);
		return pagebuildinfo;
	}
}
