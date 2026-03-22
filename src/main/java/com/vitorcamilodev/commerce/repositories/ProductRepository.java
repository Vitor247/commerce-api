package com.vitorcamilodev.commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitorcamilodev.commerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
