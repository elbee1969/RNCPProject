package fr.formation.eprint.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.formation.eprint.dtos.BillAdminViewDto;
import fr.formation.eprint.entities.Bill;
import fr.formation.eprint.entities.Status;

public interface BillRepository extends JpaRepository<Bill, Long> {

	@Query(value = "SELECT new fr.formation.eprint.dtos.BillAdminViewDto(b.id, b.customUser.id, b.customUser.firstname, b.customUser.lastname, a.num, a.street, a.town, a.postal, a.country, b.customUser.email,  b.billDate, b.totalPriceHT, b.totalPriceTTC, b.totalWeight"
			+ " ,b.totalItem, b.status) FROM Bill b, Address a WHERE b.customUser.id = :customUserId AND b.status = :status AND b.customUser.id = a.id")
	List<BillAdminViewDto> getAllBillByUserIdAndStatus(@Param("customUserId") Long customUserId,
			@Param("status") Status status);

	@Query(value = "SELECT new fr.formation.eprint.dtos.BillAdminViewDto(b.id, b.customUser.id, b.customUser.firstname, b.customUser.lastname, a.num, a.street, a.town, a.postal, a.country, b.customUser.email,  b.billDate, b.totalPriceHT, b.totalPriceTTC, b.totalWeight"
			+ " ,b.totalItem, b.status) FROM Bill b, Address a WHERE b.status = :status AND b.customUser.id = a.id")
	List<BillAdminViewDto> findAllBills(@Param("status") Status status);
}
