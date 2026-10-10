package com.example.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    public void setId(Long id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public Long getId() {
        return id;
    }
    @OneToMany(mappedBy ="categorie")
    List<Produit>produit;

}
