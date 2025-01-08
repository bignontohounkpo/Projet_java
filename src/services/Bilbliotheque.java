package services;
import models.Livre;
import java.util.ArrayList;
import java.util.HashMap;

public class Bilbliotheque {
    private String nom;
    private HashMap <Integer, Livre> livres;
    private HashMap <Integer, Utilisateur> utilisateurs;
    private HashMap <Utilisateur, ArrayList<Livre>> historique;

    public Bilbliotheque(String nom) {
        this.nom = nom;
        this.livres = new HashMap <Integer, Livre>();
        this.utilisteurs = new HashMap <Integer, Utilisateur>();
        this.historique = new HashMap <Utilisateur, ArrayList<Livre>>();
    }

    public void ajouterLivre(Livre livre) {
        this.livres.put(livre.getId(), livre);
    }

    public void supprimerLivre(int id) {
        this.livres.remove(id);
    }

    public Livre rechercherLivre(String titre) {
        for (Livre livre : this.livres.values()) {
            if (livre.getTitre().equals(titre)) {
                return livre;
            }
        }
        return null;
    }

    public void emprunterLivre(Utilisateur utilisateur, Livre livre) {
        if (livre.estDisponible()) {
            livre.marquerIndisponible();
            if (this.historique.containsKey(utilisateur)) {
                this.historique.get(utilisateur).add(livre);
            } else {
                ArrayList<Livre> livres = new ArrayList<Livre>();
                livres.add(livre);
                this.historique.put(utilisateur, livres);
            }
        }
    }

    public void retournerLivre(Utilisateur utilisateur, Livre livre) {
        if (this.historique.containsKey(utilisateur)) {
            this.historique.get(utilisateur).remove(livre);
            livre.marquerDisponible();
        }
    }

    public void afficStatistiques() {
        System.out.println("Nombre de livres: " + this.livres.size());
        System.out.println("Nombre d'utilisateurs: " + this.utilisateurs.size());
        System.out.println("Nombre d'emprunts: " + this.historique.size());
    }
}