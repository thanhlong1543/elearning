package com.myclass.service;

import com.myclass.dto.TargetDto;

import java.util.List;

public interface TargetService {
    List<TargetDto> findAll();
    TargetDto findByID(int id);
    int add(TargetDto targetDto);
    int update(TargetDto targetDto);
    void delete(int id);
    List<TargetDto> findAllCourseId(int courseId);
}
