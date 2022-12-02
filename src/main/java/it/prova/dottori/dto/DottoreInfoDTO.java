package it.prova.dottori.dto;

import lombok.Data;

@Data
public class DottoreInfoDTO {
	private String codiceDottore;
	private String codFiscalePazienteAttualmenteInVisita;
	
	public DottoreInfoDTO() {
	}
	
	public DottoreInfoDTO(String codiceDottore, String codFiscalePazienteAttualmenteInVisita) {
		super();
		this.codiceDottore = codiceDottore;
		this.codFiscalePazienteAttualmenteInVisita = codFiscalePazienteAttualmenteInVisita;
	}
}
