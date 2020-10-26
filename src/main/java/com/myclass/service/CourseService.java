package com.myclass.service;

import java.util.List;

import com.myclass.dto.CourseDto;

public interface CourseService {
	List<CourseDto> findAll();
	List<CourseDto> findDiscount();
	CourseDto findById(int id);
	void add(CourseDto courseDto);
	void update(CourseDto courseDto);
	void delete(int id);
	List<CourseDto> findByPopular();
	List<CourseDto> findAllCategory(int id);
	List<CourseDto> findAllNew();
	List<CourseDto> findByName(String titleCourse);
}
