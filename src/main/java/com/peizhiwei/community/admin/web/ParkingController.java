package com.peizhiwei.community.admin.web;

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

import com.peizhiwei.community.admin.entity.HouseOwner;
import com.peizhiwei.community.admin.entity.JspResult;
import com.peizhiwei.community.admin.entity.Parking;
import com.peizhiwei.community.admin.service.HouseOwnerService;
import com.peizhiwei.community.admin.service.ParkingService;

@Controller
@RequestMapping("/parkinginfo")
public class ParkingController {
	@Autowired
	ParkingService parkingservice;
	@Autowired
	HouseOwnerService houseownerservice;
	
	/**
	 * 日期格式，在每一个日期实体上添加一个注解，再在这里添加一下语句，这样可以保证后台和页面间交互的日期格式是标准的，不需要再进行格式化
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	/**
	 * 获取所有停车位信息
	 * @return
	 */
	@RequestMapping("/getallparkinginfo")
	@ResponseBody
	public List<Parking> getallparkinginfo(){
		List<Parking> listparkinginfo = parkingservice.getallparkinginfo();
		return listparkinginfo;
	}
	/**
	 * 更改停车位业主
	 * @param parkingId
	 * @param houseNumber
	 * @param parkingSellTime
	 * @param ParkingPrice
	 * @return
	 */
	@RequestMapping("/updateparkinginfo")
	@ResponseBody
	public JspResult updateparkinginfo(
			@RequestParam(value = "parkingId",required = false)Integer parkingId,
			@RequestParam(value = "houseNumber",required = false)String houseNumber,
			@RequestParam(value = "parkingSellTime",required = false)Date parkingSellTime,
			@RequestParam(value = "ParkingPrice",required = false)BigDecimal ParkingPrice) {
		Parking parking = new Parking();
		JspResult rs = new JspResult();
		HouseOwner houseowner = new HouseOwner();
		try {
			parking.setParkingId(parkingId);
			parking.setParkingSellTime(parkingSellTime);
			parking.setParkingPrice(ParkingPrice);
			houseowner = houseownerservice.gethouseownerinfoaccordinghousenumber(houseNumber);
			parking.setHouseOwner(houseowner);
			parkingservice.updateparkinginfo(parking);
			rs.setFlag(true);
			rs.setMsg("修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
		
	}
	/**
	 * 调整车位价格
	 * @param parkingId
	 * @param parkingPrice
	 * @return
	 */
	@RequestMapping("/updateparkingprice")
	@ResponseBody
	public JspResult updateparkingprice(Integer parkingId, BigDecimal parkingPrice) {
		JspResult rs = new JspResult();
		Parking parking = new Parking();
		parking.setParkingId(parkingId);
		parking.setParkingPrice(parkingPrice);
		parkingservice.updateparkingprice(parking);
		rs.setFlag(true);
		rs.setMsg("修改成功！");
		return rs;
	}
	/**
	 * 出售停车位，并修改该停车位信息
	 * @param parkingId
	 * @param ownerName
	 * @param houseNumber
	 * @param parkingSellTime
	 * @param parkingPrice
	 * @return
	 */
	@RequestMapping("/sellupdateparkinginfo")
	@ResponseBody
	public JspResult sellupdateparkinginfo(
			@RequestParam(value = "parkingId",required = false)Integer parkingId,
			@RequestParam(value = "ownerName",required = false)String ownerName,
			@RequestParam(value = "houseNumber",required = false)String houseNumber,
			@RequestParam(value = "parkingSellTime",required = false)Date parkingSellTime,
			@RequestParam(value = "parkingPrice",required = false)BigDecimal parkingPrice) {
		JspResult rs = new JspResult();
		Parking parking = new Parking();
		HouseOwner houseowner = new HouseOwner();
		try {
			parking.setParkingId(parkingId);
			houseowner = houseownerservice.gethouseownerinfoaccordinghousenumber(houseNumber);
			parking.setHouseOwner(houseowner);
			parking.setParkingSellTime(parkingSellTime);
			parking.setParkingPrice(parkingPrice);
			parkingservice.sellupdateparkinginfo(parking);
			rs.setFlag(true);
			rs.setMsg("出售成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	@RequestMapping("/takebackupdateparkinginfo")
	@ResponseBody
	public JspResult takebackupdateparkinginfo(Integer parkingId) {
		JspResult rs = new JspResult();
		try {
			parkingservice.takebackupdateparkinginfo(parkingId);
			rs.setFlag(true);
			rs.setMsg("收回成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * 批量增加停车位信息
	 * @param parkingSum
	 * @param parkingPrice
	 * @return
	 */
	@RequestMapping("/insertparkinginfo")
	@ResponseBody
	public JspResult insertparkinginfo(
			@RequestParam(value = "parkingSum",required = false)int parkingSum,
			@RequestParam(value = "parkingPrice",required = false)BigDecimal parkingPrice) {
		JspResult rs = new JspResult();
		List<Parking> listparkinginfo = new ArrayList<Parking>();
		String maxNumber = parkingservice.getparkinginfomaxparkingnumber();
		int maxnumber = Integer.parseInt(maxNumber);//将字符串转成数字
		try {
			for(int i=0;i<parkingSum;i++) {
				Parking parking = new Parking();
				String parkingNumber = String.format("%03d", maxnumber+i+1);//拼接车位号，三位，前面不够的用0补
				parking.setParkingNumber(parkingNumber);
				parking.setParkingPrice(parkingPrice);
				listparkinginfo.add(parking);
			}
			parkingservice.insertparkinginfo(listparkinginfo);
			rs.setFlag(true);
			rs.setMsg("添加成功！");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
}
