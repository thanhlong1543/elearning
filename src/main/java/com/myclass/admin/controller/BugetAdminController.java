package com.myclass.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.myclass.dto.UserDetailDto;
import com.myclass.util.UrlConstants;


public class BugetAdminController {
	/*
	@ModelAttribute
    public void addBugetToModel(Authentication authentication, ModelMap model) {
		
			UserDetailDto userDetailDto = (UserDetailDto) authentication.getPrincipal();
	        model.addAttribute("userDetailDto", userDetailDto);
		
    }
    */
}
