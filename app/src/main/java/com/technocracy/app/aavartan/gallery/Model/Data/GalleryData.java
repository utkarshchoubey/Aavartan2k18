package com.technocracy.app.aavartan.gallery.Model.Data;


import java.util.List;

public class GalleryData {
    private boolean success;
    private String message;
    private List<Image> imagelist;

    public GalleryData(boolean success, String message, List<Image> imagelist) {
        this.success = success;
        this.message = message;
        this.imagelist = imagelist;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Image> getImageList() {
        return imagelist;
    }
}
