package com.kranius.fetcher.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private boolean available;

    @NotBlank
    @Column(nullable = false)
    private String reference;

    @Column(nullable = false)
    private ZonedDateTime lastRefreshed;

    @ManyToOne(fetch = FetchType.LAZY)
    private Utilisateur utilisateur;

    public Produit() {
    }

    public Produit(long id, String url, boolean available, String reference, ZonedDateTime lastRefreshed, Utilisateur utilisateur) {
        this.id = id;
        this.url = url;
        this.available = available;
        this.reference = reference;
        this.lastRefreshed = lastRefreshed;
        this.utilisateur = utilisateur;
    }

    public Produit(String url, boolean available, String reference, ZonedDateTime lastRefreshed, Utilisateur utilisateur) {
        this.url = url;
        this.available = available;
        this.reference = reference;
        this.lastRefreshed = lastRefreshed;
        this.utilisateur = utilisateur;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public ZonedDateTime getLastRefreshed() {
        return lastRefreshed;
    }

    public void setLastRefreshed(ZonedDateTime lastRefreshed) {
        this.lastRefreshed = lastRefreshed;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produit produit = (Produit) o;
        return id == produit.id && available == produit.available && Objects.equals(url, produit.url) && Objects.equals(reference, produit.reference) && Objects.equals(lastRefreshed, produit.lastRefreshed) && Objects.equals(utilisateur, produit.utilisateur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, available, reference, lastRefreshed, utilisateur);
    }
}
