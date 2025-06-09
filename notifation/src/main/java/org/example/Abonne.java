package org.example;
import java.io.IOException;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;

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
        Scanner scanner = new Scanner(System.in);
        File fichierJson = new File("lesEmployes.json");
        ObjectMapper mapper = new ObjectMapper();
        //JE verifi si le fichier existe
        if(fichierJson.exists() && fichierJson.length() > 0){
            System.out.println("Entrer votre email :");
            String email = scanner.nextLine();
            System.out.println("Entrer votre mot de passe :");
            String password = scanner.nextLine();
            ListeEmploye listeEmploye = mapper.readValue(fichierJson,ListeEmploye.class);
                String objets="";
                String messages="";
                for(Employe liste : listeEmploye.getListesEmployes()) {
                    if (liste.getEmail().equals(email) && liste.getPassword().equals(password)) {
                        if (liste.getEstAbonne() == true) {
                            System.out.println("Entrer l'objet de la notification :");
                            objets = scanner.nextLine();
                            System.out.println("Entrer le message :");
                            messages = scanner.nextLine();
                        }
                    }
                    //condition pour l'email et le mot de passe n'est pas correct !
                    else{
                        System.out.println("l'email ou mot de passe incorrect");
                    }
                }
                Notification notification = new Notification(objets,messages);
                List<Notification> mesNotifications = new ArrayList<>();
                mesNotifications.add(notification);
                for(Employe listes : listeEmploye.getListesEmployes()){
                    if(listes.getEstAbonne() == true){
                        listes.setListesNotification(mesNotifications);
                        mapper.writerWithDefaultPrettyPrinter().writeValue(fichierJson, listeEmploye);
                        System.out.println("Notification envoyé à " + listes.getNom() +" à son addresse "+ listes.getEmail());
                    }
                }

        }else{
            System.out.println("Desolé mais le fichier n'existe pas");
        }


    }
}
