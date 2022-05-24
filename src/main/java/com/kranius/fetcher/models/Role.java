package com.kranius.fetcher.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "role")
    private Set<Utilisateur> utilisateur;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, Set<Utilisateur> utilisateur) {
        this.name = name;
        this.utilisateur = utilisateur;
    }

    public Role(long id, String name, Set<Utilisateur> utilisateur) {
        this.id = id;
        this.name = name;
        this.utilisateur = utilisateur;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Utilisateur> getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Set<Utilisateur> utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id && Objects.equals(name, role.name) && Objects.equals(utilisateur, role.utilisateur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, utilisateur);
    }
}
