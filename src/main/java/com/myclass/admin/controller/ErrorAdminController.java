package com.myclass.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myclass.util.UrlConstants;

@Controller
@RequestMapping(value = "")
public class ErrorAdminController {
	
	@RequestMapping(value = UrlConstants.Admin.ERROR_403, method = RequestMethod.GET)
	public String error403() {
		
		
		return UrlConstants.Admin.ERROR_VIEW_403;
	}
}
