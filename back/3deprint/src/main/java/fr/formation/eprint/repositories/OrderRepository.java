package fr.formation.eprint.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.eprint.entities.Bill;
import fr.formation.eprint.entities.Order;

public interface OrderRepository extends JpaRepository<Bill, Long> {

	Order save(Order order);

}
