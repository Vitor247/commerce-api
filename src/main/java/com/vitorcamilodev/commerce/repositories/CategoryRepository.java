package com.vitorcamilodev.commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitorcamilodev.commerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
