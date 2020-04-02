package com.peizhiwei.community.owner.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peizhiwei.community.admin.entity.HouseOwner;
import com.peizhiwei.community.admin.entity.JspResult;
import com.peizhiwei.community.owner.service.OwnerLoginService;

@Controller
@RequestMapping("/owner")
public class OwnerLoginController {
	@Autowired
	OwnerLoginService ownerloginservice;
	
	
	@RequestMapping("/ownerlogin")
	public String ownerlogin() {
		return "/ownerlogin";
	}
	@RequestMapping("/ownerindex")
	public String ownerindex() {
		return "/ownerindex";
	}
	/**
	 * 验证登录
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping("/checklogin")
	@ResponseBody
	public JspResult checklogin(
			@RequestParam(value = "username",required = false)String username,
			@RequestParam(value = "password",required = false)String password,
			HttpServletRequest request) {
		JspResult rs = new JspResult();
		HouseOwner owner = new HouseOwner();
		owner = ownerloginservice.checkownerlogin(username, password);
		if(owner!=null) {
			request.getSession().setAttribute("owner", owner);
			rs.setFlag(true);
			rs.setMsg("/community/owner/ownerindex");
		}else {
			rs.setFlag(false);
			rs.setMsg("用户名或密码错误");
		}
		return rs;
	}
	/**
	 * 获取当前登录用户的名字
	 * @param session
	 * @return
	 */
	@RequestMapping("/getusername")
	@ResponseBody
	public JspResult getusername(HttpSession session) {
		JspResult rs = new JspResult();
		Object ownersession = session.getAttribute("owner");
		HouseOwner owner = (HouseOwner) ownersession;
		String username = owner.getOwnerName();
		rs.setFlag(true);
		rs.setMsg(username);
		return rs;
	}
	
	/**
	 * 退出
	 * @param session
	 * @return
	 */
	@RequestMapping("/signout")
	@ResponseBody
	public JspResult signout(HttpSession session) {
		JspResult rs = new JspResult();
		session.setAttribute("owner", null);
		rs.setFlag(true);
		rs.setMsg("/community/owner/ownerlogin");
		return rs;
	}
}
