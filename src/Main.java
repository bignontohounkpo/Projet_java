import java.util.ArrayList;
import java.util.Scanner;
import services.Bibliotheque;
import models.Livre;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Bibliotheque bibliotheque = new Bibliotheque("CAEB");
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Bienvenue à la bibliothèque " + bibliotheque.getNom());
        System.out.println("Êtes-vous :");
        System.out.println("1. Utilisateur");
        System.out.println("2. Bibliothécaire");
        
        int roleChoix = scanner.nextInt();
        scanner.nextLine(); 
        
        if (roleChoix == 2) {
            menuBibliothecaire(bibliotheque, scanner);
        } else {
            menuUtilisateur(bibliotheque, scanner);
        }
        
        scanner.close();
    }

    // Fonction pour afficher le menu bibliothécaire
    public static void menuBibliothecaire(Bibliotheque bibliotheque, Scanner scanner) {
        int choix;
        do {
            System.out.println("\n=== Menu Bibliothécaire ===");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Supprimer un livre");
            System.out.println("3. Afficher tous les livres");
            System.out.println("4. Afficher les statistiques");
            System.out.println("5. Quitter");
            choix = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choix) {
                case 1:
                    bibliotheque.ajouterLivre();
                    break;
                case 2:
                    bibliotheque.supprimerLivre();
                    break;
                case 3:
                    bibliotheque.afficherTousLesLivres();
                    break;
                case 4:
                    bibliotheque.afficherStatistiques();
                    break;
                case 5:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        } while (choix != 5);
    }

    // Fonction pour afficher le menu utilisateur
    public static void menuUtilisateur(Bibliotheque bibliotheque, Scanner scanner) {
        int choix;
        do {
            System.out.println("\n=== Menu Utilisateur ===");
            System.out.println("1. Rechercher un livre");
            System.out.println("2. Emprunter un livre");
            System.out.println("3. Retourner un livre");
            System.out.println("4. Voir mon historique");
            System.out.println("5. Quitter");
            
            choix = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choix) {
                case 1:
                    rechercherLivre(bibliotheque, scanner);
                    break;
                case 2:
                    bibliotheque.emprunterLivreInteractif();
                    break;
                case 3:
                    bibliotheque.retournerLivreInteractif();
                    break;
                case 4:
                    bibliotheque.voirHistoriqueUtilisateur();
                    break;
                case 5:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        } while (choix != 5);
    }


    // Fonction pour rechercher un livre par terme de recherche

    public static void rechercherLivre(Bibliotheque bibliotheque, Scanner scanner) {
        System.out.println("Entrez le terme de recherche (titre, auteur, genre ou année) :");
        String terme = scanner.nextLine();
        HashMap<String, ArrayList<Livre>> resultats = bibliotheque.rechercherLivre(terme);
        
        if (resultats.isEmpty()) {
            System.out.println("Aucun livre trouvé");
        } else {
            System.out.println("\n=== Résultats de la recherche ===");
            for (String categorie : resultats.keySet()) {
                System.out.println("\nRésultats par " + categorie + " :");
                for (Livre livre : resultats.get(categorie)) {
                    System.out.println("\n" + livre.getDetails());
                }
            }
        }
    }
}