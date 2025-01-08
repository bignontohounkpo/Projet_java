package services;
import models.Livre;
import models.Utilisateur;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Bibliotheque {
    private String nom;
    private HashMap <Integer, Livre> livres;
    private HashMap <Integer, Utilisateur> utilisateurs;
    private HashMap <Utilisateur, ArrayList<Livre>> historique;

    public Bibliotheque(String nom) {
        this.nom = nom;
        this.livres = new HashMap <Integer, Livre>();
        this.utilisateurs = new HashMap <Integer, Utilisateur>();
        this.historique = new HashMap <Utilisateur, ArrayList<Livre>>();
        this.initialiserBibliotheque();
    }

    private void initialiserBibliotheque() {
        this.livres.put(1, new Livre("Le Petit Prince", "Antoine de Saint-Exupéry", 1943, "Fiction"));
        this.livres.put(2, new Livre("1984", "George Orwell", 1949, "Dystopian"));
        this.livres.put(3, new Livre("Moby Dick", "Herman Melville", 1851, "Adventure"));
        this.livres.put(4, new Livre("Les Misérables", "Victor Hugo", 1862, "Historical"));
    }

    public void ajouterLivre() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le titre du livre: ");
        String titre = scanner.nextLine();
        System.out.println("Entrez l'auteur du livre: ");
        String auteur = scanner.nextLine();
        System.out.println("Entrez le genre du livre: ");
        String genre = scanner.nextLine();
        System.out.println("Entrez l'annee du livre: ");
        int annee = scanner.nextInt();
        Livre livre = new Livre(titre, auteur, annee, genre);
        this.livres.put(livre.getId(), livre);
    }

    public void supprimerLivre() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le titre du livre: ");
        String nom = scanner.nextLine();
        HashMap<String, ArrayList<Livre>> Resultat = this.rechercherLivre(nom);
        
        if (Resultat.isEmpty()) {
            System.out.println("Aucun livre trouvé");
            return;
        }
        
        for (String key : Resultat.keySet()) {
            System.out.println("Recherche en fonction du " + key + ":");
            for (Livre livre : Resultat.get(key)) {
                System.out.println(livre.getDetails());
            }
        }
    
        int id = 0;
        while (true) {
            System.out.println("\nEntrez l'id du livre correspondant: ");
            id = scanner.nextInt();
            scanner.nextLine();
    
            if (!this.livres.containsKey(id)) {
                System.out.println("Le livre n'existe pas, veuillez réessayer.");
            } else {
                break;
            }
        }
    
        System.out.println("Veuillez confirmer la suppression du livre: " + this.livres.get(id).getTitre());
        System.out.println("Êtes-vous sûr de vouloir supprimer ce livre ? (o/n)");
        String confirmation = scanner.nextLine(); 
        if (confirmation.equals("o")) {
            this.livres.remove(id);
            System.out.println("Le livre a bien été supprimé");
        } else {
            System.out.println("Suppression annulée");
        }
    }

    public HashMap <String, ArrayList<Livre>> rechercherLivreParEquivalence(String titre) {
        HashMap <String, ArrayList<Livre>> Resultat = new HashMap <String, ArrayList<Livre>>();

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

    public HashMap <String, ArrayList<Livre>> rechercherLivreParInclusion(String titre) {
        HashMap <String, ArrayList<Livre>> Resultat = new HashMap <String, ArrayList<Livre>>();

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
    
    public HashMap <String, ArrayList<Livre>> rechercherLivre(String titre) {
        HashMap <String, ArrayList<Livre>> Resultat = new HashMap <String, ArrayList<Livre>>();

        Resultat = rechercherLivreParEquivalence(titre);
        if (Resultat.isEmpty()) {
            Resultat = rechercherLivreParInclusion(titre);
        }
        return Resultat;
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

    public void afficherStatistiques() {
        System.out.println("Nombre de livres: " + this.livres.size());
        System.out.println("Nombre d'utilisateurs: " + this.utilisateurs.size());
        System.out.println("Nombre d'emprunts: " + this.historique.size());
    }

    public void emprunterLivreInteractif() {
        Scanner scanner = new Scanner(System.in);
        
        // Gestion de l'utilisateur
        System.out.println("Entrez l'ID de l'utilisateur (ou 0 pour nouveau utilisateur): ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        
        Utilisateur utilisateur;
        if (userId == 0) {
            System.out.println("Création d'un nouvel utilisateur");
            System.out.println("Nom: ");
            String nom = scanner.nextLine();
            System.out.println("Prénom: ");
            String prenom = scanner.nextLine();
            System.out.println("Email: ");
            String email = scanner.nextLine();
            utilisateur = new Utilisateur(nom, prenom, email, new ArrayList<Livre>());
            this.utilisateurs.put(utilisateur.getId(), utilisateur);
            System.out.println("Nouvel utilisateur créé avec l'ID: " + utilisateur.getId());
        } else {
            utilisateur = this.utilisateurs.get(userId);
            if (utilisateur == null) {
                System.out.println("Utilisateur non trouvé.");
                return;
            }
        }

        // Recherche et sélection du livre
        System.out.println("Entrez le titre du livre à emprunter: ");
        String titreLivre = scanner.nextLine();
        HashMap<String, ArrayList<Livre>> resultats = this.rechercherLivre(titreLivre);
        
        if (resultats.isEmpty()) {
            System.out.println("Aucun livre trouvé.");
            return;
        }

        // Affichage des résultats
        for (String key : resultats.keySet()) {
            System.out.println("\nRésultats pour " + key + ":");
            for (Livre livre : resultats.get(key)) {
                System.out.println(livre.getDetails() + " - Disponible: " + (livre.estDisponible() ? "Oui" : "Non"));
            }
        }

        // Sélection du livre
        System.out.println("\nEntrez l'ID du livre à emprunter: ");
        int livreId = scanner.nextInt();
        Livre livreChoisi = this.livres.get(livreId);

        if (livreChoisi == null) {
            System.out.println("Livre non trouvé.");
            return;
        }

        if (!livreChoisi.estDisponible()) {
            System.out.println("Ce livre n'est pas disponible actuellement.");
            return;
        }

        // Emprunt du livre
        this.emprunterLivre(utilisateur, livreChoisi);
        System.out.println("Livre emprunté avec succès!");
    }

    public void retournerLivreInteractif() {
        Scanner scanner = new Scanner(System.in);
        
        // Identification de l'utilisateur
        System.out.println("Entrez l'ID de l'utilisateur: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        
        Utilisateur utilisateur = this.utilisateurs.get(userId);
        if (utilisateur == null) {
            System.out.println("Utilisateur non trouvé.");
            return;
        }

        // Vérification si l'utilisateur a des livres empruntés
        ArrayList<Livre> livresEmpruntes = this.historique.get(utilisateur);
        if (livresEmpruntes == null || livresEmpruntes.isEmpty()) {
            System.out.println("Cet utilisateur n'a aucun livre emprunté.");
            return;
        }

        // Affichage des livres empruntés
        System.out.println("\nLivres empruntés par l'utilisateur:");
        for (Livre livre : livresEmpruntes) {
            System.out.println(livre.getDetails());
        }

        // Sélection du livre à retourner
        System.out.println("\nEntrez l'ID du livre à retourner: ");
        int livreId = scanner.nextInt();
        
        Livre livreARetourner = null;
        for (Livre livre : livresEmpruntes) {
            if (livre.getId() == livreId) {
                livreARetourner = livre;
                break;
            }
        }

        if (livreARetourner == null) {
            System.out.println("Ce livre n'a pas été emprunté par cet utilisateur.");
            return;
        }

        // Retour du livre
        this.retournerLivre(utilisateur, livreARetourner);
        System.out.println("Livre retourné avec succès!");
    }
}