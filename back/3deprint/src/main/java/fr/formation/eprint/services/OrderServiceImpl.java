package fr.formation.eprint.services;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import fr.formation.eprint.dtos.OrderCreateDto;
import fr.formation.eprint.entities.Order;
import fr.formation.eprint.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	private OrderRepository orderRepository;
	private ModelMapper mapper;
	
	protected OrderServiceImpl( OrderRepository orderRepository, ModelMapper mapper) {
		this.orderRepository = orderRepository;
		this.mapper = mapper;
	}

	@Override
	public Order create(@Valid OrderCreateDto dto) {
		Order order = new Order();
		mapper.map(dto, order);
		return orderRepository.save(order);
	}

}
