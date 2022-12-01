package it.prova.dottori.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "dottore")
@Data
public class Dottore {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "codiceDottore")
	private String codiceDottore;
	@Column(name = "codFiscalePazienteAttualmenteInVisita")
	private String codFiscalePazienteAttualmenteInVisita;
	@Column(name = "inVisita")
	private Boolean inVisita;
	@Column(name = "inServizio")
	private String inServizio;
	
	public Dottore() {
	}

	public Dottore(String nome, String cognome, String codiceDottore, String codFiscalePazienteAttualmenteInVisita,
			Boolean inVisita, String inServizio) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDottore = codiceDottore;
		this.codFiscalePazienteAttualmenteInVisita = codFiscalePazienteAttualmenteInVisita;
		this.inVisita = inVisita;
		this.inServizio = inServizio;
	}

	public Dottore(Long id) {
		super();
		this.id = id;
	}
}
