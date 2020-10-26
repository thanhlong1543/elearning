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

import com.myclass.dto.UserDto;
import com.myclass.service.UserService;
import com.myclass.util.UrlConstants;

@RestController
@RequestMapping(value = UrlConstants.Admin.API_USER)
public class ApiUserConstroller {
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public Object get() {
		List<UserDto> users =  userService.findAll();
		return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public Object get(@PathVariable("id") int id) {
		try {
			UserDto users =  userService.findByID(id);
			return new ResponseEntity<UserDto>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Không tìm thấy!", HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping("")
	public Object add(@RequestBody UserDto userDto) {
		try {
			userService.add(userDto);
			return new ResponseEntity<String>("Thêm thành công", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Thêm thất bại", HttpStatus.BAD_REQUEST);
		}	
	}
	
	@PutMapping("")
	public Object update(@RequestBody UserDto userDto) {
		try {
			userService.update(userDto);
			return new ResponseEntity<String>("Sửa Thành Công", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Sửa Thất bại", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	public Object delete(@PathVariable("id") int id) {
		try {
			userService.delete(id);
			return new ResponseEntity<String>("Xóa Thành Công", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Xóa Thất bại", HttpStatus.BAD_GATEWAY);
		}
	}
	
}
