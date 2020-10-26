package com.myclass.repository;

import com.myclass.entity.Target;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TargetRepository extends JpaRepository<Target, Integer> {
	
	@Query(value = "SELECT * FROM targets WHERE course_id = ?", nativeQuery = true)
	public List<Target> findAllCourseId(int courseId);
}
