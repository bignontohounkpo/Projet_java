package models;

public class Livre {
    private int id;
    private String titre;
    private String auteur;
    private int annee;
    private String genre;
    private boolean estDisponible;
    static int count = 0;

    public Livre(String titre, String auteur, int annee, String genre) {
        this.id = ++count;
        this.titre = titre;
        this.auteur = auteur;
        this.annee = annee;
        this.genre = genre;
        this.estDisponible = true;
    }

    public String getDetails() {
        return "ID: " + this.id + "\nTitre: " + this.titre + "\nAuteur: " + this.auteur + "\nAnnée: " + this.annee + "\nGenre: " + this.genre + "\nDisponible: " + this.estDisponible;
    }

    public void marquerIndisponible() {
        this.estDisponible = false;
    }

    public void marquerDisponible() {
        this.estDisponible = true;
    }

    public String getTitre() {
        return this.titre;
    }

    public boolean estDisponible() {
        return this.estDisponible;
    }

    public int getId() {
        return this.id;
    }
    public int nbrLivre() {
        return count;
    }

    public String getGenre() {
        return this.genre;
    }

    public int getAnnee() {
        return this.annee;
    }

    public String getAuteur() {
        return this.auteur;
    }

}