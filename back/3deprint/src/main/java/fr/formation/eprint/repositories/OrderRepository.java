package fr.formation.eprint.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.formation.eprint.dtos.OrderAdminViewDto;
import fr.formation.eprint.dtos.OrderDto;
import fr.formation.eprint.entities.Order;
import fr.formation.eprint.entities.Status;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query(value = "SELECT o FROM Order o WHERE o.status ='I'")
	List<OrderAdminViewDto> findAllOrders(Sort sort);

	@Query("SELECT new fr.formation.eprint.dtos.OrderDto(o.id, o.image.id, o.name, o.quantity, o.weight, o.price, o.totalPrice, o.totalWeight, o.timeToPrint, o.status, o.customUser.id)"
			+ "  FROM Order o WHERE o.customUser.id = :customUserId AND o.status = :status")
	List<OrderDto> getAllOrderByUserIdAndStatus(@Param("customUserId") Long customUserId,
			@Param("status") Status status);

	OrderAdminViewDto getById(Long id);

	// Image, String, int, float, float, double, double, String, Status, CustomUser
}
