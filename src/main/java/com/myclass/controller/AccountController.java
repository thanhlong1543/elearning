package com.myclass.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.dto.UserDto;
import com.myclass.service.CategoryService;
import com.myclass.service.UploadPathService;
import com.myclass.service.UserService;
import com.myclass.util.UrlConstants;


@Controller
@RequestMapping(value = "")
public class AccountController {
	@Autowired
    private CategoryService categoryService;
	@Autowired
	private UserService userService;
	@Autowired
	private UploadPathService uploadPathService;
	
	@RequestMapping(value = UrlConstants.Client.ACCOUNT_REGISTER, method = RequestMethod.GET)
	public String register(ModelMap modelMap) {
		modelMap.addAttribute("userDto", new UserDto());
		return UrlConstants.Client.ACCOUNT_VIEW_REGISTER;
	}
	
	@RequestMapping(value = UrlConstants.Client.ACCOUNT_REGISTER, method = RequestMethod.POST)
	public String register(ModelMap modelMap,@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult errors) {
		if (errors.hasErrors()) {
			
			return UrlConstants.Client.ACCOUNT_VIEW_REGISTER;
		}
		if (userService.findByEmail(userDto) == true) {
			errors.rejectValue("email", "error.user", "Tài khoảng Email đã tồn tại.");
			return UrlConstants.Client.ACCOUNT_VIEW_REGISTER;
		}
		try {
			if (userDto.getPassword().compareTo(userDto.getConfirmPassword()) == -1 ) {
				modelMap.addAttribute("message", "Mật khẩu 1 và 2 không trùng nhau!");
				return UrlConstants.Client.ACCOUNT_VIEW_REGISTER;
			}
			MultipartFile file = userDto.getFile();
			userDto.setAvatar(uploadPathService.doUpload(file, UrlConstants.Admin.FILE_USER));
			userDto.setRole_id(3);
			userService.add(userDto);
			 modelMap.addAttribute("categoryDto", categoryService.findAll());
			return UrlConstants.Client.ACCOUNT_VIEW_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UrlConstants.Client.ACCOUNT_VIEW_REGISTER;
	}
	
	@RequestMapping(value = UrlConstants.Client.ACCOUNT_SUCCESS, method = RequestMethod.GET)
	public String success(ModelMap modelMap) {

        modelMap.addAttribute("categoryDto", categoryService.findAll());
		return UrlConstants.Client.ACCOUNT_VIEW_SUCCESS;
	}
	
	@RequestMapping(value = UrlConstants.Client.ACCOUNT_LOGIN, method = RequestMethod.GET)
	public String login(ModelMap modelMap) {
		modelMap.addAttribute("categoryDto", categoryService.findAll());
		return UrlConstants.Client.ACCOUNT_VIEW_LOGIN;
	}
	
}
