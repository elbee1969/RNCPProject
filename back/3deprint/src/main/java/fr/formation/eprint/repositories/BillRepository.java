package fr.formation.eprint.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.formation.eprint.dtos.BillAdminViewDto;
import fr.formation.eprint.dtos.BillDto;
import fr.formation.eprint.entities.Bill;
import fr.formation.eprint.entities.Status;

public interface BillRepository extends JpaRepository<Bill, Long> {

	@Query("SELECT new fr.formation.eprint.dtos.BillDto(b.id, b.customUser.id, b.billDate, b.totalPriceHT, b.totalPriceTTC, b.totalWeight, b.totalItem, b.status)"
			+ "  FROM Bill b WHERE b.customUser.id = :customUserId AND b.status = :status")
	List<BillDto> getAllBillByUserIdAndStatus(@Param("customUserId") Long customUserId, @Param("status") Status status);

	@Query(value = "SELECT b FROM Bill b WHERE b.status ='I'")
	List<BillAdminViewDto> findAllBills(Sort by);
}
