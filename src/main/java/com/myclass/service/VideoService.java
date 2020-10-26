package com.myclass.service;

import java.util.List;

import com.myclass.dto.VideoDto;

public interface VideoService {
	List<VideoDto> findAll();
	VideoDto findByID(int id);
	int add(VideoDto videoDto);
	int update(VideoDto videoDto);
	void delete(int id);
	List<VideoDto> findAllCourseId(int id);
}
