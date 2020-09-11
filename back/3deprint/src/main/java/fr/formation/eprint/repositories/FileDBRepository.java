package fr.formation.eprint.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.eprint.entities.FileDB;


public interface FileDBRepository extends JpaRepository<FileDB, String> {
	Optional<FileDB> findByname(String name);

//	FileDB findById(Long id);

}