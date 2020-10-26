package com.myclass.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.CourseDto;
import com.myclass.service.CourseService;
import com.myclass.util.UrlConstants;

@RestController
@RequestMapping(value = UrlConstants.Client.API_CLIENT_COURSE)
public class ApiCourseConstroller {

	@Autowired
	private CourseService courseService;
	
	@GetMapping("")
	public Object get() {
		List<CourseDto> courseList =  courseService.findAll();
		return new ResponseEntity<List<CourseDto>>(courseList, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/search")
	@ResponseBody
	public Object search(@RequestParam("searchCourse") String titleCourse) {
		try {
			List<CourseDto> courseDtos = courseService.findByName(titleCourse);
			return new ResponseEntity<List<CourseDto>>(courseDtos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Sửa Thất bại", HttpStatus.BAD_REQUEST);
		}
	}
	
}
