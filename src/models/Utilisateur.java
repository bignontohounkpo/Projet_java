package models;

import java.util.ArrayList;

public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private ArrayList<Livre> historique;
    private static int idCompteur = 0;

    // Constructeur
    public Utilisateur(String nom, String prenom, String email, ArrayList<Livre> historique) {
        this.id = ++idCompteur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.historique = historique;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Livre> getHistorique() {
        return historique;
    }

    public void ajouterLivreHistorique(Livre livre) {
        historique.add(livre);
    }

    public void emprunterLivre(Livre livre) {
        if (livre.estDisponible()) {
            livre.marquerIndisponible();
            ajouterLivreHistorique(livre);
            System.out.println(prenom + " " + nom + " a emprunté le livre: " + livre.getTitre());
        } else {
            System.out.println("Le livre " + livre.getTitre() + " n'est pas disponible pour l'emprunt.");
        }
    }

    public void retournerLivre(Livre livre) {
        if (historique.contains(livre)) {
            livre.marquerDisponible();
            historique.remove(livre);
            System.out.println(prenom + " " + nom + " a retourné le livre: " + livre.getTitre());
        } else {
            System.out.println("Cet utilisateur n'a pas emprunté le livre: " + livre.getTitre());
        }
    }

    public static int nbrUtil(){
        return idCompteur;
    }

    public void afficherHistorique() {
        System.out.println("Historique des livres empruntés par " + prenom + " " + nom + ":");
        if (historique.isEmpty()) {
            System.out.println("Aucun livre emprunté.");
        } else {
            for (Livre livre : historique) {
                System.out.println("- " + livre.getTitre());
            }
        }
    }
    
}