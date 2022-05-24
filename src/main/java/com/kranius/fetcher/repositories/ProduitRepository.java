package com.kranius.fetcher.repositories;

import com.kranius.fetcher.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    Optional<Produit> findByUrl(String url);
    List<Produit> findByAvailable(Boolean status);
    Optional<Produit> findByReference(String reference);
}
