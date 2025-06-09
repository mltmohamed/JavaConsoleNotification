package org.example;

import java.util.ArrayList;
import java.util.List;

public class Employe {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private boolean estAbonne = false;
    List<Notification> listesNotification = new ArrayList<>();
    public Employe(){}
    public Employe(String nom, String prenom, String email, String password){
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }
    // les getters

    public List<Notification> getNotification() {return listesNotification;}

    public void setListesNotification(List<Notification> listesNotification) {
        this.listesNotification = listesNotification;
    }

    public String getEmail() {return email;}

    public String getNom() {return nom;}

    public String getPrenom() {return prenom;}

    public String getPassword() {return password;}

    public boolean getEstAbonne() {return estAbonne;}
    // les setters
    public void setEmail(String email) {this.email = email;}

    public void setEstAbonne(boolean estAbonne) {this.estAbonne = estAbonne;}

    public void setNom(String nom) {this.nom = nom;}

    public void setPassword(String password) {this.password = password;}

    public void setPrenom(String prenom) {this.prenom = prenom;}
    // La methode s'abonner à un service de notification
    public void abonnerServicedeNotification(){
        this.estAbonne = true;
    }
}
