package com.myclass.admin.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myclass.dto.UserDetailDto;
import com.myclass.util.UrlConstants;

@Controller
@RequestMapping(value = UrlConstants.Admin.HOME)
public class HomeAdminController {

	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Authentication authentication, ModelMap modelMap) {
		
		UserDetailDto userDetailDto = (UserDetailDto) authentication.getPrincipal();
		System.out.println(authentication.getPrincipal());
		modelMap.addAttribute("userDetailDto", userDetailDto);
		return UrlConstants.Admin.HOME_VIEW;
	}
}
