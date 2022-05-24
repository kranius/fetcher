package com.kranius.fetcher.services;

import com.kranius.fetcher.exceptions.IdentityNotFoundException;
import com.kranius.fetcher.models.Produit;
import com.kranius.fetcher.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {
    private final ProduitRepository produitRepository;

    public Produit createProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public void deleteProduit(long id) throws IdentityNotFoundException {
        Produit produit = produitRepository
                .findById(id)
                .orElseThrow(() -> new IdentityNotFoundException("Could not find Produit with id=" + id));

        produitRepository.deleteById(id);
    }

    @Autowired
    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }
}
