package fr.formation.eprint.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.eprint.entities.Bill;

public interface OrderRepository extends JpaRepository<Bill, Long> {

}
