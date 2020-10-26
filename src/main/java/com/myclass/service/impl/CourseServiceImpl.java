package com.myclass.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dto.CourseDto;
import com.myclass.entity.Course;
import com.myclass.repository.CourseRepository;
import com.myclass.service.CourseService;

@Service
@Transactional(rollbackOn = Exception.class)
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public List<CourseDto> findAll() {
		List<CourseDto> listCourseDto = new ArrayList<CourseDto>();
		List<Course> listCourse = courseRepository.findAll();
		DecimalFormat decimal = new DecimalFormat("###,###,###,###.000");
		for (Course course : listCourse) {
			listCourseDto.add(new CourseDto( course.getId(), course.getTitle(), course.getImage(), course.getLeturesCount(), 
					course.getHourCount(), course.getViewCount(), course.getPrice(), course.getDiscount(), course.getPromotionPrice(), 
					course.getDescription(), course.getContent(), course.getCategoryId(), course.getLastUpdate(), course.getCategory().getTitle()));
		}
		return listCourseDto;
	}

	@Override
	public List<CourseDto> findDiscount() {
		List<Course> courseList = courseRepository.findByDiscount();
		List<CourseDto> courseDtoList = new ArrayList<CourseDto>();
		for(Course course : courseList){
			courseDtoList.add(new CourseDto( course.getId(), course.getTitle(), course.getImage(), course.getLeturesCount(),
					course.getHourCount(), course.getViewCount(), course.getPrice(), course.getDiscount(), course.getPromotionPrice(),
					course.getDescription(), course.getContent(), course.getCategoryId(), course.getLastUpdate(), course.getCategory().getTitle()));
		}
		return courseDtoList;
	}

	@Override
	public CourseDto findById(int id) {
		CourseDto courseDto = new CourseDto();
		Course course = courseRepository.findById(id).get();
		courseDto.setId(course.getId());
		courseDto.setTitle(course.getTitle());
		courseDto.setImage(course.getImage());
		courseDto.setLeturesCount(course.getLeturesCount());
		courseDto.setHourCount(course.getHourCount());
		courseDto.setPrice(course.getPrice());
		courseDto.setDiscount(course.getDiscount());
		courseDto.setPromotionPrice(course.getPromotionPrice());
		courseDto.setDescription(course.getDescription());
		courseDto.setContent(course.getContent());
		courseDto.setCategoryId(course.getCategoryId());
		courseDto.setCategoryTitle(course.getCategory().getTitle());
		return courseDto;
	}

	@Override
	public void add(CourseDto courseDto) {
		Course course = new Course();
		course.setTitle(courseDto.getTitle());
		course.setImage(courseDto.getImage());
		course.setLeturesCount(courseDto.getLeturesCount());
		course.setHourCount(courseDto.getHourCount());
		course.setPrice(courseDto.getPrice());
		course.setDiscount(courseDto.getDiscount());
		if (courseDto.getDiscount() == 0){
			course.setPromotionPrice(courseDto.getPromotionPrice());
		} else {
			course.setPromotionPrice( (double)(courseDto.getPrice() * ((100.0f - courseDto.getDiscount()) / 100.0f)));
		}
		course.setDescription(courseDto.getDescription());
		course.setContent(courseDto.getContent());
		course.setCategoryId(courseDto.getCategoryId());
		courseRepository.save(course);
		
	}

	@Override
	public void update(CourseDto courseDto) {
		Course course = courseRepository.findById(courseDto.getId()).get();
		course.setTitle(courseDto.getTitle());
		course.setImage(courseDto.getImage());
		course.setLeturesCount(courseDto.getLeturesCount());
		course.setHourCount(courseDto.getHourCount());
		course.setPrice(courseDto.getPrice());
		course.setDiscount(courseDto.getDiscount());
		if (courseDto.getDiscount() == 0){
			course.setPromotionPrice(courseDto.getPromotionPrice());
		} else {
			DecimalFormat df = new DecimalFormat("###,###,###,###.000");
			course.setPromotionPrice( (double)(courseDto.getPrice() * ((100.0f - courseDto.getDiscount()) / 100.0f)));
		}
		course.setDescription(courseDto.getDescription());
		course.setContent(courseDto.getContent());
		course.setCategoryId(courseDto.getCategoryId());
		courseRepository.save(course);
	}

	@Override
	public void delete(int id) {
		courseRepository.deleteById(id);
		
	}

	@Override
	public List<CourseDto> findByPopular() {
		List<CourseDto> courseDtoList = new ArrayList<CourseDto>();
		List<Course> courseList = courseRepository.findByPopular();
		for(Course course : courseList){
			courseDtoList.add(new CourseDto( course.getId(), course.getTitle(), course.getImage(), course.getLeturesCount(),
					course.getHourCount(), course.getViewCount(), course.getPrice(), course.getDiscount(), course.getPromotionPrice(),
					course.getDescription(), course.getContent(), course.getCategoryId(), course.getLastUpdate(), course.getCategory().getTitle()));
		}
		return courseDtoList;
	}

	@Override
	public List<CourseDto> findAllCategory(int id) {
		List<CourseDto> courseDtoList = new ArrayList<CourseDto>();
		List<Course> courseList = courseRepository.findByCategory(id);
		for(Course course : courseList){
			courseDtoList.add(new CourseDto( course.getId(), course.getTitle(), course.getImage(), course.getLeturesCount(),
					course.getHourCount(), course.getViewCount(), course.getPrice(), course.getDiscount(), course.getPromotionPrice(),
					course.getDescription(), course.getContent(), course.getCategoryId(), course.getLastUpdate(), course.getCategory().getTitle()));
		}
		return courseDtoList;
	}

	@Override
	public List<CourseDto> findAllNew() {
		List<Course> courseList = courseRepository.findAllNewCourse();
		List<CourseDto> courseDtoList = new ArrayList<CourseDto>();
		for(Course course : courseList){
			courseDtoList.add(new CourseDto( course.getId(), course.getTitle(), course.getImage(), course.getLeturesCount(),
					course.getHourCount(), course.getViewCount(), course.getPrice(), course.getDiscount(), course.getPromotionPrice(),
					course.getDescription(), course.getContent(), course.getCategoryId(), course.getLastUpdate(), course.getCategory().getTitle()));
		}
		return courseDtoList;
	}

	@Override
	public List<CourseDto> findByName(String titleCourse) {
		List<Course> courseList = courseRepository.findByName(titleCourse);
		List<CourseDto> courseDtoList = new ArrayList<CourseDto>();
		for(Course course : courseList){
			System.out.println(course.getId());
			courseDtoList.add(new CourseDto( course.getId(), course.getTitle(), course.getImage(), course.getLeturesCount(),
					course.getHourCount(), course.getViewCount(), course.getPrice(), course.getDiscount(), course.getPromotionPrice(),
					course.getDescription(), course.getContent(), course.getCategoryId(), course.getLastUpdate(), course.getCategory().getTitle()));
		}
		return courseDtoList;
	}

	
}
