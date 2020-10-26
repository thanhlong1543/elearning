package com.myclass.dto;

public class LoginDto {
	private String email;
	private String pasword;
	
	public LoginDto() {}

	public LoginDto(String email, String pasword) {
		super();
		this.email = email;
		this.pasword = pasword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasword() {
		return pasword;
	}

	public void setPasword(String pasword) {
		this.pasword = pasword;
	}
	
	
}
