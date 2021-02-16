package fr.formation.eprint.services;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fr.formation.eprint.dtos.OrderAdminViewDto;
import fr.formation.eprint.dtos.OrderCreateDto;
import fr.formation.eprint.dtos.OrderDto;
import fr.formation.eprint.dtos.OrderPatchDto;
import fr.formation.eprint.dtos.OrderViewItemDto;
import fr.formation.eprint.entities.CustomUser;
import fr.formation.eprint.entities.Image;
import fr.formation.eprint.entities.Order;
import fr.formation.eprint.entities.Status;
import fr.formation.eprint.exception.ResourceNotFoundException;
import fr.formation.eprint.repositories.CustomUserJpaRepository;
import fr.formation.eprint.repositories.ImageRepository;
import fr.formation.eprint.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
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
		p = p + (p / 2);
		int q = dto.getQuantity();
		float w = dto.getWeight();
		Double totalPrice = (double) (q * p);
		Double totalWeight = (double) (q * w);
		order.setImage(image);
		order.setCustomUser(user);
		order.setTotalPrice(totalPrice);
		order.setTotalWeight(totalWeight);
		orderRepository.save(order);
	}

	@Override
	public List<OrderAdminViewDto> getAll(Status status) {
		return orderRepository.findAllOrders(Sort.by("customUser.id"), status);
	}

	@Override
	public List<OrderDto> getAllByUserIdAndStatus(Long id, Status status) {
		return orderRepository.getAllOrderByUserIdAndStatus(id, status);
	}

	@Override
	public void update(Long id, @Valid OrderPatchDto dto) {
		Order order = orderRepository.findById(id).get();
		mapper.map(dto, order);
		orderRepository.save(order);

	}

	@Override
	public OrderAdminViewDto getOne(Long id) {
		// TODO Auto-generated method stub
		return orderRepository.getById(id);
	}

	@Override
	public List<OrderViewItemDto> getAllById(Long id) {
		return orderRepository.getByBillId(id);
	}

	@Override
	public void deleteOne(Long id) {
		orderRepository.deleteById(id);

	}

	@Transactional
	@Override
	public void updateOrderStatusOver(Long billId) {
		orderRepository.updateOrderStatusOver(billId);

	}

	@Transactional
	@Override
	public void updateOrder(Long id, @Valid OrderPatchDto dto) {
		Order order = orderRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
		mapper.map(dto, order);
		orderRepository.save(order);

	}

}
