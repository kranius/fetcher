package com.kranius.fetcher.services;

import com.kranius.fetcher.exceptions.IdentityNotFoundException;
import com.kranius.fetcher.models.Produit;
import com.kranius.fetcher.models.Utilisateur;
import com.kranius.fetcher.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

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

    public Produit updateProduit(long id, Produit produit) throws IdentityNotFoundException {
        Produit fetched = produitRepository
                .findById(id)
                .orElseThrow(() -> new IdentityNotFoundException("Could not find Produit with id=" + id));

        fetched.setPrice(produit.getPrice());
        fetched.setAvailable(produit.isAvailable());
        fetched.setLastRefreshed(ZonedDateTime.now());

        return produitRepository.save(fetched);
    }

    public Produit updateOrInsertProduct(Produit produit) throws IdentityNotFoundException {
        Utilisateur utilisateur = produit.getUtilisateur();
        long productId = utilisateur.hasProduit(produit);

        if (0 <= productId) {
            return updateProduit(productId, produit);
        } else {
            return createProduit(produit);
        }
    }

    @Autowired
    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }
}
