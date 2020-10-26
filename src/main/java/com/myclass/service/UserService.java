package com.myclass.service;

import java.util.List;


import com.myclass.dto.UserDto;

public interface UserService {
	List<UserDto> findAll();
	UserDto findByID(int id);
	boolean findByEmail(UserDto userDto);
	int add(UserDto userDto);
	int update(UserDto userDto);
	void delete(int id);
	boolean resgiter(UserDto userDto);
}
