package com.exemple.fdatabase.models;

import java.sql.Blob;
//
//public class Pic {
//        private String name;
//        private Blob image;
//
//    public Pic(String name, Blob image) {
//        this.name = name;
//        this.image = image;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Blob getImage() {
//        return image;
//    }
//
//    public void setImage(Blob image) {
//        this.image = image;
//    }
//}
public class Pic {
    private String name;
    private  int id;
    private byte[] image; // Changed from Blob to byte[]

    public Pic(String name, byte[] image) {
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pic() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    // Getters and setters remain the same
}
