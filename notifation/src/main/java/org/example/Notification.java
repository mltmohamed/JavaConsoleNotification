package org.example;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;
public class Notification {
    private String objet;
    private String contenu;

    public Notification(String objet, String contenu){
        this.objet = objet;
        this.contenu = contenu;
    }
    public Notification(){}

    public String getContenu() {return contenu;}

    public String getObjet() {return objet;}

    public void setObjet(String objet) {this.objet = objet;}

    public void setContenu(String contenu) {this.contenu = contenu;}

    //La Methode afficher les notifications reçues
    public void afficherNotificationsReçus() throws IOException {
        File fichierJson = new File("lesEmployes.json");
        ObjectMapper mapper = new ObjectMapper();
        if(fichierJson.exists()){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Entrer votre email : ");
            String email = scanner.nextLine();
            System.out.print("Entrer votre mot de passe  : ");
            String password = scanner.nextLine();
            ListeEmploye listesEmploye = mapper.readValue(fichierJson,ListeEmploye.class);
            for(Employe liste : listesEmploye.getListesEmployes()){
                if((liste.getEmail()).equals(email) && (liste.getPassword()).equals(password)){
                    if(liste.getNotification()==null){
                        System.out.print("Aucune notification disponible");
                    }
                    if(liste.getNotification() != null){
                        System.out.print("Voici vos notifications : " + liste.getNotification());
                    }
                }
            }
        }else{
            System.out.print("Fichier introuvable");
        }

    }
}
