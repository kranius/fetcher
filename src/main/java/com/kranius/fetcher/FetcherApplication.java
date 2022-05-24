package com.kranius.fetcher;

import com.kranius.fetcher.models.Produit;
import com.kranius.fetcher.models.Role;
import com.kranius.fetcher.models.Utilisateur;
import com.kranius.fetcher.services.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;

@SpringBootApplication
public class FetcherApplication implements CommandLineRunner {

	@Autowired
	private UtilisateurService utilisateurService;

	private static Logger LOGGER = LoggerFactory.getLogger(FetcherApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(FetcherApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
		Role adminRole = new Role("ADMIN");

		Utilisateur kranius = new Utilisateur("kranius","toto1234",new HashSet<Role>(), new ArrayList<Produit>());
		kranius.addRole(adminRole);

		Utilisateur fetched = utilisateurService.createUtilisateur(kranius);


		Produit p1, p2, p3;

		p1 = new Produit("https://www.thomann.de/fr/rme_fireface_ucx_ii.htm", false, "fireface UCX", ZonedDateTime.now(), fetched);
		fetched.addProduit(p1);

		LOGGER.info("ok");
	}
}
