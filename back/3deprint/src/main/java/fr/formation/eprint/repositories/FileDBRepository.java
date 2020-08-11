package fr.formation.eprint.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.eprint.entities.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
	Optional<FileDB> findByName(String name);
}