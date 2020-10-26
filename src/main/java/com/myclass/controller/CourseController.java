package com.myclass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclass.service.CategoryService;
import com.myclass.service.CourseService;
import com.myclass.service.TargetService;
import com.myclass.service.VideoService;
import com.myclass.util.UrlConstants;

@Controller
@RequestMapping(value = "")
public class CourseController {
	@Autowired
    private CategoryService categoryService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TargetService targetService;
    @Autowired
    private VideoService videoService;
    
    @RequestMapping(value = UrlConstants.Client.COURSE_LIST, method = RequestMethod.GET)
    public String list(@RequestParam("id") int id, ModelMap modelMap) {
    	 modelMap.addAttribute("categoryDto", categoryService.findAll());
    	 modelMap.addAttribute("categoryTitle", categoryService.findById(id));
    	 modelMap.addAttribute("courseDto",courseService.findAllCategory(id));
    	
    	return UrlConstants.Client.COURSE_LIST_VIEW;
    }
    
    @RequestMapping(value = UrlConstants.Client.COURSE_DETAIL, method = RequestMethod.GET)
    public String detail(@RequestParam("id") int id, ModelMap modelMap) {
    	
    	modelMap.addAttribute("categoryDto", categoryService.findAll());
    	modelMap.addAttribute("courseDto", courseService.findById(id));
    	modelMap.addAttribute("targetDto", targetService.findAllCourseId(id));
    	modelMap.addAttribute("videoDto", videoService.findAllCourseId(id));
    	return UrlConstants.Client.COURSE_DETAIL_VIEW;
    }
    
    
    @RequestMapping(value = UrlConstants.Client.COURSE_SEARCH, method = RequestMethod.GET)
    public String search(ModelMap modelMap) {
    	modelMap.addAttribute("categoryDto", categoryService.findAll());
    	return UrlConstants.Client.COURSE_SEARCH_VIEW;
    }
}
