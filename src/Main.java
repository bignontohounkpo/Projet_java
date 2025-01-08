import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import services.Bibliotheque;
import models.Livre;
import models.Utilisateur;

public class Main {
    public static void main(String[] args) {
        Bibliotheque bibliotheque = new Bibliotheque("CAEB");
        Scanner scanner = new Scanner(System.in);
        menu(bibliotheque, scanner);
        scanner.close();
    }

    public static void menu(Bibliotheque bibliotheque, Scanner scanner) {
        int choix = 0;
        do {
            afficherMenu();
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    bibliotheque.ajouterLivre();
                    break;
                case 2:
                    bibliotheque.supprimerLivre();
                    break;
                case 3:
                    rechercherLivre(bibliotheque);
                    break;
                case 4:
                    bibliotheque.emprunterLivreInteractif();
                    break;
                case 5:
                    bibliotheque.retournerLivreInteractif();
                    break;
                case 6:
                    bibliotheque.afficherStatistiques();
                    break;
                case 7:
                    System.out.println("Merci d'avoir utilisé notre service");
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        } while (choix != 7);

    }
    public static void afficherMenu() {
        System.out.println("Bienvenue à la bibliothèque");
        System.out.println("1. Ajouter un livre");
        System.out.println("2. Supprimer un livre");
        System.out.println("3. Rechercher un livre");
        System.out.println("4. Emprunter un livre");
        System.out.println("5. Retourner un livre");
        System.out.println("6. Afficher les statistiques");
        System.out.println("7. Quitter");
        System.out.println("Choisissez une option en tapant le chiffre correspon au clavier: ");
    }

    public static void rechercherLivre(Bibliotheque bibliotheque) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le titre du livre : ");
        String titre = scanner.nextLine();
        HashMap<String, ArrayList<Livre>> Resultat = bibliotheque.rechercherLivre(titre);
        afficherResultats(Resultat);
    }

    public static void afficherResultats(HashMap<String, ArrayList<Livre>> Resultat) {
        System.out.println("Résultat de la recherche :");

        if (Resultat.isEmpty()) {
            System.out.println("Aucun livre trouve");
            return;
        }

        for (String key : Resultat.keySet()) {
            System.out.println("Recherche en fonction du " + key + ":");
            for (Livre livre : Resultat.get(key)) {
                System.out.println(livre.getDetails());
            }
        }
    }

}