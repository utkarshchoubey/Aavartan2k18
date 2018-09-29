package com.technocracy.app.aavartan.gallery.Model;


import com.technocracy.app.aavartan.gallery.GalleryCallback;

public interface GalleryProvider {
    void getImages(GalleryCallback callback);
}
