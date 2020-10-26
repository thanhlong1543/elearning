package com.myclass.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclass.dto.RoleDto;
import com.myclass.service.RoleService;
import com.myclass.util.UrlConstants;

@Controller
@RequestMapping(value = "")
public class RoleAdminController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = UrlConstants.Admin.ROLE, method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		List<RoleDto> listRoleDto = roleService.findAll();
		modelMap.addAttribute("listRoleDto", listRoleDto);
		return UrlConstants.Admin.ROLE_VIEW;
	}
	
	@RequestMapping(value = UrlConstants.Admin.ROLE_ADD, method = RequestMethod.GET)
	public String add(ModelMap modelMap) {
		modelMap.addAttribute("roleDto", new RoleDto());
		return UrlConstants.Admin.ROLE_ADD;
	}
	
	@RequestMapping(value = UrlConstants.Admin.ROLE_ADD, method = RequestMethod.POST)
	public String add(ModelMap modelMap,@Valid @ModelAttribute("roleDto") RoleDto roleDto, BindingResult errors) {
//		System.out.println(roleDto.getName().trim().length());
		if (errors.hasErrors()) {
			return UrlConstants.Admin.ROLE_ADD;
		}
		
		try {
			roleService.add(roleDto);
			return "redirect:" + UrlConstants.Admin.ROLE;
		} catch (Exception e) {
			e.printStackTrace();			
		}
		modelMap.addAttribute("message", "Thêm mới thất bại.");
		return UrlConstants.Admin.ROLE_ADD;
	}
	
	@RequestMapping(value = UrlConstants.Admin.ROLE_EDIT, method =  RequestMethod.GET)
	public String edit(@RequestParam("id") int id, ModelMap modelMap) {
		modelMap.addAttribute("roleDto",roleService.findByID(id));
		return UrlConstants.Admin.ROLE_EDIT;
	}
	
	@RequestMapping(value = UrlConstants.Admin.ROLE_EDIT, method = RequestMethod.POST)
	public String edit(ModelMap modelMap,@Valid @ModelAttribute("roleDto") RoleDto roleDto, BindingResult errors) {
		if (errors.hasErrors()) {
			return UrlConstants.Admin.ROLE_EDIT;
		}
		try {
			roleService.update(roleDto);
			return "redirect:" + UrlConstants.Admin.ROLE;
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelMap.addAttribute("message", "Sửa thất bại!");
		return UrlConstants.Admin.ROLE;
	}
	
	@RequestMapping(value = UrlConstants.Admin.ROLE_DELETE, method = RequestMethod.GET)
	public String delete(@RequestParam("id") int id, ModelMap modelMap) {
		roleService.delete(id);
		return "redirect:"+ UrlConstants.Admin.ROLE;
	}
	
}
