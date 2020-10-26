package com.myclass.service;

import java.util.List;

import com.myclass.dto.CategoryDto;
import com.myclass.entity.Category;

public interface CategoryService {
	List<CategoryDto> findAll();
	Category findById(int id);
	void add(CategoryDto categorieDto);
	void update(CategoryDto categorieDto);
	void delete(int id);
	
	List<CategoryDto> findByAll(String category);
}
