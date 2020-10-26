package com.myclass.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin/login")
public class AccountAdminController {
	
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String login() {
		return "/admin/account/index";
	}
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String alogin() {
		return "/admin/account/index";
	}
}
