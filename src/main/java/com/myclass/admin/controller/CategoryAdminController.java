package com.myclass.admin.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclass.dto.CategoryDto;
import com.myclass.dto.UserDetailDto;
import com.myclass.service.CategoryService;
import com.myclass.util.UrlConstants;

@Controller
@RequestMapping(value = "")
public class CategoryAdminController {

	@Autowired
	private CategoryService categoryService;
	
	
	
	public void init(Authentication authentication, ModelMap model) {
		UserDetailDto userDetailDto = (UserDetailDto) authentication.getPrincipal();
        model.addAttribute("userDetailDto", userDetailDto);
	}
	
	@RequestMapping(value = UrlConstants.Admin.CATEGORY, method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		List<CategoryDto> listCategoryDto = categoryService.findAll();
		modelMap.addAttribute("listCategoryDto", listCategoryDto);
		return UrlConstants.Admin.CATEGORY_VIEW;
	}
	
	@RequestMapping(value = UrlConstants.Admin.CATEGORY_ADD, method = RequestMethod.GET)
	public String add(ModelMap modelMap) {
		modelMap.addAttribute("categoryDto", new CategoryDto());
		return UrlConstants.Admin.CATEGORY_ADD;
	}
	
	@RequestMapping(value = UrlConstants.Admin.CATEGORY_ADD, method = RequestMethod.POST)
	public String add(ModelMap modelMap, @ModelAttribute("categoryDto") CategoryDto categoryDto, BindingResult errors) {
		if (errors.hasErrors()) {
			return UrlConstants.Admin.CATEGORY_ADD;
		}
		try {
			categoryService.add(categoryDto);
			return "redirect:"+ UrlConstants.Admin.CATEGORY;
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelMap.addAttribute("message", "Thêm mới thất bại!");
		return UrlConstants.Admin.CATEGORY_ADD;
	}
	
	@RequestMapping(value = UrlConstants.Admin.CATEGORY_EDIT, method = RequestMethod.GET)
	public String edit(@RequestParam("id") int id, ModelMap modelMap) {
		//Category category = categoryService.findById(id);
		modelMap.addAttribute("categoryDto", categoryService.findById(id));
		return UrlConstants.Admin.CATEGORY_EDIT;
	}
	
	@RequestMapping(value = UrlConstants.Admin.CATEGORY_EDIT, method = RequestMethod.POST)
	public String edit(ModelMap modelMap, @ModelAttribute("categoryDto") CategoryDto categoryDto, BindingResult errors) {
		if (categoryDto.getTitle().isEmpty()) {
			errors.reject("title","Vui lòng nhập tên");
		}
		if (errors.hasErrors()) {
			return UrlConstants.Admin.CATEGORY_EDIT;
		}
		try {
			categoryService.update(categoryDto);
			return "redirect:" + UrlConstants.Admin.CATEGORY;
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelMap.addAttribute("message", "Sửa thất bại!");
		return UrlConstants.Admin.CATEGORY_EDIT;
	}
	
	@RequestMapping(value = UrlConstants.Admin.CATEGORY_DELETE, method = RequestMethod.GET)
	public String delete(@RequestParam("id") int id) {
		
		categoryService.delete(id);
		return "redirect:" + UrlConstants.Admin.CATEGORY;
	}
}
