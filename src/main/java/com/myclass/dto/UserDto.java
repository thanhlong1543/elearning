package com.myclass.dto;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

public class UserDto {
	private int id;
	@NotBlank(message = "Vui lòng nhập Email!")
	@Length(max = 100, message = "Vui lòng không nhập quá 100 ký tự!")
	private String email;
	@NotBlank(message = "Vui lòng nhập fullname!")
	@Length(max = 100, message = "Vui lòng không nhập quá 100 ký tự!")
	private String fullname;
	@Length(max = 100, message = "Vui lòng không nhập quá 100 ký tự!")
	private String password;
	private String confirmPassword;
	private String avatar;
	@Length(max = 20, message = "Vui lòng không nhập quá 20 ký tự!")
	private String phone;
	@Length(max = 255, message = "Vui lòng không nhập quá 255 ký tự!")
	private String address;
	private int role_id;
	private String roleName;
	private MultipartFile file;
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserDto(int id, String email, String fullname, String avatar, String phone, String address, int role_id, String roleName) {
		super();
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.phone = phone;
		this.address = address;
		this.role_id = role_id;
		this.roleName = roleName;
		this.avatar = avatar;
	}
	public UserDto(int id, String email, String fullname, String avatar, String phone, String address,
			int role_id) {
		super();
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.avatar = avatar;
		this.phone = phone;
		this.address = address;
		this.role_id = role_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
}
