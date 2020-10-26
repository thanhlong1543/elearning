package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dto.RoleDto;
import com.myclass.entity.Role;
import com.myclass.repository.RoleRepository;
import com.myclass.service.RoleService;

@Service
@Transactional(rollbackOn = Exception.class)
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<RoleDto> findAll() {
		List<RoleDto> listRoleDto = new ArrayList<RoleDto>();
		List<Role> listRole = roleRepository.findAll();
		for (Role role : listRole) {
			listRoleDto.add(new RoleDto(role.getId(), role.getName(), role.getDescription()));
		}
		return listRoleDto;
	}

	@Override
	public RoleDto findByID(int id) {
		Role role = roleRepository.findById(id).get();
		RoleDto roleDto = new RoleDto(role.getId(), role.getName(), role.getDescription());
		return roleDto;
	}

	@Override
	public void add(RoleDto roleDto) {
		Role role = new Role(roleDto.getId(), roleDto.getName(), roleDto.getDescription());
		roleRepository.save(role);		
	}

	@Override
	public void update(RoleDto roleDto) {
		Role role = roleRepository.findById(roleDto.getId()).get();
		role.setName(roleDto.getName());
		role.setDescription(roleDto.getDescription());
		roleRepository.save(role);
	}

	@Override
	public void delete(int id) {
		roleRepository.deleteById(id);
	}
	
}
