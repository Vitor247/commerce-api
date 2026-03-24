package com.vitorcamilodev.commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vitorcamilodev.commerce.dto.ProductMinDTO;
import com.vitorcamilodev.commerce.entities.PageResponse;
import com.vitorcamilodev.commerce.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping
	public ResponseEntity<PageResponse<ProductMinDTO>> findAll(@RequestParam(defaultValue = "") String name,
			Pageable pageable) {

		Page<ProductMinDTO> page = service.findAll(name, pageable);
		return ResponseEntity.ok(new PageResponse<>(page));
	}

}
