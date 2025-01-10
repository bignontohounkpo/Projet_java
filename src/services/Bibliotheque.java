package services;

import models.Livre;
import models.Utilisateur;
import models.Avis;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Classe représentant une bibliothèque.
 * Elle gère les livres, les utilisateurs, les emprunts et les avis.
 */
public class Bibliotheque {
    private String nom;
    private HashMap<Integer, Livre> livres;
    private HashMap<Integer, Utilisateur> utilisateurs;
    private HashMap<Utilisateur, ArrayList<Livre>> historique;
    private HashMap<Utilisateur, ArrayList<Livre>> empruntsActuels;
    private Scanner scanner;

    /**
     * Constructeur de la classe Bibliotheque.
     * 
     * @param nom Le nom de la bibliothèque
     */
    public Bibliotheque(String nom) {
        this.nom = nom;
        this.livres = new HashMap<>();
        this.utilisateurs = new HashMap<>();
        this.historique = new HashMap<>();
        this.empruntsActuels = new HashMap<>();
        this.scanner = new Scanner(System.in);
        this.initialiserBibliotheque();
    }

    /**
     * Initialise la bibliothèque avec des données par défaut :
     * - Quelques livres de différents genres
     * - Des utilisateurs (un bibliothécaire et des utilisateurs standard)
     */
    private void initialiserBibliotheque() {
        // Création des livres par défaut
        Livre livre1 = new Livre("L'Enfant noir", "Camara Laye", 1953, "Roman autobiographique");
        Livre livre2 = new Livre("Les Bouts de bois de Dieu", "Ousmane Sembène", 1960, "Roman historique");
        Livre livre3 = new Livre("Le Monde s'effondre", "Chinua Achebe", 1958, "Roman");
        Livre livre4 = new Livre("Une si longue lettre", "Mariama Bâ", 1979, "Roman épistolaire");
        Livre livre5 = new Livre("L'Étrange destin de Wangrin", "Amadou Hampâté Bâ", 1973, "Roman");

        livres.put(livre1.getId(), livre1);
        livres.put(livre2.getId(), livre2);
        livres.put(livre3.getId(), livre3);
        livres.put(livre4.getId(), livre4);
        livres.put(livre5.getId(), livre5);

        // Création des utilisateurs par défaut
        Utilisateur bibliothecaire = new Utilisateur("Dr MOUSSE", "Mikael", "mikael.mousse@gmail.com",
                "bibliothecaire");
        Utilisateur utilisateur1 = new Utilisateur("ATCHO", "Iovann", "iovannatcho@gmail.com", "utilisateur");
        Utilisateur utilisateur2 = new Utilisateur("TOHOUNKPO", "Prisca", "bpriscatohounkpo@gmail.com", "utilisateur");

        utilisateurs.put(bibliothecaire.getId(), bibliothecaire);
        utilisateurs.put(utilisateur1.getId(), utilisateur1);
        utilisateurs.put(utilisateur2.getId(), utilisateur2);

        // Initialisation des historiques et emprunts
        historique.put(bibliothecaire, new ArrayList<>());
        historique.put(utilisateur1, new ArrayList<>());
        historique.put(utilisateur2, new ArrayList<>());

        empruntsActuels.put(bibliothecaire, new ArrayList<>());
        empruntsActuels.put(utilisateur1, new ArrayList<>());
        empruntsActuels.put(utilisateur2, new ArrayList<>());
    }

    /**
     * Permet d'ajouter un nouveau livre à la bibliothèque de manière interactive.
     * Demande à l'utilisateur de saisir les informations du livre (titre, auteur,
     * année, genre).
     */
    public void ajouterLivre() {
        System.out.println("Entrez le titre du livre: ");
        String titre = scanner.nextLine();
        System.out.println("Entrez l'auteur du livre: ");
        String auteur = scanner.nextLine();
        System.out.println("Entrez le genre du livre: ");
        String genre = scanner.nextLine();
        System.out.println("Entrez l'annee du livre: ");
        int annee = scanner.nextInt();

        while (annee < 0 || annee > 2023) {
            System.out.println("Année invalide");
            System.out.println("Entrez l'annee du livre: ");
            annee = scanner.nextInt();
        }

        scanner.nextLine();
        Livre livre = new Livre(titre, auteur, annee, genre);
        this.livres.put(livre.getId(), livre);
        System.out.println("Livre ajouté avec succès !");
    }

    public String getNom() {
        return this.nom;
    }

    /**
     * Permet de supprimer un livre de la bibliothèque.
     * Recherche d'abord le livre par titre, puis demande confirmation avant la
     * suppression.
     */
    public void supprimerLivre() {
        System.out.println("Entrez le titre du livre à supprimer :");
        String titre = scanner.nextLine();

        HashMap<String, ArrayList<Livre>> resultats = rechercherLivre(titre);
        if (resultats.isEmpty()) {
            System.out.println("Aucun livre trouvé");
            return;
        }

        System.out.println("\n=== Livres trouvés ===");
        for (String categorie : resultats.keySet()) {
            System.out.println("\nRésultats par " + categorie + " :");
            for (Livre livre : resultats.get(categorie)) {
                System.out.println(livre.getDetails());
            }
        }

        System.out.println("\nEntrez l'ID du livre à supprimer :");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (livres.containsKey(id)) {
            System.out.println("Êtes-vous sûr de vouloir supprimer ce livre ? (o/n)");
            String confirmation = scanner.nextLine();
            if (confirmation.toLowerCase().equals("o")) {
                livres.remove(id);
                System.out.println("Livre supprimé avec succès !");
            } else {
                System.out.println("Suppression annulée");
            }
        } else {
            System.out.println("ID invalide");
        }
    }

    /**
     * Recherche des livres dans la bibliothèque selon un titre.
     * La recherche s'effectue sur le titre, l'auteur, le genre et l'année par une
     * recherche exacte.
     * 
     * @param titre Le titre à rechercher
     * @return Une HashMap contenant les résultats classés par catégorie (titre,
     *         auteur, genre, année)
     */
    public HashMap<String, ArrayList<Livre>> rechercherLivreParEquivalence(String titre) {
        HashMap<String, ArrayList<Livre>> Resultat = new HashMap<String, ArrayList<Livre>>();

        for (Livre livre : this.livres.values()) {
            if (livre.getTitre().toLowerCase().contains(titre.toLowerCase())) {
                if (!Resultat.containsKey("Titre")) {
                    Resultat.put("Titre", new ArrayList<Livre>());
                }
                Resultat.get("Titre").add(livre);
            }

            if (livre.getAuteur().toLowerCase().contains(titre.toLowerCase())) {
                if (!Resultat.containsKey("Auteur")) {
                    Resultat.put("Auteur", new ArrayList<Livre>());
                }
                Resultat.get("Auteur").add(livre);
            }

            if (livre.getGenre().toLowerCase().contains(titre.toLowerCase())) {
                if (!Resultat.containsKey("Genre")) {
                    Resultat.put("Genre", new ArrayList<Livre>());
                }
                Resultat.get("Genre").add(livre);
            }

            try {
                if (livre.getAnnee() == Integer.parseInt(titre)) {
                    if (!Resultat.containsKey("Annee")) {
                        Resultat.put("Annee", new ArrayList<Livre>());
                    }
                    Resultat.get("Annee").add(livre);
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }
        return Resultat;
    }

    /**
     * Recherche des livres dans la bibliothèque selon un titre.
     * La recherche s'effectue sur le titre, l'auteur, le genre et l'année par une
     * recherche par inclusion.
     * 
     * @param titre Le titre à rechercher
     * @return Une HashMap contenant les résultats classés par catégorie (titre,
     *         auteur, genre, année)
     */

    public HashMap<String, ArrayList<Livre>> rechercherLivreParInclusion(String titre) {
        HashMap<String, ArrayList<Livre>> Resultat = new HashMap<String, ArrayList<Livre>>();

        for (Livre livre : this.livres.values()) {
            if (livre.getTitre().toLowerCase().contains(titre.toLowerCase())) {
                if (!Resultat.containsKey("Titre")) {
                    Resultat.put("Titre", new ArrayList<Livre>());
                }
                Resultat.get("Titre").add(livre);
            }

            if (livre.getAuteur().toLowerCase().contains(titre.toLowerCase())) {
                if (!Resultat.containsKey("Auteur")) {
                    Resultat.put("Auteur", new ArrayList<Livre>());
                }
                Resultat.get("Auteur").add(livre);
            }

            if (livre.getGenre().toLowerCase().contains(titre.toLowerCase())) {
                if (!Resultat.containsKey("Genre")) {
                    Resultat.put("Genre", new ArrayList<Livre>());
                }
                Resultat.get("Genre").add(livre);
            }

            try {
                if (livre.getAnnee() == Integer.parseInt(titre)) {
                    if (!Resultat.containsKey("Annee")) {
                        Resultat.put("Annee", new ArrayList<Livre>());
                    }
                    Resultat.get("Annee").add(livre);
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }
        return Resultat;
    }

    /**
     * Recherche des livres dans la bibliothèque selon un titre.
     * La recherche regroupe les deux differentes recherches. si aucunes
     * correspondances directe n'est trouvé alors la recherche est faite par
     * inclusion
     * 
     * @param titre Le titre à rechercher
     * @return Une HashMap contenant les résultats classés par catégorie (titre,
     *         auteur, genre, année)
     */
    public HashMap<String, ArrayList<Livre>> rechercherLivre(String titre) {
        HashMap<String, ArrayList<Livre>> Resultat = new HashMap<String, ArrayList<Livre>>();

        Resultat = rechercherLivreParEquivalence(titre);
        if (Resultat.isEmpty()) {
            Resultat = rechercherLivreParInclusion(titre);
        }
        return Resultat;
    }

    /**
     * Affiche la liste de tous les livres de la bibliothèque.
     */
    public void afficherTousLesLivres() {
        if (livres.isEmpty()) {
            System.out.println("Aucun livre dans la bibliothèque");
            return;
        }
        System.out.println("\nListe de tous les livres :");
        for (Livre livre : livres.values()) {
            System.out.println("\n" + livre.getDetails());
        }
    }

    /**
     * Permet à un utilisateur d'emprunter un livre de manière interactive.
     * Si l'utilisateur n'a pas de compte, permet d'en créer un.
     * Recherche le livre par titre et permet de le sélectionner par ID.
     */
    public void emprunterLivreInteractif() {
        System.out.println("Avez-vous déjà un compte ? (o/n)");
        String reponse = scanner.nextLine();

        Utilisateur utilisateur;
        if (reponse.toLowerCase().equals("n")) {
            // Création d'un nouvel utilisateur
            System.out.println("=== Création d'un nouveau compte ===");
            System.out.println("Entrez votre nom :");
            String nom = scanner.nextLine();
            System.out.println("Entrez votre prénom :");
            String prenom = scanner.nextLine();
            System.out.println("Entrez votre email :");
            String email = scanner.nextLine();

            utilisateur = new Utilisateur(nom, prenom, email, "utilisateur");
            utilisateurs.put(utilisateur.getId(), utilisateur);
            historique.put(utilisateur, new ArrayList<>());
            empruntsActuels.put(utilisateur, new ArrayList<>());
            System.out.println("Compte créé avec succès ! Votre ID est : " + utilisateur.getId());
        } else {
            System.out.println("Entrez votre ID utilisateur :");
            int userId = scanner.nextInt();
            scanner.nextLine();

            utilisateur = utilisateurs.get(userId);
            if (utilisateur == null) {
                System.out.println("Utilisateur non trouvé");
                return;
            }
            if (!historique.containsKey(utilisateur)) {
                historique.put(utilisateur, new ArrayList<>());
                empruntsActuels.put(utilisateur, new ArrayList<>());
            }
        }

        System.out.println("Entrez le titre du livre à emprunter :");
        String titre = scanner.nextLine();
        HashMap<String, ArrayList<Livre>> resultats = rechercherLivre(titre);

        if (resultats.isEmpty()) {
            System.out.println("Aucun livre trouvé");
            return;
        }

        for (String categorie : resultats.keySet()) {
            System.out.println("\nRésultats par " + categorie + " :");
            for (Livre livre : resultats.get(categorie)) {
                if (livre.estDisponible()) {
                    System.out.println(livre.getDetails());
                }
            }
        }

        System.out.println("\nEntrez l'ID du livre à emprunter :");
        int livreId = scanner.nextInt();
        scanner.nextLine();

        Livre livre = livres.get(livreId);
        if (livre != null && livre.estDisponible()) {
            livre.marquerIndisponible();
            historique.get(utilisateur).add(livre);
            empruntsActuels.get(utilisateur).add(livre);
            System.out.println("Livre emprunté avec succès !");
        } else {
            System.out.println("Livre non disponible ou ID invalide");
        }
    }

    /**
     * Permet à un utilisateur de retourner un livre emprunté.
     * Affiche la liste des livres actuellement empruntés par l'utilisateur.
     * Propose de laisser un avis après le retour du livre.
     */
    public void retournerLivreInteractif() {
        System.out.println("Entrez votre ID utilisateur :");
        int userId = scanner.nextInt();
        scanner.nextLine();

        Utilisateur utilisateur = utilisateurs.get(userId);
        if (utilisateur == null) {
            System.out.println("Utilisateur non trouvé");
            return;
        }

        ArrayList<Livre> livresEmpruntes = empruntsActuels.get(utilisateur);
        if (livresEmpruntes == null || livresEmpruntes.isEmpty()) {
            System.out.println("Vous n'avez aucun livre emprunté actuellement");
            return;
        }

        System.out.println("\n=== Vos livres actuellement empruntés ===");
        for (Livre livre : livresEmpruntes) {
            System.out.println("\n" + livre.getDetails());
        }

        System.out.println("\nEntrez l'ID du livre à retourner :");
        int livreId = scanner.nextInt();
        scanner.nextLine();

        Livre livre = livres.get(livreId);
        if (livre != null && livresEmpruntes.contains(livre)) {
            livre.marquerDisponible();
            livresEmpruntes.remove(livre);

            // Demander un avis
            System.out.println("\nVoulez-vous laisser un avis pour ce livre ? (o/n)");
            String reponse = scanner.nextLine();

            if (reponse.toLowerCase().equals("o")) {
                System.out.println("Donnez une note de 1 à 5 étoiles :");
                int note = scanner.nextInt();

                while (note < 0 || note > 5) {
                    System.out.println("Note invalide");
                    System.out.println("Donnez une note de 1 à 5 étoiles :");
                    note = scanner.nextInt();
                }

                scanner.nextLine();

                System.out.println("Laissez un commentaire :");
                String commentaire = scanner.nextLine();

                Avis avis = new Avis(utilisateur, note, commentaire);
                livre.ajouterAvis(avis);
                System.out.println("Merci pour votre avis !");
            }

            System.out.println("Livre retourné avec succès !");
        } else {
            System.out.println("ID invalide ou livre non emprunté par vous");
        }
    }

    /**
     * Affiche l'historique complet d'un utilisateur :
     * - Tous les livres empruntés (actuels et passés)
     * - Le statut de chaque livre (retourné ou en cours d'emprunt)
     * - Les avis laissés par l'utilisateur
     */
    public void voirHistoriqueUtilisateur() {
        System.out.println("Entrez votre ID utilisateur :");
        int userId = scanner.nextInt();
        scanner.nextLine();

        Utilisateur utilisateur = utilisateurs.get(userId);
        if (utilisateur == null) {
            System.out.println("Utilisateur non trouvé");
            return;
        }

        ArrayList<Livre> historiqueLivres = historique.get(utilisateur);
        ArrayList<Livre> empruntsActuelsLivres = empruntsActuels.get(utilisateur);

        if (historiqueLivres == null || historiqueLivres.isEmpty()) {
            System.out.println("Vous n'avez jamais emprunté de livre");
            return;
        }

        System.out.println("\n=== Votre historique d'emprunts ===");
        System.out.println("Nombre total de livres empruntés : " + historiqueLivres.size());

        // Afficher tous les livres de l'historique
        System.out.println("\nTous les livres que vous avez empruntés :");
        for (Livre livre : historiqueLivres) {
            System.out.println("\n" + livre.getDetails());
            boolean estActuellementEmprunte = empruntsActuelsLivres.contains(livre);
            System.out.println("Statut actuel : " + (estActuellementEmprunte ? "En cours d'emprunt" : "Retourné"));
        }

        // Afficher les avis de l'utilisateur
        System.out.println("\nVos avis :");
        boolean aAuMoinsUnAvis = false;
        for (Livre livre : livres.values()) {
            for (Avis avis : livre.getAvis()) {
                if (avis.getUtilisateur().getId() == utilisateur.getId()) {
                    aAuMoinsUnAvis = true;
                    System.out.println("\nLivre : " + livre.getTitre());
                    System.out.println(avis.toString());
                }
            }
        }
        if (!aAuMoinsUnAvis) {
            System.out.println("Vous n'avez pas encore laissé d'avis");
        }
    }

    /**
     * Affiche les statistiques de la bibliothèque :
     * - Nombre total de livres
     * - Nombre de livres disponibles
     * - Nombre de livres empruntés
     * - Liste des livres les mieux notés
     */
    public void afficherStatistiques() {
        System.out.println("\n=== Statistiques de la bibliothèque ===");
        System.out.println("Nombre total de livres : " + livres.size());

        int livresDisponibles = 0;
        int livresEmpruntes = 0;

        for (Livre livre : livres.values()) {
            if (livre.estDisponible()) {
                livresDisponibles++;
            } else {
                livresEmpruntes++;
            }
        }

        System.out.println("Livres disponibles : " + livresDisponibles);
        System.out.println("Livres empruntés : " + livresEmpruntes);

        // Afficher les livres les mieux notés
        System.out.println("\nTop des livres les mieux notés :");
        livres.values().stream()
                .filter(l -> !l.getAvis().isEmpty())
                .sorted((l1, l2) -> Double.compare(l2.getMoyenneNotes(), l1.getMoyenneNotes()))
                .limit(3)
                .forEach(l -> System.out.println(l.getTitre() + " - Note moyenne : " +
                        String.format("%.1f", l.getMoyenneNotes()) + "/5.0"));
    }
}