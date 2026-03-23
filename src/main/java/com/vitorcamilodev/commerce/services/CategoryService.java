package com.vitorcamilodev.commerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitorcamilodev.commerce.dto.CategoryDTO;
import com.vitorcamilodev.commerce.entities.Category;
import com.vitorcamilodev.commerce.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;


	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> result = repository.findAll();
		return result.stream().map(x -> new CategoryDTO(x)).toList();
	}

	
}
