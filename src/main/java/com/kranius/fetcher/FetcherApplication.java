package com.kranius.fetcher;

import com.kranius.fetcher.crawlers.CrawlerPicker;
import com.kranius.fetcher.crawlers.WebCrawlerInterface;
import com.kranius.fetcher.models.Produit;
import com.kranius.fetcher.models.Role;
import com.kranius.fetcher.models.Utilisateur;
import com.kranius.fetcher.services.ProduitService;
import com.kranius.fetcher.services.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;

@SpringBootApplication
public class FetcherApplication implements CommandLineRunner {

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private ProduitService produitService;

	private static Logger LOGGER = LoggerFactory.getLogger(FetcherApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(FetcherApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
		Role adminRole = new Role("ADMIN");

		Utilisateur kranius = new Utilisateur("kranius","toto1234", new HashSet<Role>(), new ArrayList<Produit>());
		kranius.addRole(adminRole);

		Utilisateur fetchedUser = utilisateurService.createUtilisateur(kranius);

		URL url = new URL("https://www.thomann.de/fr/rme_fireface_ucx_ii.htm");

		// choose proper crawler
		WebCrawlerInterface crawler = CrawlerPicker.pickCrawlerFromUrl(url);

		// crawl webpage
		Produit p = crawler.makeProduct(url, fetchedUser);

		if (p == null) {
			//deal with error in UI
			LOGGER.info("enter a correct product url");
			return;
		}

		// if user already has the product then update else insert
		Produit persistedProduct = produitService.updateOrInsertProduct(p);

		System.out.println("ok");
	}
}
