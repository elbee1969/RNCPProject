package fr.formation.eprint.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import fr.formation.eprint.dtos.OrderAdminViewDto;
import fr.formation.eprint.dtos.OrderDto;
import fr.formation.eprint.entities.Bill;
import fr.formation.eprint.entities.Order;
import fr.formation.eprint.entities.Status;
import fr.formation.eprint.repositories.BillRepository;
import fr.formation.eprint.repositories.CustomUserJpaRepository;

@Service
public class BillServiceImpl implements BillService {

	private final BillRepository billRepo;
	private final OrderService orderService;
	private final CustomUserJpaRepository customUserRepo;
	private ModelMapper mapper;

	public BillServiceImpl(BillRepository billrepo, OrderService orderService, ModelMapper mapper,
			CustomUserJpaRepository customUserRepo) {
		this.billRepo = billrepo;
		this.orderService = orderService;
		this.mapper = mapper;
		this.customUserRepo = customUserRepo;
	}

	public void toto() {
		List<OrderAdminViewDto> dtos = orderService.getAll();
	}

	@Override
	public void create(Long userId, Status status) {

		List<OrderDto> orders = orderService.getAllByIdAndStatus(userId, status);
		List<Order> orders1 = convertList(orders, Order.class);
		Bill bill = new Bill();
		bill.setOrderDate(LocalDate.now());
		bill.setOrders(orders1);
		bill.setCustomUser(customUserRepo.getOne(userId));
		bill.setStatus(status);
		billRepo.save(bill);

	}

	private <S, D> List<D> convertList(List<S> source, Class<D> destination) {
		return source.stream().map(elt -> mapper.map(elt, destination)).collect(Collectors.toList());
	}

}
