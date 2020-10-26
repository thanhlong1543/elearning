package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.myclass.dto.VideoDto;
import com.myclass.entity.Video;
import com.myclass.repository.VideoRepository;
import com.myclass.service.VideoService;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {
	@Autowired
	private VideoRepository videoRepository;
	
	@Override
	public List<VideoDto> findAll() {
		List<VideoDto> listVideoDto = new ArrayList<VideoDto>();
		List<Video> listVideo = videoRepository.findAll();
		for (Video video : listVideo) {
			listVideoDto.add(new VideoDto(video.getId(), video.getTitle(), video.getUrl(), video.getTimeCount(), video.getCourseId(),video.getCourse().getTitle()));
		}
		return listVideoDto;
	}

	@Override
	public VideoDto findByID(int id) {
		Video video = videoRepository.findById(id).get();
		VideoDto videoDto = new VideoDto(video.getId(), video.getTitle(), video.getUrl(), video.getTimeCount(), video.getCourseId());
		return videoDto;
	}

	@Override
	public int add(VideoDto videoDto) {
		Video video = new Video(videoDto.getId(), videoDto.getTitle(), videoDto.getUrl(), videoDto.getTimeCount(), videoDto.getCourseId());
		videoRepository.save(video);
		return 1;
	}

	@Override
	public int update(VideoDto videoDto) {
		Video video = videoRepository.findById(videoDto.getId()).get();
		video.setTitle(videoDto.getTitle());
		video.setUrl(videoDto.getUrl());
		video.setTimeCount(videoDto.getTimeCount());
		video.setCourseId(videoDto.getCourseId());
		videoRepository.save(video);
		return 1;
	}

	@Override
	public void delete(int id) {
		videoRepository.deleteById(id);
	}

	@Override
	public List<VideoDto> findAllCourseId(int id) {
		List<VideoDto> listVideoDto = new ArrayList<VideoDto>();
		List<Video> listVideo = videoRepository.findAllCourseId(id);
		for (Video video : listVideo) {
			listVideoDto.add(new VideoDto(video.getId(), video.getTitle(), video.getUrl(), video.getTimeCount(), video.getCourseId(),video.getCourse().getTitle()));
		}
		return listVideoDto;
	}

}
