package com.myclass.admin.controller;

import com.myclass.dto.TargetDto;
import com.myclass.service.CourseService;
import com.myclass.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myclass.util.UrlConstants;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "")
public class TargetAdminController {
	@Autowired
	private TargetService targetService;
	@Autowired
	private CourseService courseService;

	@RequestMapping(value = UrlConstants.Admin.TARGET, method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		List<TargetDto> targetDtos = targetService.findAll();
		modelMap.addAttribute("targetDtos", targetDtos);
		return UrlConstants.Admin.TARGET_VIEW;
	}

	@RequestMapping(value = UrlConstants.Admin.TARGET_ADD, method = RequestMethod.GET)
	public String add(ModelMap modelMap) {
		modelMap.addAttribute("targetDto", new TargetDto());
		modelMap.addAttribute("courseDto", courseService.findAll());
		return UrlConstants.Admin.TARGET_ADD;
	}

	@RequestMapping(value = UrlConstants.Admin.TARGET_ADD, method = RequestMethod.POST)
	public String add(ModelMap modelMap, @Valid @ModelAttribute("targetDto") TargetDto targetDto, BindingResult errors){
		if (errors.hasErrors()){
			modelMap.addAttribute("courseDto", courseService.findAll());
			return UrlConstants.Admin.TARGET_ADD;
		}
		try {
			targetService.add(targetDto);
			return "redirect:" + UrlConstants.Admin.TARGET;
		} catch (Exception e){
			e.printStackTrace();
		}
		modelMap.addAttribute("courseDto", courseService.findAll());
		return UrlConstants.Admin.TARGET_ADD;
	}

	@RequestMapping(value = UrlConstants.Admin.TARGET_EDIT, method = RequestMethod.GET)
	public String edit(ModelMap modelMap, @RequestParam("id") int id){
		modelMap.addAttribute("targetDto", targetService.findByID(id));
		modelMap.addAttribute("courseDto", courseService.findAll());
		return UrlConstants.Admin.TARGET_EDIT;
	}

	@RequestMapping(value = UrlConstants.Admin.TARGET_EDIT, method = RequestMethod.POST)
	public String edit(ModelMap modelMap, @Valid @ModelAttribute("targetDto") TargetDto targetDto, BindingResult errors){
		if (errors.hasErrors()){
			modelMap.addAttribute("courseDto", courseService.findAll());
			return UrlConstants.Admin.TARGET_EDIT;
		}
		try {
			targetService.update(targetDto);
			return "redirect:" + UrlConstants.Admin.TARGET;
		} catch (Exception e){
			modelMap.addAttribute("courseDto", courseService.findAll());
			e.printStackTrace();
		}
		return UrlConstants.Admin.TARGET_EDIT;
	}

	@RequestMapping(value = UrlConstants.Admin.TARGET_DELETE, method = RequestMethod.GET)
	public String delete(@RequestParam("id") int id){
		targetService.delete(id);
		return "redirect:" + UrlConstants.Admin.TARGET;
	}
}
