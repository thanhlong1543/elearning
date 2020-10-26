package com.myclass.service.impl;

import com.myclass.dto.TargetDto;
import com.myclass.entity.Target;
import com.myclass.repository.TargetRepository;
import com.myclass.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TargetServiceImpl implements TargetService {
    @Autowired
    private TargetRepository targetRepository;
    @Override
    public List<TargetDto> findAll() {
        List<TargetDto> targetDtos = new ArrayList<TargetDto>();
        List<Target> targets = targetRepository.findAll();
        for (Target target : targets) {
            targetDtos.add(new TargetDto(target.getId(), target.getTitle(), target.getCourseId(), target.getCourse().getTitle()));
        }
        return targetDtos;
    }

    @Override
    public TargetDto findByID(int id) {
        Target target = targetRepository.findById(id).get();
        TargetDto targetDto = new TargetDto(target.getId(), target.getTitle(), target.getCourseId());
        return targetDto;
    }

    @Override
    public int add(TargetDto targetDto) {
        Target target = new Target(targetDto.getId(), targetDto.getTitle(), targetDto.getCourseId());
        targetRepository.save(target);
        return 1;
    }

    @Override
    public int update(TargetDto targetDto) {
        Target target = targetRepository.findById(targetDto.getId()).get();
        target.setTitle(targetDto.getTitle());
        target.setCourse_id(targetDto.getCourseId());
        targetRepository.save(target);
        return 1;
    }

    @Override
    public void delete(int id) {
        targetRepository.deleteById(id);
    }

	@Override
	public List<TargetDto> findAllCourseId(int courseId) {
		List<TargetDto> targetDtos = new ArrayList<TargetDto>();
		List<Target> targets = targetRepository.findAllCourseId(courseId);
		for (Target target : targets) {
            targetDtos.add(new TargetDto(target.getId(), target.getTitle(), target.getCourseId(), target.getCourse().getTitle()));
        }
		return targetDtos;
	}
    
    
}
