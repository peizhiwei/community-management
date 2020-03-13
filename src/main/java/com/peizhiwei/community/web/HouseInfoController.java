package com.peizhiwei.community.web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

import com.peizhiwei.community.entity.HouseInfo;
import com.peizhiwei.community.entity.HouseType;
import com.peizhiwei.community.entity.JspResult;
import com.peizhiwei.community.service.HouseInfoService;

@Controller
@RequestMapping("/houseinfo")
public class HouseInfoController {
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
	 * 获取所有房间信息
	 * @return
	 */
	@RequestMapping("/getallhouseinfo")
	@ResponseBody
	public List<HouseInfo> getallhouseinfo(){
		List<HouseInfo> listhouseinfo = houseinfoservice.getallhouseinfo();
		return listhouseinfo;
	}
	/**
	 * 获取所有房型信息，并传递个jsp页面
	 * @return
	 */
	@RequestMapping("/getallhousetype")
	@ResponseBody
	public List<HouseType> getallhousetype(){
		List<HouseType> listhousetype=houseinfoservice.getallhousetype();
		return listhousetype;
	}
	/**
	 * 修改房间信息
	 * @return
	 */
	@RequestMapping("/changehouseinfo")
	@ResponseBody
	public JspResult changehouseinfo(
			@RequestParam(value = "houseId",required = false)Integer houseId,
			@RequestParam(value = "houseNumber",required = false)String houseNumber,
			@RequestParam(value = "houseArea",required = false)BigDecimal houseArea,
			@RequestParam(value = "houseType",required = false)String houseType,
			@RequestParam(value = "houseInTime",required = false)Date houseInTime) {
		HouseInfo houseinfo=new HouseInfo();
		HouseType housetypeinfo=new HouseType();
		JspResult rs = new JspResult();
		try {
			houseinfo.setHouseId(houseId);
			houseinfo.setHouseNumber(houseNumber);
			houseinfo.setHouseArea(houseArea);
			List<HouseType> listhousetype= houseinfoservice.getallhousetype();
			for(int i=0;i<listhousetype.size();i++) {
				if(listhousetype.get(i).getHouseTypeName().equals(houseType)) {
					housetypeinfo.setHouseTypeId(listhousetype.get(i).getHouseTypeId());
				}
			}
			housetypeinfo.setHouseTypeName(houseType);
			houseinfo.setHouseType(housetypeinfo);
			houseinfo.setHouseInTime(houseInTime);
			houseinfoservice.updatehouseinfo(houseinfo);
			rs.setFlag(true);
			rs.setMsg("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
}
