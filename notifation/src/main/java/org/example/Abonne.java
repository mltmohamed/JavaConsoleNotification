package org.example;
import java.io.IOException;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class Abonne extends Employe implements Message{

    @Override
    public void setEstAbonne(boolean estAbonne) {super.setEstAbonne(true);}

    public Abonne(String nom, String prenom, String email, String password) {
        super(nom, prenom, email, password);
    }


    // La methode se desabonner
    public void desabonnerServiceNotification(){
       super.setEstAbonne(false);
    }

    // Les methodes de Message
    @Override
    public void EnvoyerEmail(String objetEmail, String contenuEmail) throws IOException{

    }
    @Override
    public void EnvoyerNotification() throws IOException{
        File fichierJson = new File("lesEmployes.json");
        ObjectMapper mapper = new ObjectMapper();
        if(fichierJson.exists()){
            ListeEmploye listedesEmployes = mapper.readValue(fichierJson, ListeEmploye.class);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Entrer votre email");
            String email = scanner.nextLine();

            System.out.println("Entrer votre mot de passe");
            String password = scanner.nextLine();
            for(Employe liste : listedesEmployes.getListesEmployes()){
                if(liste.getEmail().equals(email) && liste.getPassword().equals(password)){
                    if(liste.getIsEstAbonne() == true){
                        System.out.println("Entrer l'objet du message");
                        String objets = scanner.nextLine();
                        System.out.println("Entrer le contenu du message");
                        String contenus = scanner.nextLine();
                        Notification notification = new Notification(objets, contenus);
                        for(Employe listes : listedesEmployes.getListesEmployes()){
                            if(listes.getEmail().equals(email)){
                                continue;
                            }; // si la liste est null
                            if(listes.getNotification() ==null){
                                List<Notification> listeNotification = new ArrayList<Notification>();
                                listeNotification.add(notification);
                                listes.setListesNotification(listeNotification);
                                mapper.writerWithDefaultPrettyPrinter().writeValue(fichierJson,listedesEmployes);

                            } // si la liste contient des elements
                            if(listes.getNotification() !=null){
                                listes.getNotification().add(notification);
                                mapper.writerWithDefaultPrettyPrinter().writeValue(fichierJson,listedesEmployes);
                            }
                            List<Notification> listeNotification = new ArrayList<Notification>();
                            listeNotification.add(notification);
                            listes.setListesNotification(listesNotification);
                        }
                    }
                    else if (liste.getIsEstAbonne() == false) {
                        System.out.println("Desolé mais vous n'etes pas abonné à un service de notification");
                    }
                }
                else {
                    System.out.println("Email ou mot de passe incorrect !");
                }
            }
        }
        else {
            System.out.println("Désolé le fichier n'existe pas");
        }

    }
}
