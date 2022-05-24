package com.kranius.fetcher.services;

import com.kranius.fetcher.exceptions.IdentityNotFoundException;
import com.kranius.fetcher.exceptions.UsernameAlreadyTakenException;
import com.kranius.fetcher.models.Produit;
import com.kranius.fetcher.models.Utilisateur;
import com.kranius.fetcher.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public boolean isUsernameTaken(String username) {
        return utilisateurRepository.findByUsername(username).isPresent();
    }

    public Utilisateur createUtilisateur(Utilisateur utilisateur) throws UsernameAlreadyTakenException {
        if (isUsernameTaken(utilisateur.getUsername()))
            throw new UsernameAlreadyTakenException("Username " + utilisateur.getUsername() + " is already taken");
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateur(long id) throws IdentityNotFoundException {
        Utilisateur fetched = utilisateurRepository
                .findById(id)
                .orElseThrow(() -> new IdentityNotFoundException("Could not find Utilisateur id " + id));
        utilisateurRepository.delete(fetched);
    }

    public Utilisateur readUtilisateur(long id) throws IdentityNotFoundException {
        return utilisateurRepository
                .findById(id)
                .orElseThrow(() -> new IdentityNotFoundException("Could not find Utilisateur id : " + id));
    }

    public Utilisateur updatePassword(long id, String password) throws IdentityNotFoundException {
        Utilisateur fetched = utilisateurRepository
                .findById(id)
                .orElseThrow(() -> new IdentityNotFoundException("Could not find Utilisateur id : " + id));

        fetched.setPassword(password);

        return utilisateurRepository.save(fetched);
    }

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<Produit> getAllProduits(String name) throws IdentityNotFoundException {
        Utilisateur fetched = utilisateurRepository
                .findByUsername(name)
                .orElseThrow(() -> new IdentityNotFoundException("Could not find Utilisateur name : " + name));

        return fetched.getProduit();
    }
}
