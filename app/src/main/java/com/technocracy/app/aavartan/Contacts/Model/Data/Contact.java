package com.technocracy.app.aavartan.Contacts.Model.Data;


public class Contact {
    private int id;
    private String name;
    private String designation;
    private String imageurl;
    private String facebookurl;

    public Contact(int id, String name, String designation,
                   String imageurl, String facebookurl) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.imageurl = imageurl;
        this.facebookurl = facebookurl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public String getImageUrl() {
        return imageurl;
    }

    public String getFacebookUrl() {
        return facebookurl;
    }
}
