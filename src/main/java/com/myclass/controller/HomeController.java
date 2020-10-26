package com.myclass.controller;

import com.myclass.service.CategoryService;
import com.myclass.service.CourseService;
import com.myclass.util.UrlConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "")
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CourseService courseService;
    
    @RequestMapping(value = {"/",UrlConstants.Client.HOME_URL}, method = RequestMethod.GET)
    public String Home(ModelMap modelMap){
        modelMap.addAttribute("categoryDto", categoryService.findAll());
        modelMap.addAttribute("courseDtoSale", courseService.findDiscount());
        modelMap.addAttribute("courseDtoPopular", courseService.findByPopular());
        modelMap.addAttribute("courseDtoNew", courseService.findAllNew());
        return UrlConstants.Client.HOME;
    }
    
    
}
