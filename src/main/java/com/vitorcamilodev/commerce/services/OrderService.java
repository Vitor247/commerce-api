package com.vitorcamilodev.commerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitorcamilodev.commerce.dto.OrderDTO;
import com.vitorcamilodev.commerce.dto.OrderItemDTO;
import com.vitorcamilodev.commerce.entities.Order;
import com.vitorcamilodev.commerce.entities.OrderItem;
import com.vitorcamilodev.commerce.entities.Product;
import com.vitorcamilodev.commerce.entities.enums.OrderStatus;
import com.vitorcamilodev.commerce.repositories.OrderItemRepository;
import com.vitorcamilodev.commerce.repositories.OrderRepository;
import com.vitorcamilodev.commerce.repositories.ProductRepository;
import com.vitorcamilodev.commerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthService authService;

	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Order order = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		authService.validateSelfOrAdmin(order.getClient().getId());
		return new OrderDTO(order);
	}
	

	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		Order order = new Order();
		order.setMoment(Instant.now());
		order.setStatus(OrderStatus.WAITING_PAYMENT);
		order.setClient(userService.authenticated());
		
		for(OrderItemDTO itemDto : dto.getItems()) {
			Product product = productRepository.getReferenceById(itemDto.getProductId());
			OrderItem item = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());
			order.getItems().add(item);
		}
		repository.save(order);
		orderItemRepository.saveAll(order.getItems());
		
		return new OrderDTO(order);
	}
	
}
