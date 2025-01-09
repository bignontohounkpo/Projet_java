package models;
import java.util.ArrayList;

/**
 * Classe représentant un utilisateur.
 */
public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String role;
    private ArrayList<Livre> historique;
    private static int idCompteur = 0;

    /**
     * Constructeur de la classe Utilisateur.
     * 
     * @param nom    Le nom de l'utilisateur.
     * @param prenom Le prénom de l'utilisateur.
     * @param email  L'adresse email de l'utilisateur.
     * @param role   Le rôle de l'utilisateur ("bibliothecaire" ou "utilisateur").
     */
    public Utilisateur(String nom, String prenom, String email, String role) {
        this.id = ++idCompteur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.role = role.toLowerCase();
        this.historique = new ArrayList<>();
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

    public String getRole() {
        return role;
    }

    public ArrayList<Livre> getHistorique() {
        return historique;
    }

    /**
     * Ajoute un livre à l'historique de l'utilisateur.
     * 
     * @param livre Le livre à ajouter.
     */
    public void ajouterLivreHistorique(Livre livre) {
        historique.add(livre);
    }

    /**
     * Verifie si l'utilisateur est un bibliothecaire.
     * 
     * @return true si l'utilisateur est un bibliothecaire, false sinon.
     */
    public boolean estBibliothecaire() {
        return role.equals("bibliothecaire");
    }

    /**
     * Récupère la représentation en chaîne de l'utilisateur.
     * 
     * @return La chaîne de caractères contenant les informations de l'utilisateur.
     */
    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}