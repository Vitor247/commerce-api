package com.vitorcamilodev.commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitorcamilodev.commerce.entities.OrderItem;
import com.vitorcamilodev.commerce.entities.associations.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{

}
