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
    @Column(nullable = false, unique = true)
    private String url;

    @Column(nullable = false)
    private boolean available;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String reference;

    @Column(nullable = false)
    private ZonedDateTime lastRefreshed;

    @ManyToOne(fetch = FetchType.LAZY)
    private Utilisateur utilisateur;

    public Produit() {
    }

    public Produit(long id, String url, boolean available, String reference, ZonedDateTime lastRefreshed, String price, Utilisateur utilisateur) {
        this.id = id;
        this.url = url;
        this.price = price;
        this.available = available;
        this.reference = reference;
        this.lastRefreshed = lastRefreshed;
        this.utilisateur = utilisateur;
    }

    public Produit(String url, boolean available, String reference, ZonedDateTime lastRefreshed, String price, Utilisateur utilisateur) {
        this.url = url;
        this.price = price;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produit produit = (Produit) o;
        return id == produit.id && available == produit.available && url.equals(produit.url) && price.equals(produit.price) && name.equals(produit.name) && reference.equals(produit.reference) && lastRefreshed.equals(produit.lastRefreshed) && utilisateur.equals(produit.utilisateur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, name, available, reference, price, lastRefreshed, utilisateur);
    }
}
