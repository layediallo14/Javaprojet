import java.util.*;

public class GestionTache {

    private static GestionnaireTache gestionnaireTache;

    public static void main(String[] args) {
        gestionnaireTache = new GestionnaireTache(100);
        Scanner scanner = new Scanner(System.in);
        int choix = 0;

        while (choix != 7) {
            afficherMenu();
            choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer le saut de ligne après la saisie de l'entier

            switch (choix) {
                case 1:
                    ajouterTache(scanner);
                    break;
                case 2:
                    modifierTache(scanner);
                    break;
                case 3:
                    supprimerTache(scanner);
                    break;
                case 4:
                    listerToutesLesTaches();
                    break;
                case 5:
                    listerTachesParEtat(scanner);
                    break;
                case 6:
                    changerEtatTache(scanner);
                    break;
                case 7:
                     System.out.println("Merci à bientot");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
        }

        scanner.close();
    }

    private static void afficherMenu() {
        System.out.println("\n=== Gestion des tâches ===");
        System.out.println("1. Ajouter une tâche");
        System.out.println("2. Modifier une tâche");
        System.out.println("3. Supprimer une tâche");
        System.out.println("4. Lister toutes les tâches");
        System.out.println("5. Lister les tâches par état");
        System.out.println("6. Changer l'état d'une tâche");
        System.out.println("7. Quitter");
        System.out.print("Veuillez choisir une option : ");
    }

    private static void ajouterTache(Scanner scanner) {
        System.out.print("Veuillez saisir le titre de la tâche : ");
        String titre = scanner.nextLine();

        Tache nouvelleTache = new Tache(titre);
        boolean ajoutReussi = gestionnaireTache.ajouter(nouvelleTache);

        if (ajoutReussi) {
            System.out.println("Tâche ajoutée avec succès !");
        } else {
            System.out.println("Impossible d'ajouter la tâche. Le gestionnaire est plein.");
        }
    }

    private static void modifierTache(Scanner scanner) {
        System.out.print("Veuillez saisir l'identifiant de la tâche à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Pour consommer le saut de ligne après la saisie de l'entier

        Tache tacheExistante = trouverTacheParId(id);

        if (tacheExistante != null) {
            System.out.print("Veuillez saisir le nouveau titre de la tâche : ");
            String nouveauTitre = scanner.nextLine();
            tacheExistante.setTitre(nouveauTitre);
            System.out.println("Tâche modifiée avec succès !");
        } else {
            System.out.println("Aucune tâche trouvée avec l'identifiant spécifié.");
        }
    }

    private static void supprimerTache(Scanner scanner) {
        System.out.print("Veuillez saisir l'identifiant de la tâche à supprimer : ");
        int id = scanner.nextInt();

        boolean suppressionReussie = gestionnaireTache.supprimer(id);

        if (suppressionReussie) {
            System.out.println("Tâche supprimée avec succès !");
        } else {
            System.out.println("Aucune tâche trouvée avec l'identifiant spécifié.");
        }
    }

    private static void listerToutesLesTaches() {
        Tache[] taches = gestionnaireTache.lister();

        if (taches.length > 0) {
            System.out.println("\n=== Liste de toutes les tâches ===");
            for (Tache tache : taches) {
                System.out.println(tache.toString());
            }
        } else {
            System.out.println("Aucune tâche à afficher.");
        }
    }

    private static void listerTachesParEtat(Scanner scanner) {
        System.out.print("Veuillez saisir l'état des tâches à lister (EN_COURS, TERMINE, PREVU) : ");
        String etat = scanner.nextLine();

        Tache[] taches = gestionnaireTache.lister(etat);

        if (taches.length > 0) {
            System.out.println("\n=== Liste des tâches en état '" + etat + "' ===");
            for (Tache tache : taches) {
                System.out.println(tache.toString());
            }
        } else {
            System.out.println("Aucune tâche à afficher pour l'état spécifié.");
        }
    }

    private static void changerEtatTache(Scanner scanner) {
        System.out.print("Veuillez saisir l'identifiant de la tâche à modifier son état : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Pour consommer le saut de ligne après la saisie de l'entier

        Tache tacheExistante = trouverTacheParId(id);

        if (tacheExistante != null) {
            System.out.print("Veuillez saisir le nouvel état de la tâche (EN_COURS, TERMINE, PREVU) : ");
            String nouvelEtat = scanner.nextLine();
            tacheExistante.setEtat(nouvelEtat);
            System.out.println("État de la tâche modifié avec succès !");
        } else {
            System.out.println("Aucune tâche trouvée avec l'identifiant spécifié.");
        }
    }

    private static Tache trouverTacheParId(int id) {
        Tache[] taches = gestionnaireTache.lister();

        for (Tache tache : taches) {
            if (tache.getIdentifiant() == id) {
                return tache;
            }
        }

        return null;
    }

}
