package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dto.CategoryDto;
import com.myclass.entity.Category;
import com.myclass.repository.CategoryRepository;
import com.myclass.service.CategoryService;

@Service
@Transactional(rollbackOn = Exception.class)
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<CategoryDto> findAll() {
		List<CategoryDto> listCategoryDto = new ArrayList<CategoryDto>();
		List<Category> listCategory = categoryRepository.findAll();
		for (Category category : listCategory) {
			listCategoryDto.add(new CategoryDto(category.getId(), category.getTitle(), category.getIcon()));
		}
		return listCategoryDto;
	}

	@Override
	public Category findById(int id) {
		Category category = categoryRepository.findById(id).get();
		return category;
	}

	@Override
	public void add(CategoryDto categorieDto) {
		Category category = new Category(categorieDto.getId(), categorieDto.getTitle(), categorieDto.getIcon());
		categoryRepository.save(category);
	}

	@Override
	public void update(CategoryDto categorieDto) {
		Category category = categoryRepository.findById(categorieDto.getId()).get();
		category.setTitle(categorieDto.getTitle());
		category.setIcon(categorieDto.getIcon());
		categoryRepository.save(category);
	}

	@Override
	public void delete(int id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public List<CategoryDto> findByAll(String category) {
		return null;
	}
	
	

}
