package com.vitorcamilodev.commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitorcamilodev.commerce.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
