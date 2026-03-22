package com.vitorcamilodev.commerce.dto;

import java.time.LocalDate;

import com.vitorcamilodev.commerce.entities.User;

public class UserDTO {


	private Long id;
	private String name;
	private String email;
	private String phone;
	private LocalDate birthDate;

	public UserDTO(User user) {
		id = user.getId();
		name = user.getName();
		email = user.getEmail();
		phone = user.getPhone();
		birthDate = user.getBirthDate();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}	
	
}
