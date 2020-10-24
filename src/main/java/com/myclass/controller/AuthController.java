package com.myclass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class AuthController {
	
	@RequestMapping(value = "",method = RequestMethod.GET)
	public String login() {
		
		return "login/index";
	}
	
	@RequestMapping(value = "",method = RequestMethod.POST)
	public String login(Model model) {
		
		return "login/index";
	}
	
	
}