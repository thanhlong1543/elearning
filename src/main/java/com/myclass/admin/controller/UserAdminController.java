package com.myclass.admin.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.dto.RoleDto;
import com.myclass.dto.UserDto;
import com.myclass.service.RoleService;
import com.myclass.service.UploadPathService;
import com.myclass.service.UserService;
import com.myclass.util.UrlConstants;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "")
public class UserAdminController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UploadPathService uploadPathService;
	
	@RequestMapping(value = UrlConstants.Admin.USER, method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		List<UserDto> userDto =  userService.findAll();
		modelMap.addAttribute("listUser", userDto);
		return UrlConstants.Admin.USER_VIEW;
	}
	
	@RequestMapping(value = UrlConstants.Admin.USER_ADD, method = RequestMethod.GET)
	public String add(ModelMap modelMap) {
		List<RoleDto> roleDto = roleService.findAll();
		modelMap.addAttribute("userDto", new UserDto());
		modelMap.addAttribute("roleDto", roleDto);
		return UrlConstants.Admin.USER_ADD;
	}
	
	@RequestMapping(value = UrlConstants.Admin.USER_ADD, method = RequestMethod.POST)
	public String add(ModelMap modelMap,@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult errors) {
		
		if (errors.hasErrors()) {
			modelMap.addAttribute("roleDto", roleService.findAll());
			return UrlConstants.Admin.USER_ADD;
		}
		try {
			if (userDto.getPassword().compareTo(userDto.getConfirmPassword()) == -1 ) {
				modelMap.addAttribute("message", "Mật khẩu 1 và 2 không trùng nhau!");
				List<RoleDto> roleDto = roleService.findAll();
				//modelMap.addAttribute("roles", roleDto);
				return UrlConstants.Admin.USER_ADD;
			}
			MultipartFile file = userDto.getFile();
			userDto.setAvatar(uploadPathService.doUpload(file, UrlConstants.Admin.FILE_USER));
			userService.add(userDto);
			return "redirect:"+ UrlConstants.Admin.USER;
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelMap.addAttribute("roleDto", roleService.findAll());
		modelMap.addAttribute("message","Thêm thất bại!");
		return UrlConstants.Admin.USER_ADD;
	}
	
	@RequestMapping(value = UrlConstants.Admin.USER_EDIT, method = RequestMethod.GET)
	public String edit(ModelMap modelMap, @RequestParam("id") int id) {
		UserDto userDto = userService.findByID(id);
		List<RoleDto> listRoleDto = roleService.findAll();
		modelMap.addAttribute("userDto", userDto);
		modelMap.addAttribute("roleDto", listRoleDto);
		return UrlConstants.Admin.USER_EDIT;
	}
	
	@RequestMapping(value = UrlConstants.Admin.USER_EDIT, method = RequestMethod.POST)
	public String edit(ModelMap modelMap,@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult errors) {
		
		if (errors.hasErrors()) {
			List<RoleDto> listRoleDto = roleService.findAll();
			modelMap.addAttribute("roleDto", listRoleDto);
			return UrlConstants.Admin.USER_EDIT;
		}
		try {
			if (userDto.getPassword().compareTo(userDto.getConfirmPassword()) == -1 ) {
				modelMap.addAttribute("message", "Mật khẩu 1 và 2 không trùng nhau!");
				List<RoleDto> listRoleDto = roleService.findAll();
				modelMap.addAttribute("roleDto", listRoleDto);
				return UrlConstants.Admin.USER_EDIT;
			}
			if (userDto.getFile().getSize() > 0){
				System.out.println("getAvatar = " + userDto.getAvatar());
				uploadPathService.DeleteFile(userDto.getAvatar(), UrlConstants.Admin.FILE_USER);
				userDto.setAvatar(uploadPathService.doUpload(userDto.getFile(), UrlConstants.Admin.FILE_USER));
			}
			userService.update(userDto);
			return "redirect:" + UrlConstants.Admin.USER;
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<RoleDto> listRoleDto = roleService.findAll();
		modelMap.addAttribute("roleDto", listRoleDto);
		modelMap.addAttribute("message", "Thêm thất bại!");
		return UrlConstants.Admin.USER_EDIT;
	}

	@RequestMapping(value = "/admin/user/detail", method = RequestMethod.GET)
	public String details(){
		return "/admin/user/detail";
	}
	
	@RequestMapping(value = UrlConstants.Admin.USER_DELETE, method = RequestMethod.GET)
	public String delete(ModelMap modelMap, @RequestParam("id") int id) {
		userService.delete(id);
		return "redirect:"+ UrlConstants.Admin.USER;
	}
}
