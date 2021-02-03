package fr.formation.eprint.services;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import fr.formation.eprint.dtos.BillCreateDto;
import fr.formation.eprint.dtos.OrderAdminViewDto;
import fr.formation.eprint.repositories.BillRepository;

@Service
public class BillServiceImpl implements BillService {

	private final BillRepository billRepo;
	private final OrderService orderService;
	private ModelMapper mapper;

	public BillServiceImpl(BillRepository billrepo, OrderService orderService, ModelMapper mapper) {
		this.billRepo = billrepo;
		this.orderService = orderService;
		this.mapper = mapper;
	}

	public void toto() {
		List<OrderAdminViewDto> dtos = orderService.getAll();
	}

	@Override
	public void create(@Valid BillCreateDto dto) {
		// TODO Auto-generated method stub

	}

}
