import java.util.Scanner;
import services.Bibliotheque;
public class Main {
    public static void main(String[] args) {
        Bibliotheque bibliotheque = new Bibliotheque();
        Scanner scanner = new Scanner(System.in);
        menu();
    }

    public static void menu() {
        int choix = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            afficherMenu();
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
//                    ajouterLivre();
                    break;
                case 2:
//                    supprimerLivre();
                    break;
                case 3:
//                    rechercherLivre();
                    break;
                case 4:
//                    emprunterLivre();
                    break;
                case 5:
//                    retournerLivre();
                    break;
                case 6:
//                    afficherStatistiques();
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
}