package com.exemple.fdatabase.models;

public class Colie {
    private String date , type , last_position , code,LastPosition;
    private int cote , poids ,id,CmndId , FournisseurId;

    public Colie(int anInt, int cursorInt, int i, int anInt1, String date, String type, String last_position, String code, int cote, int poids) {
        this.date = date;
        this.type = type;
        this.last_position = last_position;
        this.code = code;
        this.cote = cote;
        this.poids = poids;
    }

    public Colie() {
    }

    public String getLastPosition() {
        return last_position;
    }

    public void setLastPosition(String lastPosition) {
        last_position = lastPosition;
    }

    public int getFournisseurId() {
        return FournisseurId;
    }

    public void setFournisseurId(int fournisseurId) {
        FournisseurId = fournisseurId;
    }

    public int getCmndId() {
        return CmndId;
    }

    public void setCmndId(int cmndId) {
        CmndId = cmndId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLast_position() {
        return last_position;
    }

    public void setLast_position(String last_position) {
        this.last_position = last_position;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCote() {
        return cote;
    }

    public void setCote(int cote) {
        this.cote = cote;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
