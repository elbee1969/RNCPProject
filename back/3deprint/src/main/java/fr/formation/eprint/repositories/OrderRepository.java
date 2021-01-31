package fr.formation.eprint.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.eprint.dtos.OrderAdminViewDto;
import fr.formation.eprint.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query(value = "SELECT o FROM Order o")
	List<OrderAdminViewDto> findAllOrders(Sort sort);





}
