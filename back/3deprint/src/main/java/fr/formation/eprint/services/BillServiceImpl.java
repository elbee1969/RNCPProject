package fr.formation.eprint.services;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import fr.formation.eprint.dtos.BillCreateDto;
import fr.formation.eprint.dtos.OrderCreateDto;
import fr.formation.eprint.entities.Bill;
import fr.formation.eprint.entities.Order;
import fr.formation.eprint.repositories.BillRepository;
import fr.formation.eprint.repositories.OrderRepository;

@Service
public class BillServiceImpl implements BillService {

	private final BillRepository billRepo;
	private final OrderRepository orderRepo;
	private ModelMapper mapper;
	
	public BillServiceImpl(BillRepository billrepo, OrderRepository orderRepo, ModelMapper mapper) {
		this.billRepo = billrepo;
		this.orderRepo = orderRepo;
		this.mapper = mapper;
	}
	
	@Override
	public void create(Long id, @Valid BillCreateDto dto) {
		

		Bill bill = new Bill();

		List<Order> orders = new ArrayList<>();
		
		for (Order orderDto : dto.getOrders()) {
			List<Order> order = orderRepo.findAll();
			mapper.map(orderDto, order);
		}
		mapper.map(dto, bill);
		
	}

}
