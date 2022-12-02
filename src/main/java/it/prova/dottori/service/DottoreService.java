package it.prova.dottori.service;

import java.util.List;

import it.prova.dottori.model.Dottore;

public interface DottoreService {

	public Dottore caricaPerCodice(String codice);

	public Dottore inserisciNuovo(Dottore transientInstance);

	public List<Dottore> listAll();

	Dottore aggiorna(Dottore dottoreInstance);
	
	void rimuovi(Dottore dottoreInstance);
}
