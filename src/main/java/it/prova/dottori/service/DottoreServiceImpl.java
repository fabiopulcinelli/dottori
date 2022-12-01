package it.prova.dottori.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.dottori.model.Dottore;
import it.prova.dottori.repository.DottoreRepository;

@Service
public class DottoreServiceImpl implements DottoreService {

	@Autowired
	private DottoreRepository repository;

	@Override
	public Dottore caricaPerCodice(String codice) {
		return repository.findByCodiceDottore(codice);
	}

	@Override
	public Dottore inserisciNuovo(Dottore transientInstance) {
		return repository.save(transientInstance);
	}

	@Override
	public List<Dottore> listAll() {
		return (List<Dottore>) repository.findAll();
	}

}
