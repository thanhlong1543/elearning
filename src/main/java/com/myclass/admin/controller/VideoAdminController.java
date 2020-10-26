package com.myclass.admin.controller;

import com.myclass.dto.VideoDto;
import com.myclass.service.CourseService;
import com.myclass.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myclass.util.UrlConstants;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "")
public class VideoAdminController {
	@Autowired
	private VideoService videoService;
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value = UrlConstants.Admin.VIDEO, method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.addAttribute("videoDto", videoService.findAll());
		return UrlConstants.Admin.VIDEO_VIEW;
	}

	@RequestMapping(value = UrlConstants.Admin.VIDEO_ADD, method = RequestMethod.GET)
	public String add(ModelMap modelMap){
		modelMap.addAttribute("videoDto",new VideoDto());
		modelMap.addAttribute("courseDto", courseService.findAll());
		return UrlConstants.Admin.VIDEO_ADD;
	}

	@RequestMapping(value = UrlConstants.Admin.VIDEO_ADD, method = RequestMethod.POST)
	public String add(ModelMap modelMap, @ModelAttribute("videoDto") VideoDto videoDto, BindingResult errors){
		if (errors.hasErrors()){
			modelMap.addAttribute("courseDto", courseService.findAll());
			return UrlConstants.Admin.VIDEO_ADD;
		}
		try {
			videoService.add(videoDto);
			return "redirect:"+ UrlConstants.Admin.VIDEO;
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelMap.addAttribute("courseDto", courseService.findAll());
		return UrlConstants.Admin.VIDEO_ADD;
	}

	@RequestMapping(value = UrlConstants.Admin.VIDEO_EDIT, method = RequestMethod.GET)
	public String edit(ModelMap modelMap, @RequestParam("id") int id){
        modelMap.addAttribute("videoDto", videoService.findByID(id));
        modelMap.addAttribute("courseDto", courseService.findAll());
	    return UrlConstants.Admin.VIDEO_EDIT;
    }

    @RequestMapping(value = UrlConstants.Admin.VIDEO_EDIT, method = RequestMethod.POST)
    public String edit(ModelMap modelMap,@ModelAttribute("videoDto") VideoDto videoDto, BindingResult errors){
        if (errors.hasErrors()){
            modelMap.addAttribute("courseDto", courseService.findAll());
            return UrlConstants.Admin.VIDEO_EDIT;
        }
        try {
            videoService.update(videoDto);
            return "redirect:" + UrlConstants.Admin.VIDEO;
        } catch (Exception e){
            e.printStackTrace();
        }
        modelMap.addAttribute("courseDto", courseService.findAll());
	    return UrlConstants.Admin.VIDEO_EDIT;
    }

    @RequestMapping(value = UrlConstants.Admin.VIDEO_DELETE, method = RequestMethod.GET)
    public String delete(@RequestParam("id") int id){
        videoService.delete(id);
	    return "redirect:" + UrlConstants.Admin.VIDEO;
    }

}
