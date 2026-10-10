package com.example;
import org.h2.tools.Server;

import com.example.model.Produit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            Server.createWebServer("-web", "-webPort", "8082").start();
            System.out.println("Console H2 disponible sur : http://localhost:8082");
        } catch (Exception e) {
            System.out.println("Erreur lors du démarrage de la console H2");
            e.printStackTrace();
        }
        // Création de l'EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-demo");

        // Insertion de produits
        insererProduits(emf);

        // Lecture des produits
        lireProduits(emf);

        // Fermeture de l'EntityManagerFactory
        emf.close();
    }

    private static void insererProduits(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Création de quelques produits
            Produit p1 = new Produit("pc", new BigDecimal("9599.99"));
            Produit p2 = new Produit("tel", new BigDecimal("2499.99"));
            Produit p3 = new Produit("Tab", new BigDecimal("1899.99"));
            Produit p4 = new Produit("clima", new BigDecimal("1899.99"));
            Produit p5 = new Produit("chargeur", new BigDecimal("1899.99"));

            // Persistance des produits
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.persist(p4);
            em.persist(p5);

            em.getTransaction().commit();
            System.out.println("Produits insérés avec succès !");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private static void lireProduits(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        try {
            // Requête JPQL pour récupérer tous les produits
            List<Produit> produits = em.createQuery("SELECT p FROM Produit p", Produit.class)
                    .getResultList();

            System.out.println("\nListe des produits :");
            for (Produit produit : produits) {
                System.out.println(produit);
            }

            // Recherche d'un produit par ID
            System.out.println("\nRecherche du produit avec ID=2 :");
            Produit produit = em.find(Produit.class, 2L);
            if (produit != null) {
                System.out.println(produit);
            } else {
                System.out.println("pas de produit");
            }
        } finally {
            em.close();
        }
    }

    private static void updaterProduits(long id, EntityManager em, BigDecimal newPrix) {
        try {
            em.getTransaction().begin();
            Produit produit = em.find(Produit.class, id);
            if (produit == null) {
                throw new IllegalArgumentException("Produit introuvable : " + id);
            }
            produit.setPrix(newPrix);
            em.getTransaction().commit();
            System.out.println("Prix du produit mis à jour !");
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }
    private static void DeleteProduits(long id, EntityManager em) {

        try {
            em.getTransaction().begin();



            Produit produit = em.find(Produit.class, id);



            em.remove(produit);



            em.getTransaction().commit();
            System.out.println("Produits a ete supprimer !");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
public static void plagePrix(EntityManager em,long max,long min) {
        
}

}
