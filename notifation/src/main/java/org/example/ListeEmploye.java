package org.example;

import java.util.List;

public class ListeEmploye {
    List<Employe> listesEmployes;
    public ListeEmploye(){}
    public ListeEmploye(List<Employe> listesEmployes){
        this.listesEmployes = listesEmployes;
    }
    // Le setter
    public void setListesEmployes(List<Employe> listesEmployes) {
        this.listesEmployes = listesEmployes;
    }
    // le getter
    public List<Employe> getListesEmployes() {return listesEmployes;}
}
