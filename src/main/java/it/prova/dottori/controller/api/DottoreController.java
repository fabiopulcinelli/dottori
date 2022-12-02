package it.prova.dottori.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.dottori.dto.DottoreDTO;
import it.prova.dottori.dto.DottoreInfoDTO;
import it.prova.dottori.model.Dottore;
import it.prova.dottori.service.DottoreService;
import it.prova.triage.web.api.exception.DottoreImpegnatoException;
import it.prova.triage.web.api.exception.DottoreNotFoundException;
import it.prova.triage.web.api.exception.DottoreNotInServizioException;
import it.prova.triage.web.api.exception.IdNotNullForInsertException;

@RestController
@RequestMapping("/api/dottore")
public class DottoreController {

	@Autowired
	private DottoreService dottoreService;

	@GetMapping("/{codice}")
	public DottoreDTO findByCodice(@PathVariable(value = "codice", required = true) String codice) {
		Dottore result = dottoreService.caricaPerCodice(codice);
		//qui andrebbe gestito con un 404 ma per semplicità mandiamo un oggetto vuoto
		return result == null ? new DottoreDTO()
				: DottoreDTO.buildDottoreDTOFromModel(result);
	}
	
	@GetMapping("verifica/{codice}")
	public DottoreDTO verifica(@PathVariable(value = "codice",required = true) String codice) {
		Dottore result = dottoreService.caricaPerCodice(codice);

		if (result == null)
			throw new DottoreNotFoundException("Dottore not found");

		if(!result.getInServizio())
			throw new DottoreNotInServizioException("Il dottore selezionato non e' attualmente in servizio");
		
		if(result.getInVisita())
			throw new DottoreImpegnatoException("Il dottore selezionato e' attualmente impegnato con un altro paziente");
		
		return result == null ? new DottoreDTO()
				: DottoreDTO.buildDottoreDTOFromModel(result);
	}

	@GetMapping
	public List<DottoreDTO> getAll() {
		return DottoreDTO.createFilmDTOListFromModelList(dottoreService.listAll());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DottoreDTO createNew(@RequestBody DottoreDTO input) {
		if (input.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

		Dottore newEntry = input.buildDottoreModel();

		DottoreDTO result = DottoreDTO
				.buildDottoreDTOFromModel(dottoreService.inserisciNuovo(newEntry));
		return result;
	}
	
	@PutMapping("/{codice}")
	public DottoreDTO update(@Valid @RequestBody DottoreDTO dottoreInput, @PathVariable(required = true) String codice) {
		Dottore dottore = dottoreService.caricaPerCodice(codice);

		if (dottore == null)
			throw new DottoreNotFoundException("Dottore not found");

		dottoreInput.setId(dottoreService.caricaPerCodice(codice).getId());
		Dottore dottoreAggiornato = dottoreService.aggiorna(dottoreInput.buildDottoreModel());
		return DottoreDTO.buildDottoreDTOFromModel(dottoreAggiornato);
	}
	
	@DeleteMapping("/{codice}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(required = true) String codice) {
		dottoreService.rimuovi(dottoreService.caricaPerCodice(codice));
	}
	
	@PutMapping("impostaInVisita")
	public void impostaInVisita(@RequestBody DottoreInfoDTO doc) {
		DottoreInfoDTO dottore = new DottoreInfoDTO(doc.getCodiceDottore(),doc.getCodFiscalePazienteAttualmenteInVisita());
		
		Dottore dottoreAggiornato = dottoreService.caricaPerCodice(dottore.getCodiceDottore());
		dottoreAggiornato.setCodFiscalePazienteAttualmenteInVisita(dottore.getCodFiscalePazienteAttualmenteInVisita());
		
		dottoreAggiornato.setInVisita(true);
		
		dottoreAggiornato = dottoreService.aggiorna(dottoreAggiornato);
	}
}
