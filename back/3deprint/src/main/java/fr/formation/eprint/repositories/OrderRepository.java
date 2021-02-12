package fr.formation.eprint.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.formation.eprint.dtos.OrderAdminViewDto;
import fr.formation.eprint.dtos.OrderDto;
import fr.formation.eprint.dtos.OrderViewItemDto;
import fr.formation.eprint.entities.Order;
import fr.formation.eprint.entities.Status;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query(value = "SELECT o FROM Order o WHERE o.status ='I'")
	List<OrderAdminViewDto> findAllOrdersI(Sort sort);

	@Query(value = "SELECT o FROM Order o WHERE o.status ='A'")
	List<OrderAdminViewDto> findAllOrdersA(Sort sort);

	@Query("SELECT new fr.formation.eprint.dtos.OrderDto(o.id, o.image.id, o.name, o.quantity, o.weight, o.price, o.totalPrice, o.totalWeight, o.timeToPrint, o.status, o.customUser.id)"
			+ "  FROM Order o WHERE o.customUser.id = :customUserId AND o.status = :status")
	List<OrderDto> getAllOrderByUserIdAndStatus(@Param("customUserId") Long customUserId,
			@Param("status") Status status);

	OrderAdminViewDto getById(Long id);

	@Query(value = "SELECT o.name, o.price, o.quantity FROM orders AS o INNER JOIN bills_orders AS j on (j.orders_id = o.id and j.bill_id = :id)", nativeQuery = true)
	List<OrderViewItemDto> getByBillId(@Param("id") Long id);

}
