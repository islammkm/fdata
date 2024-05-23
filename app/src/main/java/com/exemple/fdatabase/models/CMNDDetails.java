package com.exemple.fdatabase.models;

public class CMNDDetails {
    String cityf , cityc , site , datecmnd ,nomcmnd;
    int id , cmndid;

    public CMNDDetails(String cityf, String cityc, String site, String datecmnd, String nomcmnd, int cmndid) {
        this.cityf = cityf;
        this.cityc = cityc;
        this.site = site;
        this.datecmnd = datecmnd;
        this.nomcmnd = nomcmnd;
        this.cmndid = cmndid;
    }

    public CMNDDetails() {
    }

    public String getCityf() {
        return cityf;
    }

    public String getCityc() {
        return cityc;
    }

    public void setCityc(String cityc) {
        this.cityc = cityc;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCmndid() {
        return cmndid;
    }

    public void setCmndid(int cmndid) {
        this.cmndid = cmndid;
    }

    public void setCityf(String cityf) {
        this.cityf = cityf;
    }
}
