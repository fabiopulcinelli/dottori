package it.prova.dottori;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.dottori.model.Dottore;

import it.prova.dottori.repository.DottoreRepository;

@SpringBootApplication
public class DottoriApplication implements CommandLineRunner {

	@Autowired
	private DottoreRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DottoriApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Popolo DB
		repository.save(new Dottore("Mario","Rossi","DOC1", null, false, true));
		repository.save(new Dottore("Peppe","Bianchi","DOC2", null, false, false));

	}
}
