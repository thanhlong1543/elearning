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

import com.myclass.dto.CategoryDto;
import com.myclass.dto.CourseDto;
import com.myclass.service.CategoryService;
import com.myclass.service.CourseService;
import com.myclass.service.UploadPathService;
import com.myclass.util.UrlConstants;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "") // ko hay
public class CourseAdminController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UploadPathService uploadPathService;
	
	@RequestMapping(value = UrlConstants.Admin.COURSE, method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		List<CourseDto> courseDto = courseService.findAll();
		modelMap.addAttribute("courseDto",courseDto);
		return UrlConstants.Admin.COURSE_VIEW;
	}
	
	@RequestMapping(value = UrlConstants.Admin.COURSE_ADD, method = RequestMethod.GET)
	public String add(ModelMap modelMap) {
		List<CategoryDto> categoryDto = categoryService.findAll();
		modelMap.addAttribute("courseDto", new CourseDto());
		modelMap.addAttribute("categoryDto", categoryDto);
		return UrlConstants.Admin.COURSE_ADD;
	}
	
	@RequestMapping(value = UrlConstants.Admin.COURSE_ADD, method = RequestMethod.POST)
	public String add(ModelMap modelMap,@Valid @ModelAttribute("courseDto") CourseDto courseDto,
			BindingResult errors) {
		
		if (errors.hasErrors()) {
			modelMap.addAttribute("categoryDto", categoryService.findAll());
			return UrlConstants.Admin.COURSE_ADD;
		}
		try {
			MultipartFile fileImage = courseDto.getFileImage();
			courseDto.setImage(uploadPathService.doUpload(fileImage, UrlConstants.Admin.FILE_COURSE));
			courseService.add(courseDto);
			return "redirect:" + UrlConstants.Admin.COURSE;
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelMap.addAttribute("categoryDto", categoryService.findAll());
		modelMap.addAttribute("message", "Thêm mới thất bại!");
		return  UrlConstants.Admin.COURSE_ADD;
	}
	
	@RequestMapping(value = UrlConstants.Admin.COURSE_EDIT, method = RequestMethod.GET)
	public String edit(ModelMap modelMap, @RequestParam("id") int id) {
		List<CategoryDto> categoryDto = categoryService.findAll();
		modelMap.addAttribute("courseDto", courseService.findById(id));
		modelMap.addAttribute("categoryDto", categoryDto);
		return UrlConstants.Admin.COURSE_EDIT;
	}
	
	@RequestMapping(value = UrlConstants.Admin.COURSE_EDIT, method = RequestMethod.POST)
	public String edit(ModelMap modelMap, @ModelAttribute("courseDto") CourseDto courseDto, BindingResult errors) {
		if (errors.hasErrors()) {
			return UrlConstants.Admin.COURSE_EDIT;
		}
		try {
			//handle the File
			if (courseDto.getFileImage().getSize() > 0) {
				uploadPathService.DeleteFile(courseDto.getImage(), UrlConstants.Admin.FILE_COURSE);
				courseDto.setImage(uploadPathService.doUpload(courseDto.getFileImage(), UrlConstants.Admin.FILE_COURSE));
			}
			courseService.update(courseDto);
			return "redirect:" + UrlConstants.Admin.COURSE;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UrlConstants.Admin.COURSE_EDIT;
	}
	
	@RequestMapping(value = UrlConstants.Admin.COURSE_DELETE, method = RequestMethod.GET)
	public String delete(ModelMap modelMap, @RequestParam("id") int id) {
		courseService.delete(id);
		return "redirect:" + UrlConstants.Admin.COURSE;
	}
}
