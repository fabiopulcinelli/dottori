package it.prova.dottori.dto;

import java.util.List;
import java.util.stream.Collectors;

import it.prova.dottori.model.Dottore;
import lombok.Data;

@Data
public class DottoreDTO {
	private Long id;
	private String nome;
	private String cognome;
	private String codiceDottore;
	private String codFiscalePazienteAttualmenteInVisita;
	private Boolean inVisita;
	private Boolean inServizio;
	
	public DottoreDTO() {
	}
	
	public DottoreDTO(Long id, String nome, String cognome, String codiceDottore,
			String codFiscalePazienteAttualmenteInVisita, Boolean inVisita, Boolean inServizio) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDottore = codiceDottore;
		this.codFiscalePazienteAttualmenteInVisita = codFiscalePazienteAttualmenteInVisita;
		this.inVisita = inVisita;
		this.inServizio = inServizio;
	}
	
	public Dottore buildDottoreModel() {
		return new Dottore(this.id, this.nome, this.cognome, this.codiceDottore, this.codFiscalePazienteAttualmenteInVisita, this.inVisita, this.inServizio);
	}

	public static DottoreDTO buildDottoreDTOFromModel(Dottore input) {
		return new DottoreDTO(input.getId(), input.getNome(), input.getCognome(),
				input.getCodiceDottore(), input.getCodFiscalePazienteAttualmenteInVisita(), input.getInVisita(), input.getInServizio());
	}

	public static List<DottoreDTO> createFilmDTOListFromModelList(
			List<Dottore> modelListInput) {
		return modelListInput.stream().map(inputEntity -> {
			return DottoreDTO.buildDottoreDTOFromModel(inputEntity);
		}).collect(Collectors.toList());
	}
}
