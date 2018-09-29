package com.technocracy.app.aavartan.gallery.Model.Data;


public class Image {
    private int id;
    private String img_url,img_caption;

    public Image(int id, String img_url, String img_caption) {
        this.id = id;
        this.img_url = img_url;
        this.img_caption = img_caption;
    }

    public int getId() {
        return id;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getImg_caption() {
        return img_caption;
    }
}
