package com.exemple.fdatabase.models;

public class CmndDetaols {
    private int cmndid;
    private String cityclient,fournisseur,site,datecmnd,nomcmnd;

    public int getCmndid() {
        return cmndid;
    }

    public void setCmndid(int cmndid) {
        this.cmndid = cmndid;
    }

    public String getCityclient() {
        return cityclient;
    }

    public void setCityclient(String cityclient) {
        this.cityclient = cityclient;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDatecmnd() {
        return datecmnd;
    }

    public void setDatecmnd(String datecmnd) {
        this.datecmnd = datecmnd;
    }

    public String getNomcmnd() {
        return nomcmnd;
    }

    public void setNomcmnd(String nomcmnd) {
        this.nomcmnd = nomcmnd;
    }
}
