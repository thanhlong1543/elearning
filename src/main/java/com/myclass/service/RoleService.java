package com.myclass.service;

import java.util.List;

import com.myclass.dto.RoleDto;

public interface RoleService {
	List<RoleDto> findAll();
	RoleDto findByID(int id);
	void add(RoleDto roleDto);
	void update(RoleDto roleDto);
	void delete(int id);
}
