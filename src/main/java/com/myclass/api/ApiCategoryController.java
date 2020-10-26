package com.myclass.api;

import com.myclass.dto.CategoryDto;
import com.myclass.service.CategoryService;
import com.myclass.util.UrlConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = UrlConstants.Admin.API_CATEGORY)
public class ApiCategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public Object get(){
        List<CategoryDto> categoryDtos = categoryService.findAll();
        return new ResponseEntity<List<CategoryDto>>(categoryDtos, HttpStatus.OK);
    }
}
