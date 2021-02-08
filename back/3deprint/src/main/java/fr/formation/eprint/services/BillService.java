package fr.formation.eprint.services;

import fr.formation.eprint.entities.Status;

public interface BillService {

	void create(Long id, Status status);

}
