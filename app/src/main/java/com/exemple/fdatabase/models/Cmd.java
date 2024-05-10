package com.exemple.fdatabase.models;

public class Cmd {
    private String adresse ,etat ;
    private int cote , Nphone,userid,LivreurId;
    private int id;
    public Cmd() {
    }

    public Cmd(String adresse, String etat, int cote, int nphone) {
        this.adresse = adresse;
        this.etat = etat;
        this.cote = cote;
        Nphone = nphone;
    }

    public int getLivreurId() {
        return LivreurId;
    }

    public void setLivreurId(int livreurId) {
        LivreurId = livreurId;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getCote() {
        return cote;
    }

    public void setCote(int cote) {
        this.cote = cote;
    }

    public int getNphone() {
        return Nphone;
    }

    public void setNphone(int nphone) {
        Nphone = nphone;
    }

}

