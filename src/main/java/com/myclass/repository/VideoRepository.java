package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {

	@Query(value = "SELECT * FROM videos WHERE course_id = 7;", nativeQuery = true)
	public List<Video> findAllCourseId(int courseId);
}
