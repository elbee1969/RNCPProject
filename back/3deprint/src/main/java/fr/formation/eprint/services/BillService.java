package fr.formation.eprint.services;

import java.util.List;

import fr.formation.eprint.dtos.BillAdminViewDto;
import fr.formation.eprint.entities.Status;

public interface BillService {

	void create(Long id, Status status);

	List<BillAdminViewDto> getAllByIdAndStatus(Long id, Status status);

	List<BillAdminViewDto> getAll(Status status);

}
