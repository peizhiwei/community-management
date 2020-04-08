package com.peizhiwei.community.admin.web.superadmin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.admin.entity.Admin;
import com.peizhiwei.community.admin.service.AdminManageService;

@Controller
@RequestMapping("adminmanage")
public class AdminManageController {
	@Autowired
	AdminManageService adminmanageservice;
	
	/**
	 * 获取所有管理员信息
	 * @return
	 */
	@RequestMapping("/getalladmininfo")
	@ResponseBody
	public List<Admin> getalladmininfo(){
		List<Admin> listadmin = adminmanageservice.getalladmininfo();
		return listadmin;
	}
}
