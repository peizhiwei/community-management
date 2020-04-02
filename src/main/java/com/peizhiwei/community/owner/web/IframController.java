package com.peizhiwei.community.owner.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owneri")
@Controller
public class IframController {
	@RequestMapping("/ownerselfmessage")
	public String ownerselfmessage() {
		return "/ownerselfmessage";
	}
	@RequestMapping("/ownerfamily")
	public String ownerfamily(){
		return "/ownerfamily";
	}
	@RequestMapping("/ownercomplaint")
	public String ownercomplaint() {
		return "/ownercomplaint";
	}
	@RequestMapping("/ownerrepair")
	public String ownerrepair() {
		return "/ownerrepair";
	}
	@RequestMapping("/ownerpay")
	public String ownerpay() {
		return "/ownerpay";
	}
}
