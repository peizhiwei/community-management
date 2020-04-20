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
	 * ���ڸ�ʽ����ÿһ������ʵ�������һ��ע�⣬�����������һ����䣬�������Ա�֤��̨��ҳ��佻�������ڸ�ʽ�Ǳ�׼�ģ�����Ҫ�ٽ��и�ʽ��
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	/**
	 * ��ҳ��ȡ¥����������Ϣ
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
	 * ��ȡ¥����������Ϣ
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
	 * �޸�¥����Ϣ,����޸ĵı���Ѵ��ڣ����޸�ʧ��
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
				rs.setMsg("�޸ĳɹ�");
			}else {
				rs.setFlag(false);
				rs.setMsg("��¥������Ѵ��ڣ����������룡");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
	public JspResult deletebuildinginfo(Integer buildId) {
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
	 * ����һ��¥����Ϣ���������Ѵ��ڣ������ʧ��
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
				for(int i=1;i<buildUnitSum+1;i++) {//��Ԫ��
					for(int j=1;j<buildLayer+1;j++) {//¥����
						for(int t=1;t<buildUnitSingleLayerRooms+1;t++) {//��Ԫ���㷿����
							HouseInfo houseinfo=new HouseInfo();//ÿ��ѭ����ʱ��Ҫ����newһ�����󣬷���list.add�����Ὣǰ���������ݸ��ǵ���ֻ�������һ��
							String houseNumber = String.valueOf(j)+String.valueOf(0)+String.valueOf(t);//int��תStringƴ�ӳɷ���ţ���302
							houseinfo.setHouseNumber(houseNumber);
							houseinfo.setHouseUnit(i);
							BuildingInfo buildinfo = new BuildingInfo();
							buildinfo.setBuildId(buildinginfo.getBuildId());//��ȡ�ղ����¥����Ϣid
							houseinfo.setBuildInfo(buildinfo);
							houseinfo.setHouseArea(houseArea);
							listhousetype= houseinfoservice.getallhousetype();
							for(int z=0;z<listhousetype.size();z++) {
								if(listhousetype.get(z).getHouseTypeName().equals(houseType)) {
									nhousetype.setHouseTypeId(listhousetype.get(z).getHouseTypeId());
									houseinfo.setHouseType(nhousetype);
								}
							}
							houseinfo.setHouseUse("��ס");
							houseinfo.setHouseState(0);
							houseinfolist.add(houseinfo);
						}
					}
				}
				buildinginfoservice.inserthouseinfolist(houseinfolist);
				rs.setFlag(true);
				rs.setMsg("��ӳɹ�");
			}else {
				rs.setFlag(false);
				rs.setMsg("¥������Ѵ��ڣ����������룡");
			}
		}catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return rs;
	}
	/**
	 * ����ɾ��¥����Ϣ
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
					listnodeletebuildid.add(listbuildId.get(i));//�洢����ɾ����¥��id
				}else {
					buildinginfoservice.deletebuildinfoishouseowner(listbuildId.get(i));
				}
			}
			if(listnodeletebuildid.size()==0) {
				rs.setMsg("ɾ���ɹ�");
				rs.setFlag(true);
			}else {
				String nodeletelist = new String();
				for(int j=0;j<listnodeletebuildid.size();j++) {
					String number = buildinginfoservice.selectbuildnumber(listnodeletebuildid.get(j));
					nodeletelist += number+",";
				}
				rs.setFlag(false);
				rs.setMsg(nodeletelist+"¥��������ס�����޷�ɾ����");
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return rs;
	}
	/**
	 * ����¥�����ģ����ѯ¥����Ϣ
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
