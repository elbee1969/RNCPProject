package fr.formation.eprint.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fr.formation.eprint.dtos.BillAdminViewDto;
import fr.formation.eprint.dtos.BillDto;
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

	/*
	 * public void toto() { List<OrderAdminViewDto> dtos = orderService.getAll(); }
	 */
	@Override
	public List<BillDto> getAllByIdAndStatus(Long id, Status status) {
		return billRepo.getAllBillByUserIdAndStatus(id, status);
	}

	@Transactional
	@Override
	public void create(Long userId, Status status) {
		double HT = 0;
		final double TTC;
		double Weight = 0;
		double TVA = 1.2;
		int i, nbItem = 0;

		List<OrderDto> orders = orderService.getAllByUserIdAndStatus(userId, status);
		nbItem = orders.size();
		System.out.println("nbr order" + nbItem);
		for (i = 0; i < nbItem; i++) {
			HT += orders.get(i).getTotalPrice();
			Weight += orders.get(i).getTotalWeight();
		}
		TTC = HT * TVA;
		Bill bill = new Bill();
		List<Order> newOrders = convertList(orders, Order.class);
		bill.setBillDate(LocalDate.now());
		bill.setTotalPriceHT(HT);
		bill.setTotalPriceTTC(TTC);
		bill.setTotalWeight(Weight);
		bill.setOrders(newOrders);
		bill.setTotalItem(nbItem);
		bill.setCustomUser(customUserRepo.getOne(userId));
		bill.setStatus(Status.I);
		billRepo.save(bill);
		Long billId = bill.getId();
		Status toto = bill.getStatus();
		orderService.updateOrderStatusOver(billId);

	}

	private <S, D> List<D> convertList(List<S> source, Class<D> destination) {
		return source.stream().map(elt -> mapper.map(elt, destination)).collect(Collectors.toList());
	}

	@Override
	public List<BillAdminViewDto> getAll() {

		return billRepo.findAllBills(Sort.by("id"));
	}

}
