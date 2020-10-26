package com.myclass.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class RoleDto {
	private int id;
	@NotBlank(message = "Vui lòng nhập tên!")
	@Length(max = 30, message = "Vui lòng không nhập quá 30 ký tự.")
	private String name;
	@NotBlank(message = "Vui lòng nhập mô tả!")
	@Length(max = 255, message = "Vui lòng không nhập quá 255 ký tự.")
	private String description;
	public RoleDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RoleDto(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
