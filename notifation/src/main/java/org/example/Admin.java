package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Admin implements Abonnement {
    private String email = "admin@gmail.com";
    private String password ="admin";
    public Admin(String email, String password){
        this.email = email;
        this.password = password;
    }
    // Les setters et getters de email et passwords de admin
    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}
    // les dependances utilisés
    File fichierJson = new File("lesEmployes.json");
    Scanner scanner = new Scanner(System.in);
    ObjectMapper mapper = new ObjectMapper();
    // les methodes de l'interface Abonnement
    @Override
    public void ajouterAbonner() throws IOException{
        // je verifi si le fichier existe
        List<Employe> ajouter = new ArrayList<>();
        if(fichierJson.exists() && fichierJson.length() > 0){
            System.out.print("Entrer l'email de l'employé  : ");
            String saisiEmailAbonner = scanner.nextLine();
            ListeEmploye listesdesEmployes = mapper.readValue((fichierJson),ListeEmploye.class);
            for(Employe liste : listesdesEmployes.getListesEmployes()){
                //condition pour voir si l'email existe et que l'utilisateur est dejà abonner
                if((liste.getEmail()).equals(saisiEmailAbonner) && liste.getEstAbonne()==true){
                    System.out.print("L'utilisateur " + liste.getNom() + " "+ liste.getPrenom() + "est dejà abonné à un service de notification");
                    return;
                }
                //condition pour voir si l'email existe et que l'utilisateur n'est pas abonner
                else if ((liste.getEmail()).equals(saisiEmailAbonner) && liste.getEstAbonne() == false) {
                    liste.setEstAbonne(true);
                    System.out.println("L'utilisateur " + liste.getNom() + " "+ liste.getPrenom() + "a été abonné à un service de notification avec succès");
                    mapper.writerWithDefaultPrettyPrinter().writeValue(fichierJson,listesdesEmployes);
                    return;
                }
                //condition pour voir si l'employé n'existe pas dans le fichier json
                else if(!((liste.getEmail()).equals(saisiEmailAbonner))){
                    System.out.println("Ce utilisateur n'existe pas voulez vous creer un nouveau employé abonné ?");
                    System.out.print("1.OUI 2.NON");
                    String saisinouveauEmploye = scanner.nextLine();
                    int saisinouveauxEmploye = Integer.parseInt(saisinouveauEmploye);
                    // condition si l'admin veut creer un nouveau utilisateur
                    if(saisinouveauxEmploye == 1){
                        System.out.print("Entrer le nom de l'abonné ");
                        String nomNouveauAbonne = scanner.nextLine();
                        System.out.print("Entrer le prenom de l'abonné ");
                        String prenomNouveauAbonne = scanner.nextLine();
                        System.out.print("Entrer l'email de l'abonné ");
                        String emailNouveauAbonne = scanner.nextLine();
                        System.out.print("Entrer le mot de passe de l'abonné ");
                        String passwordNouveauAbonne = scanner.nextLine();
                        Employe employe = new Employe(nomNouveauAbonne,prenomNouveauAbonne,emailNouveauAbonne,passwordNouveauAbonne);
                        employe.setEstAbonne(true);
                        ajouter.add(employe);
                        listesdesEmployes.getListesEmployes().addAll(ajouter);
                        mapper.writerWithDefaultPrettyPrinter().writeValue(fichierJson,listesdesEmployes);
                        System.out.println("Le nouveau abonné a été ajouter avec succès");
                        return;

                        // Je dois envoyer un email au nouveau abonné Ici
                    }
                    // condition si l'admin ne veut pas creer un nouveau utilisateur
                    else if(saisinouveauxEmploye == 2){
                            System.out.print("Ok l'admin");
                            return;
                    }
                    // condition si l'admin fait un choix indisponible
                    else {
                        System.out.println("Désolé le choix est indisponible");
                        return;
                    }
                }
            }
            listesdesEmployes.getListesEmployes().addAll(ajouter);
            mapper.writerWithDefaultPrettyPrinter().writeValue(fichierJson,listesdesEmployes);
            System.out.println("Le nouveau abonné a été ajouter avec succès");
        }
        //condition pour verifier si le json existe mais est vide
        else if(fichierJson.exists() && fichierJson.length()==0) {
            System.out.println("Aucun employé n'existe pour le moment voulez vous creer un nouveau ?");
            System.out.print("1.OUI \n2.NON");
            Scanner scanner = new Scanner(System.in);
            String saisiExiste = scanner.nextLine();
            int saisiExistes = Integer.parseInt(saisiExiste);
            if(saisiExistes == 1){
                System.out.print("Entrer le nom de l'abonné  : ");
                String nomNouveauAbonne = scanner.nextLine();
                System.out.print("Entrer le prenom de l'abonné : ");
                String prenomNouveauAbonne = scanner.nextLine();
                System.out.print("Entrer l'email de l'abonné : ");
                String emailNouveauAbonne = scanner.nextLine();
                System.out.print("Entrer le mot de passe de l'abonné : ");
                String passwordNouveauAbonne = scanner.nextLine();
                ListeEmploye listesdesEmployes = new ListeEmploye();
                Employe employe = new Employe(nomNouveauAbonne,prenomNouveauAbonne,emailNouveauAbonne,passwordNouveauAbonne);
                employe.setEstAbonne(true);
                listesdesEmployes.getListesEmployes().add(employe);
                mapper.writerWithDefaultPrettyPrinter().writeValue(fichierJson,listesdesEmployes);
                System.out.println("Votre nouveau abonné a été ajouter avec succès");
            }
            else if (saisiExistes == 2) {
                System.out.println("Ok l'admin");
            }else {System.out.println("Choix non disponible");}
        }
        else {
            System.out.println("Desolé mais le fichier json n'existe !");
        }
    }

    @Override
    public void retirerAbonner() throws IOException{
        System.out.print("Veuillez entrez l'email de l'utilisateur à être retirer de l'abonnement");
        String emailUtilisteurRetirer = scanner.nextLine();
        if (fichierJson.exists() && fichierJson.length()>0){
            ListeEmploye listesEmployes = mapper.readValue((fichierJson), ListeEmploye.class);
            for(Employe liste : listesEmployes.getListesEmployes()){
                // je verifi si l'email existe dans le json
                if(liste.getEmail().equals(emailUtilisteurRetirer)){
                    liste.setEstAbonne(false);
                    System.out.println("l'abonné " + liste.getPrenom() + " "+ liste.getNom() +" a été retirer avec succès au service de notification");
                    mapper.writerWithDefaultPrettyPrinter().writeValue(fichierJson,listesEmployes);
                }
                // à corriger
                else {
                    System.out.println("aucun abonné trouvé qui a l'email " +emailUtilisteurRetirer );
                }
            }
                // si l'email n'est pas dans le json

        }else {System.out.println("le fichier est indiponible ou est vide");}
    }
    // La methode afficher la liste des abonnés
    public void afficherListeAbonner() throws IOException{
        ListeEmploye listesDesEmployesAAfficher = mapper.readValue(fichierJson,ListeEmploye.class);
        for(Employe liste : listesDesEmployesAAfficher.getListesEmployes()){
            //condition pour verifier si un employer est abonné ou pas
            if(liste.getEstAbonne() == true){
                System.out.println("prenom : " + liste.getPrenom() + " nom : " + liste.getNom() + " email : " + liste.getEmail());
            }
        }
        if(listesDesEmployesAAfficher.getListesEmployes() == null){
            System.out.println("Aucune personne n'est abonné a un service de notification");
        }

    }
}
