package com.kranius.fetcher.controllers;

import com.kranius.fetcher.exceptions.IdentityNotFoundException;
import com.kranius.fetcher.services.ProduitService;
import com.kranius.fetcher.services.RoleService;
import com.kranius.fetcher.services.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(value = "/app")
public class ApplicationController {

    ProduitService produitService;
    RoleService roleService;
    UtilisateurService utilisateurService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    @GetMapping("/overview")
    public String overview(Model model, Principal principal) {

        LOGGER.error("Received GET on /app/overview, user=" + principal.getName());

        try {
            model.addAttribute("products", utilisateurService.getAllProduits(principal.getName()));
        } catch (IdentityNotFoundException e) {
            model.addAttribute("error", "invalid username");
        }
        return "overview";
    }


    @Autowired
    public ApplicationController(ProduitService produitService, RoleService roleService, UtilisateurService utilisateurService) {
        this.produitService = produitService;
        this.roleService = roleService;
        this.utilisateurService = utilisateurService;
    }
}
