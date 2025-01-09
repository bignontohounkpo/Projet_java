package models;

public class Avis {
    private Utilisateur utilisateur;
    private int note;
    private String commentaire;

    /**
     * Crée un nouveau avis avec un utilisateur, une note et un commentaire.
     * 
     * @param utilisateur L'utilisateur qui a fait l'avis.
     * @param note        La note donnée (entre 1 et 5).
     * @param commentaire Le commentaire de l'avis.
     */
    public Avis(Utilisateur utilisateur, int note, String commentaire) {
        this.utilisateur = utilisateur;
        this.note = Math.min(5, Math.max(1, note));
        this.commentaire = commentaire;
    }

    /**
     * Récupère l'utilisateur qui a fait l'avis.
     * 
     * @return L'utilisateur qui a fait l'avis.
     */
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    // Getters

    public int getNote() {
        return note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    /*
     * Récupère la représentation en chaîne de l'avis.
     * 
     * @return La chaîne de caractères contenant les informations de l'avis.
     */
    @Override
    public String toString() {
        return String.format("Note de %s %s : %d/5 étoiles\n%s",
                utilisateur.getPrenom(),
                utilisateur.getNom(),
                note,
                commentaire);
    }
}
