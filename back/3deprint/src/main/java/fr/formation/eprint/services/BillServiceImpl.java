package fr.formation.eprint.services;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import fr.formation.eprint.dtos.BillCreateDto;

@Service
public class BillServiceImpl implements BillService {

	@Override
	public void create(@Valid BillCreateDto dto) {
		// TODO Auto-generated method stub
		
	}

}
