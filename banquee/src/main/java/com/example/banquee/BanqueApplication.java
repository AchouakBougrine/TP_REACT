package com.example.banquee;

import com.example.banquee.entities.Compte;
import com.example.banquee.entities.TypeCompte;
import com.example.banquee.repository.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class BanqueApplication {
	public static void main(String[] args) {
		SpringApplication.run(BanqueApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CompteRepository compteRepository) {
		return args -> {
			compteRepository.save(new Compte(null, Math.random() * 9000, LocalDate.now(), TypeCompte.EPARGNE));
			compteRepository.save(new Compte(null, Math.random() * 9000, LocalDate.now(), TypeCompte.COURANT));
			compteRepository.save(new Compte(null, Math.random() * 9000, LocalDate.now(), TypeCompte.EPARGNE));

			compteRepository.findAll().forEach(System.out::println);
		};
	}

}
