package com.peizhiwei.community.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.admin.service.AdminIndexService;

@RequestMapping("/adminindex")
@Controller
public class AdminIndexController {
	@Autowired
	AdminIndexService adminindexservice;
	/**
	 * 获取房间总数
	 * @return
	 */
	@RequestMapping("/gethouseinfosum")
	@ResponseBody
	public int gethouseinfosum() {
		int houseinfosum = adminindexservice.selecthousesum();
		return houseinfosum;
	}
	/**
	 * 获取空房间数
	 * @return
	 */
	@RequestMapping("/getnullhouseinfo")
	@ResponseBody
	public int getnullhouseinfo() {
		int nullhouse = adminindexservice.selectnullhouse();
		return nullhouse;
	}
	/**
	 * 获取业主总数
	 */
	@RequestMapping("getownersum")
	@ResponseBody
	public int getownersum() {
		int ownersum = adminindexservice.selectownersum();
		return ownersum;
	}
	/**
	 * 获取男业主数
	 */
	@RequestMapping("/getmanofowner")
	@ResponseBody
	public int getmanowner() {
		int manofowner = adminindexservice.selectmanofowner();
		return manofowner;
	}
	/**
	 * 获取住户总数
	 */
	@RequestMapping("/getresidentsum")
	@ResponseBody
	public int getresidentsum() {
		int residentsum = adminindexservice.selectresidentsum();
		return residentsum;
	}
	/**
	 * 获取男住户数
	 */
	@RequestMapping("/getmanresidentsum")
	@ResponseBody
	public int getmanresidentsum() {
		int manresidentsum = adminindexservice.selectmanresidentsum();
		return manresidentsum;
	}
	/**
	 * 获取停车位总数
	 */
	@RequestMapping("/getparkingsum")
	@ResponseBody
	public int getparkingsum() {
		int parkingSum = adminindexservice.selectparkingsum();
		return parkingSum;
	}
	/**
	 * 获取空闲停车位数
	 */
	@RequestMapping("/getnullparkingsum")
	@ResponseBody
	public int getnullparkingsum() {
		int nullparkingsum = adminindexservice.selectnullparkingsum();
		return nullparkingsum;
	}
	
}
