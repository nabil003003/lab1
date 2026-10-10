package com.example.model;

import javax.persistence.*;
import java.math.BigDecimal;

// Cette classe représente un produit enregistré en base.
@Entity
@Table(name = "Produits")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produit_id")
    private Long id;

    @Column(name = "produit_nom")
    private String nom;

    @Column(name = "produit_prix")
    private BigDecimal prix;

    public Produit() {
    }
    public Produit(String nom, BigDecimal prix) {
        this.nom = nom;
        this.prix = prix;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }
    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                '}';
    }
    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;
}
