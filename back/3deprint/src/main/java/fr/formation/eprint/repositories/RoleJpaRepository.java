package fr.formation.eprint.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.eprint.entities.Role;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role, Long> {
	Role findByDefaultRole(boolean defaultRole);
}