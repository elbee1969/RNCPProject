package fr.formation.eprint.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.eprint.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
