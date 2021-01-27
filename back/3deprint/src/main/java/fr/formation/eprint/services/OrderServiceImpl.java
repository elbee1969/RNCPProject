package fr.formation.eprint.services;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import fr.formation.eprint.dtos.OrderCreateDto;
import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.entities.Image;
import fr.formation.eprint.entities.Order;
import fr.formation.eprint.repositories.CustomUserJpaRepository;
import fr.formation.eprint.repositories.ImageRepository;
import fr.formation.eprint.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	private  final OrderRepository orderRepository;
	private final ImageRepository imageRepository;
	private final CustomUserJpaRepository customUserRepository;
	private ModelMapper mapper;
	
	
	public OrderServiceImpl(OrderRepository orderRepository, ImageRepository imageRepository,
			CustomUserJpaRepository customUserRepository, ModelMapper mapper) {
		this.orderRepository = orderRepository;
		this.imageRepository = imageRepository;
		this.customUserRepository = customUserRepository;
		this.mapper = mapper;
	}



	@Override
	public void create(@Valid OrderCreateDto dto) {
		Image image = imageRepository.getOne(dto.getImageId());
		CustomUser user = customUserRepository.getOne(dto.getCustomUserId());
		Order order = new Order();
		mapper.map(dto, order);
		float p = dto.getPrice();
		int q = dto.getQuantity();
		Double totalPrice = (double) (q * p + p/2);
		order.setImage(image);
		order.setCustomUser(user);
		order.setTotalPrice(totalPrice);
		orderRepository.save(order);
	}

}
