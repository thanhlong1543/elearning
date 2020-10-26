package com.myclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query(value = "SELECT * FROM courses c WHERE c.discount >= 1 limit 4", nativeQuery = true)
    public List<Course> findByDiscount();
    
    @Query(value = "SELECT * FROM courses ORDER BY view_count DESC LIMIT 12", nativeQuery = true)
    public List<Course> findByPopular();
    
    @Query(value = "SELECT * FROM courses WHERE category_id = ?", nativeQuery = true)
    public List<Course> findByCategory(int categoryName);
    
    @Query(value = "SELECT * FROM courses ORDER BY id DESC LIMIT 6",nativeQuery = true)
    public List<Course> findAllNewCourse();
    
    @Query(value = "SELECT * FROM courses WHERE title LIKE %?1%", nativeQuery = true)
    public List<Course> findByName(String courseTitle);
}
