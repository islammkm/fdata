package com.exemple.fdatabase.models;

public class Fourniseur {
    private String Nom , adresse ;
    private int id;

    public Fourniseur() {
    }

    public Fourniseur(String nom, String adresse, int id) {
        Nom = nom;
        this.adresse = adresse;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fourniseur(String nom, String adresse) {
        Nom = nom;
        this.adresse = adresse;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
