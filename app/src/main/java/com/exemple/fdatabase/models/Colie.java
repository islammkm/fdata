package com.exemple.fdatabase.models;

public class Colie {
    private String date , type , last_position , code,LastPosition , city , etat , prix ,  nome, photo;
    private int cote;
    private int poids;
    private int id;
    private int CmndId;
    private int FournisseurId;
    private int IdPath;

    public int getIdPath() {
        return IdPath;
    }

    public void setIdPath(int idPath) {
        IdPath = idPath;
    }

    public int getIdfournisseur() {
        return idfournisseur;
    }

    public void setIdfournisseur(int idfournisseur) {
        this.idfournisseur = idfournisseur;
    }

    private int idfournisseur;

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

    public String getCity() {
        return city;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setCity(String city) {
        this.city = city;
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
