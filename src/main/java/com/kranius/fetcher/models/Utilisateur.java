package com.kranius.fetcher.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    @Size(min = 8)
    private String password;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Role> role = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
                orphanRemoval = true,
                mappedBy = "utilisateur")
    private List<Produit> produit = new ArrayList<>();

    public void addRole(Role role) {
        this.role.add(role);
    }

    public void removeRole(Role role) {
        this.role.remove(role);
    }

    public void addProduit(Produit produit) {
        this.produit.add(produit);
    }

    public void removeProduit(Produit produit) {
        this.produit.remove(produit);
    }

    public Utilisateur() {
    }

    public Utilisateur(long id, String username, String password, Set<Role> role, List<Produit> produit) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.produit = produit;
    }

    public Utilisateur(String username, String password, Set<Role> role, List<Produit> produit) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.produit = produit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public List<Produit> getProduit() {
        return produit;
    }

    public void setProduit(List<Produit> produit) {
        this.produit = produit;
    }
}
