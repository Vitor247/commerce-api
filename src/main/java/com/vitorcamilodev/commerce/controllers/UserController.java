package com.vitorcamilodev.commerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitorcamilodev.commerce.dto.UserDTO;
import com.vitorcamilodev.commerce.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	private UserService service;

	public UserController(UserService service) {
		this.service = service;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
	@GetMapping(value = "/me")
	public ResponseEntity<UserDTO> getMe() {
		UserDTO dto = service.getMe();
		return ResponseEntity.ok(dto);
	}

}
