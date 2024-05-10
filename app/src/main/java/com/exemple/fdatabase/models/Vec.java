package com.exemple.fdatabase.models;

public class Vec {
    private String color , nom , date ;
    private int idlivreur,id;

    public Vec() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdlivreur() {
        return idlivreur;
    }

    public void setIdlivreur(int idlivreur) {
        this.idlivreur = idlivreur;
    }

    public Vec(String color, String nom, String date) {
        this.color = color;
        this.nom = nom;
        this.date = date;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
