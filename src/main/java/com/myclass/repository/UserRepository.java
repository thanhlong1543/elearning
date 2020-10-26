package com.myclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myclass.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(value = "SELECT * FROM users WHERE email = ?", nativeQuery = true)
	public User findByEmail(String email);
}
