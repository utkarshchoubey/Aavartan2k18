package com.technocracy.app.aavartan.Sponsors.Model.Data;

public class Sponsor {
    private String type,name,image_url;

    public Sponsor(String type, String name, String image_url) {
        this.type = type;
        this.name = name;
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getImage_url() {
        return image_url;
    }
}
