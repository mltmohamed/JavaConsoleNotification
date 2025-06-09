package org.example;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        File fichierJson = new File("lesEmployes.json");
        System.out.println(" _     _ _                             ");
        System.out.println("| |   (_) |                            ");
        System.out.println("| |__  _| |__   ___  _ __   ___  ___   ");
        System.out.println("| '_ \\| | '_ \\ / _ \\| '_ \\ / _ \\/ __|  ");
        System.out.println("| |_) | | | | | (_) | | | |  __/\\__ \\  ");
        System.out.println("|_.__/|_|_| |_|\\___/|_| |_|\\___||___/  ");
        System.out.println("                                       ");
        System.out.println("            B I E N V E N U            ");
        System.out.println("=======================================");

        System.out.println("voulez vous vous connecter en tant que ? ");
        System.out.println("                                       ");
        System.out.println("1.ADMIN       2.Utilisateur");
        // Je recupère la saisie de l'utilisateur
        System.out.print("Votre choix ? : ");
        Scanner scanner = new Scanner(System.in);
        String choixConnexion = scanner.nextLine();
        // condition pour se connecter en tant qu'admin
        int choixConnexions = Integer.parseInt(choixConnexion);
        if(choixConnexions == 1){
            //Creation de l'instance admin
            Admin admin = new Admin("admin@gmail.com","admin");
            System.out.print("Email admin : ");
            String emailAdmin = scanner.nextLine();
            System.out.print("password admin : ");
            String passwordAdmin = scanner.nextLine();
            // je verifi si les données entrées de l'utilisateur sont correctes
            //connexion en tant qu'admin
            if(emailAdmin.equals(admin.getEmail()) && passwordAdmin.equals(admin.getPassword())){
                System.out.println(" _     _ _                             ");
                System.out.println("| |   (_) |                            ");
                System.out.println("| |__  _| |__   ___  _ __   ___  ___   ");
                System.out.println("| '_ \\| | '_ \\ / _ \\| '_ \\ / _ \\/ __|  ");
                System.out.println("| |_) | | | | | (_) | | | |  __/\\__ \\  ");
                System.out.println("|_.__/|_|_| |_|\\___/|_| |_|\\___||___/  ");
                System.out.println("                                       ");
                System.out.println("            B I E N V E N U  A D M I N          ");
                System.out.println("================================================");
                System.out.println("Que voulez vous faire  ? ");
                System.out.println("1.Ajouter un abonné    \n2.Retirer un abonné    \n3. afficher la liste des abonnés  \n4. Se deconnecter:");
                String choiceAdmin = scanner.nextLine();
                int choicesAdmin = Integer.parseInt(choiceAdmin);
                // Choix ajouter un abonné
                if(choicesAdmin == 1 ){
                    admin.ajouterAbonner();
                }
                //choix retirer un abonné
                else if (choicesAdmin ==2) {
                    admin.retirerAbonner();
                }
                //choix afficher liste des abonnés
                else if (choicesAdmin ==3) {
                    admin.afficherListeAbonner();
                }
                //Se deconnecter de l'admin
                else if(choicesAdmin == 4){
                    System.out.println("vous vous êtes deconnecter avec succès");
                }
                //Choix non disponibles
                else {
                    System.out.println("Choix non disponibles !");
                }


            }
            //Email ou mot de passe admin incorrect !
            else {
                System.out.println("email ou mot de passe incorrect !");
            }
        }
        //condition pour se connecter en tant Qu'utilisateur
        else if (choixConnexions == 2) {
            System.out.println(" _     _ _                             ");
            System.out.println("| |   (_) |                            ");
            System.out.println("| |__  _| |__   ___  _ __   ___  ___   ");
            System.out.println("| '_ \\| | '_ \\ / _ \\| '_ \\ / _ \\/ __|  ");
            System.out.println("| |_) | | | | | (_) | | | |  __/\\__ \\  ");
            System.out.println("|_.__/|_|_| |_|\\___/|_| |_|\\___||___/  ");
            System.out.println("                                       ");
            System.out.println("            B I E N V E N U  UTILISATEUR          ");
            System.out.println("================================================");
            System.out.println("Que voulez vous faire  ? ");
            System.out.println("1.S'inscrire comme Employé    \n2.S'abonner à un service de notification    \n3. Se desabonner d'un service de notification    \n4.Envoyer un message    \n5. Afficher Ma liste de notification   \n6. Se deconnecter:");
            String saisiUtilisateur = scanner.nextLine();
            int saisiUtilisateurs = Integer.parseInt(saisiUtilisateur);
            // condition pour s'inscrire comme employé
            if(saisiUtilisateurs ==1){
                System.out.print("Entrer votre nom : ");
                String nomUtilisateur = scanner.nextLine();
                System.out.print("Entrer votre prenom : ");
                String prenomUtilisateur = scanner.nextLine();
                System.out.print("Entrer votre email : ");
                String emailUtilisateur = scanner.nextLine();
                System.out.print("Entrer votre motdepasse : ");
                String passwordUtilisateur = scanner.nextLine();
                Employe employe = new Employe(nomUtilisateur,prenomUtilisateur,emailUtilisateur,passwordUtilisateur);
                ListeEmploye listedesEmployes = mapper.readValue(fichierJson,ListeEmploye.class);
                //condition si le json est vide
                if(listedesEmployes.getListesEmployes() == null){
                    mapper.writerWithDefaultPrettyPrinter().writeValue(fichierJson,listedesEmployes);
                    System.out.println("Vous avez été ajouter avec succès maintenant vous pouvez vous abonnez à un service de notification");
                }
                // conditions si des json existe
                else{
                    listedesEmployes.getListesEmployes().add(employe);
                    mapper.writerWithDefaultPrettyPrinter().writeValue(fichierJson,listedesEmployes);
                    System.out.println("Vous avez été ajouter avec succès maintenant vous pouvez vous abonnez à un service de notification");
                }
            }
            // condition pour s'abonner à un service de notification
            else if (saisiUtilisateurs == 2) {
                ListeEmploye listeDesEmployes = mapper.readValue(fichierJson,ListeEmploye.class);
                System.out.print("Entrer votre email ");
                String emailAbonner = scanner.nextLine();
                System.out.print("Entrer votre mot de passe ");
                String passwordAbonner = scanner.nextLine();
                for(Employe liste : listeDesEmployes.getListesEmployes()){
                    if(liste.getEmail().equals(emailAbonner) && liste.getPassword().equals(passwordAbonner)){
                        if(liste.getIsEstAbonne() == true){
                            System.out.println("Desolé vous êtes dejà abonner à un service de notification");
                        }
                        else if(liste.getIsEstAbonne() == false){
                            liste.abonnerServicedeNotification();
                            System.out.println("Bravo vous êtes maintenant abonnez à un service de notification vous pouvez envoyez un message");
                            mapper.writerWithDefaultPrettyPrinter().writeValue(fichierJson,listeDesEmployes);
                        }
                    }
                }
            }
            //condition pour se desabonner d'un service de notification
            else if (saisiUtilisateurs ==3) {
                ListeEmploye listeDesEmployes = mapper.readValue(fichierJson,ListeEmploye.class);
                System.out.print("Entrer votre email ");
                String emailAbonner = scanner.nextLine();
                System.out.print("Entrer votre mot de passe ");
                String passwordAbonner = scanner.nextLine();
                for(Employe liste : listeDesEmployes.getListesEmployes()){
                    if(liste.getEmail().equals(emailAbonner) && liste.getPassword().equals(passwordAbonner)){
                        if(liste.getIsEstAbonne() == false){
                            System.out.println("Vous n'êtes abonnés à aucun service de notification");
                        }
                        else if(liste.getIsEstAbonne() == true){
                            liste.setEstAbonne(false);
                            System.out.println("Vous êtes maintenant desabonner du service de notification");
                            mapper.writerWithDefaultPrettyPrinter().writeValue(fichierJson,listeDesEmployes);
                        }
                    }
                }
            }
            //condition pour envoyer un message
            else if (saisiUtilisateurs ==4) {
                System.out.println("Quelle type de message voulez vous envoyez ? \n 1.Email  2.notification");
                String saisiMessage = scanner.nextLine();
                int saisiMessages = Integer.parseInt(saisiMessage);
                //condition envoi de l'email
                if(saisiMessages == 1){

                }
                //condition envoi de notification push
                else if (saisiMessages == 2) {
                    Abonne abonne = new Abonne("admin","admin", "admin@gmail.com","admin");
                    abonne.EnvoyerNotification();
                }
                //condition choix non disponiblle
                else{
                    System.out.println("Choix non disponible");
                }

            }
            //condition afficher ma liste de notification
            else if (saisiUtilisateurs ==5) {
                Notification notification = new Notification();
                notification.afficherNotificationsReçus();
            }
            //condition pour se deconnecter
            else if (saisiUtilisateurs ==6) {

            }
        }
        //condition pour le choix indisponible
        else {
            System.out.println("Désolé  votre choix est indisponible");
        }
    }

    }
