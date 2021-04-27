package fr.formation.eprint.services;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import fr.formation.eprint.dtos.BillAdminViewDto;
import fr.formation.eprint.dtos.OrderDto;
import fr.formation.eprint.entities.Bill;
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
	 * 
	 */
	@Override
	public List<BillAdminViewDto> getAllByIdAndStatus(Long id, Status status) {
		return billRepo.getAllBillByUserIdAndStatus(id, status);
	}

	@Transactional
	@Override
	public void create(Long userId, Status status) {
		double HT = 0;
		final double TTC;
		double Weight = 0;
		double TVA = 1.2;
		int i, nbItem, nbTotalItem = 0;

		List<OrderDto> orders = orderService.getAllByUserIdAndStatus(userId, status);
		nbItem = orders.size();
		System.out.println("nbr order" + nbItem);
		for (i = 0; i < nbItem; i++) {
			nbTotalItem += orders.get(i).getQuantity();
			HT += orders.get(i).getTotalPrice();
			Weight += orders.get(i).getTotalWeight();
		}
		TTC = HT * TVA;
		Bill bill = new Bill();
		bill.setBillDate(LocalDate.now());
		bill.setTotalPriceHT(HT);
		bill.setTotalPriceTTC(TTC);
		bill.setTotalWeight(Weight);
		bill.setTotalItem(nbTotalItem);
		bill.setCustomUser(customUserRepo.getOne(userId));
		bill.setStatus(Status.I);
		billRepo.save(bill);

	}

	@Override
	public List<BillAdminViewDto> getAll(Status status) {
		List<BillAdminViewDto> lists = billRepo.findAllBills(status);
		return lists;
	}

}
