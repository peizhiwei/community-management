package com.peizhiwei.community.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/in")
public class BackController {
	@RequestMapping("/buildinginfo")
	private String buildinginfo() {
		return "/buildinginfo";
	}
	@RequestMapping("/houseinfo")
	private String houseinfo() {
		return "/houseinfo";
	}
	@RequestMapping("/houseownerinfo")
	private String houseownerinfo() {
		return "/houseownerinfo";
	}
	@RequestMapping("/family")
	private String family() {
		return "/family";
	}
	@RequestMapping("/parking")
	private String parking() {
		return "/parking";
	}
	@RequestMapping("/complaint")
	private String complaint() {
		return "/complaint";
	}
	@RequestMapping("/repair")
	private String repair() {
		return "/repair";
	}
	@RequestMapping("/paytype")
	private String paytype() {
		return "/paytype";
	}
	@RequestMapping("/payinfodetails")
	private String payinfodetails() {
		return "/payinfodetails";
	}
	@RequestMapping("/payinfo")
	private String payinfo() {
		return "/payinfo";
	}
	@RequestMapping("/paymethod")
	private String paymethod() {
		return "/paymethod";
	}
	@RequestMapping("/payinfosummary")
	private String payinfosummary() {
		return "/payinfosummary";
	}
	@RequestMapping("/adminselfmessage")
	private String adminselfmessage() {
		return "/adminselfmessage";
	}
	@RequestMapping("/adminmanage")
	private String adminmanage() {
		return "/adminmanage";
	}
}
