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
	 * ���ڸ�ʽ����ÿһ������ʵ�������һ��ע�⣬�����������һ����䣬�������Ա�֤��̨��ҳ��佻�������ڸ�ʽ�Ǳ�׼�ģ�����Ҫ�ٽ��и�ʽ��
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	/**
	 * ��ȡ����ͣ��λ��Ϣ
	 * @return
	 */
	@RequestMapping("/getallparkinginfo")
	@ResponseBody
	public List<Parking> getallparkinginfo(){
		List<Parking> listparkinginfo = parkingservice.getallparkinginfo();
		return listparkinginfo;
	}
	/**
	 * ����ͣ��λҵ��
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
			rs.setMsg("�޸ĳɹ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
		
	}
	/**
	 * ������λ�۸�
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
		rs.setMsg("�޸ĳɹ���");
		return rs;
	}
	/**
	 * ����ͣ��λ�����޸ĸ�ͣ��λ��Ϣ
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
			rs.setMsg("���۳ɹ���");
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
			rs.setMsg("�ջسɹ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * ��������ͣ��λ��Ϣ
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
		int maxnumber = Integer.parseInt(maxNumber);//���ַ���ת������
		try {
			for(int i=0;i<parkingSum;i++) {
				Parking parking = new Parking();
				String parkingNumber = String.format("%03d", maxnumber+i+1);//ƴ�ӳ�λ�ţ���λ��ǰ�治������0��
				parking.setParkingNumber(parkingNumber);
				parking.setParkingPrice(parkingPrice);
				listparkinginfo.add(parking);
			}
			parkingservice.insertparkinginfo(listparkinginfo);
			rs.setFlag(true);
			rs.setMsg("��ӳɹ���");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
}
