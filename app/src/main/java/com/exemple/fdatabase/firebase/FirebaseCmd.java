package com.exemple.fdatabase.firebase;

public class FirebaseCmd {
    private int userId;
    private int livreurId;
    private String adresse;
    private String etat;
    private int cote,nphone;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLivreurId() {
        return livreurId;
    }

    public void setLivreurId(int livreurId) {
        this.livreurId = livreurId;
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

    public int getCoteCmd() {
        return cote;
    }

    public void setCoteCmd(int coteCmd) {
        this.cote = coteCmd;
    }

    public int getNphone() {
        return nphone;
    }

    public void setNphone(int nphone) {
        this.nphone = nphone;
    }


    // Add constructors, getters, setters as needed
}
