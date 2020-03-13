package com.peizhiwei.community.web;

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
}
