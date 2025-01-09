package models;

import java.util.ArrayList;

/**
 * Classe représentant un livre.
 */
public class Livre {
    private int id;
    private String titre;
    private String auteur;
    private int annee;
    private String genre;
    private boolean estDisponible;
    private ArrayList<Avis> avis;
    private static int count = 0;

    /**
     * Constructeur de la classe Livre.
     * 
     * @param titre  le titre du livre
     * @param auteur l'auteur du livre
     * @param annee  l'annee de publication du livre
     * @param genre  le genre du livre
     */
    public Livre(String titre, String auteur, int annee, String genre) {
        this.id = ++count;
        this.titre = titre;
        this.auteur = auteur;
        this.annee = annee;
        this.genre = genre;
        this.estDisponible = true;
        this.avis = new ArrayList<>();
    }

    /**
     * Récupère les détails du livre.
     * 
     * @return une chaîne de caractères contenant les détails du livre.
     */
    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("ID: ").append(this.id)
                .append("\nTitre: ").append(this.titre)
                .append("\nAuteur: ").append(this.auteur)
                .append("\nAnnée: ").append(this.annee)
                .append("\nGenre: ").append(this.genre)
                .append("\nDisponible: ").append(this.estDisponible);

        if (!avis.isEmpty()) {
            double moyenneNotes = getMoyenneNotes();
            details.append("\nNote moyenne: ").append(String.format("%.1f", moyenneNotes)).append("/5.0");
            details.append("\nNombre d'avis: ").append(avis.size());
            details.append("\n\nDerniers avis:");
            // Afficher les 3 derniers avis
            int start = Math.max(0, avis.size() - 3);
            for (int i = start; i < avis.size(); i++) {
                details.append("\n---\n").append(avis.get(i).toString());
            }
        }

        return details.toString();
    }

    /**
     * Ajoute un avis au livre.
     * 
     * @param avis l'avis à ajouter
     */
    public void ajouterAvis(Avis avis) {
        this.avis.add(avis);
    }

    /**
     * Calcule la moyenne des notes de l'avis.
     * 
     * @return la moyenne des notes ou 0 si le livre n'a pas d'avis
     */
    public double getMoyenneNotes() {
        if (avis.isEmpty())
            return 0;
        int somme = 0;
        for (Avis a : avis) {
            somme += a.getNote();
        }
        return (double) somme / avis.size();
    }

    // Getters

    public ArrayList<Avis> getAvis() {
        return avis;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getAnnee() {
        return annee;
    }

    public String getGenre() {
        return genre;
    }

    public boolean estDisponible() {
        return estDisponible;
    }

    public void marquerDisponible() {
        this.estDisponible = true;
    }

    public void marquerIndisponible() {
        this.estDisponible = false;
    }
}