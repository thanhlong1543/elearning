package com.myclass.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.RoleDto;
import com.myclass.service.RoleService;
import com.myclass.util.UrlConstants;

@RestController
@RequestMapping(value = UrlConstants.Admin.API_ROLE)
public class ApiRoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("")
	public Object get() {
		List<RoleDto> roles =  roleService.findAll();
		return new ResponseEntity<List<RoleDto>>(roles, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public Object get(@PathVariable("id") int id) {
		try {
			RoleDto roles =  roleService.findByID(id);
			return new ResponseEntity<RoleDto>(roles, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Không tìm thấy!", HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping("")
	public Object add(@RequestBody RoleDto roleDto) {
		try {
			roleService.add(roleDto);
			return new ResponseEntity<String>("Thêm thành công", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Thêm thất bại", HttpStatus.BAD_REQUEST);
		}	
	}
	
	@PutMapping("")
	public Object update(@RequestBody RoleDto roleDto) {
		try {
			roleService.update(roleDto);
			return new ResponseEntity<String>("Sửa Thành Công", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Sửa Thất bại", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	public Object delete(@PathVariable("id") int id) {
		try {
			roleService.delete(id);
			return new ResponseEntity<String>("Xóa Thành Công", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Xóa Thất bại", HttpStatus.BAD_GATEWAY);
		}
	}
}
