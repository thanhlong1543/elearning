package com.myclass.entity;

import org.hibernate.validator.constraints.Length;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "roles")
public class Role {
	@Column(name = "id")
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increase
	private int id;
	@Column(name = "name")
	@NotBlank(message = "Vui lòng nhập tên!")
	@Length(max = 30, message = "Vui lòng không nhập quá 30 ký tự.")
	private String name;
	@Column(name = "description")
	@NotBlank(message = "Vui lòng nhập mô tả!")
	@Length(max = 255, message = "Vui lòng không nhập quá 255 ký tự.")
	private String description;
	
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	private List<User> users;
	
	public Role() {}
	public Role(int id, String name, String description) {
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
