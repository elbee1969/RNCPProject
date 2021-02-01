package fr.formation.eprint.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.formation.eprint.dtos.OrderAdminViewDto;
import fr.formation.eprint.dtos.OrderBillDto;
import fr.formation.eprint.entities.Order;
import fr.formation.eprint.entities.Status;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query(value = "SELECT o FROM Order o")
	List<OrderAdminViewDto> findAllOrders(Sort sort);

	@Query("SELECT o from Order o WHERE o.customUser.id = :customUserId AND o.status = :status")
	List<OrderBillDto> getAllOrderByUserId(@Param("customUserId") Long customUserId, @Param("status") Status status);


}
