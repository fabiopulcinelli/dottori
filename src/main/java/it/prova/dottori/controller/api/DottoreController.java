package it.prova.dottori.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.dottori.dto.DottoreDTO;
import it.prova.dottori.model.Dottore;
import it.prova.dottori.service.DottoreService;

@RestController
@RequestMapping("/api/dottore")
public class DottoreController {

	@Autowired
	private DottoreService registroPrevidenzialeService;

	@GetMapping("/{codice}")
	public DottoreDTO findByCodice(@PathVariable(value = "codice", required = true) String codice) {
		Dottore result = registroPrevidenzialeService.caricaPerCodice(codice);
		//qui andrebbe gestito con un 404 ma per semplicità mandiamo un oggetto vuoto
		return result == null ? new DottoreDTO()
				: DottoreDTO.buildDottoreDTOFromModel(result);
	}

	@GetMapping
	public List<DottoreDTO> getAll() {
		return DottoreDTO.createFilmDTOListFromModelList(registroPrevidenzialeService.listAll());
	}

//TODO	
/* DA FARE!!!!!!!
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DottoreDTO createNew(@RequestBody DottoreDTO input) {
		// ANDREBBE GESTITA CON ADVICE!!!
		// se mi viene inviato un id jpa lo interpreta come update ed a me (producer)
		// non sta bene
		if (input.getId() != null)
			throw new RuntimeException("Non è ammesso fornire un id per la creazione");

		Dottore newEntry = input.buildDottoreModel();
		// andrebbe in un service!!!
		newEntry.setCodicePrevidenziale("REGPREV-" + newEntry.getCodiceFiscale());

		DottoreDTO result = DottoreDTO
				.buildDottoreDTOFromModel(registroPrevidenzialeService.inserisciNuovo(newEntry));
		return result;
	}
*/
}
