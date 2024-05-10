package com.exemple.fdatabase.models;

public class Livreur {
    private String nom,username,password;
    int id;
    private String RM , NM;

    public Livreur() {
    }

    public Livreur(String nom, String RM, String NM, String password, String username) {
        this.nom = nom;
        this.RM = RM;
        this.NM = NM;
        this.password=password;
        this.username=username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRM() {
        return RM;
    }

    public void setRM(String RM) {
        this.RM = RM;
    }

    public String getNM() {
        return NM;
    }

    public void setNM(String NM) {
        this.NM = NM;
    }
}
