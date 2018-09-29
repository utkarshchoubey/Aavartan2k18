package com.technocracy.app.aavartan.gallery.Model;


import android.os.Handler;

import java.util.ArrayList;

import com.technocracy.app.aavartan.gallery.GalleryCallback;
import com.technocracy.app.aavartan.gallery.Model.Data.GalleryData;
import com.technocracy.app.aavartan.gallery.Model.Data.Image;

public class MockGalleryProvider implements GalleryProvider {


    private GalleryData mockGalleryData;

    @Override
    public void getImages(final GalleryCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockGalleryData());
            }
        }, 500);
    }

    public GalleryData getMockGalleryData() {
        ArrayList<Image> images = new ArrayList<>();
        images.add(new Image(1, "http://www.aavartan.org/img/thumb-04.jpg", "AAVARTAN 2017"));
        images.add(new Image(2, "http://www.aavartan.org/img/thumb-08.jpg", "AAVARTAN 2017"));
        images.add(new Image(3, "http://www.technocracynitrr.org/assets/img/2.jpg", "AAVARTAN 2017"));
        images.add(new Image(4, "https://i.ytimg.com/vi/gmAa0xP3N0E/hqdefault.jpg", "AAVARTAN 2017"));
        images.add(new Image(5, "http://www.technocracynitrr.org/assets/images/pic2.png", "AAVARTAN 2017"));
        images.add(new Image(6, "http://www.aavartan.org/img/thumb-04.jpg", "AAVARTAN 2017"));
        images.add(new Image(7, "http://www.aavartan.org/img/thumb-11.jpg", "AAVARTAN 2017"));
        images.add(new Image(8, "https://i.ytimg.com/vi/gmAa0xP3N0E/hqdefault.jpg", "AAVARTAN 2017"));
        images.add(new Image(9, "http://www.technocracynitrr.org/assets/images/pic2.png", "AAVARTAN 2017"));
        images.add(new Image(10, "http://www.aavartan.org/img/thumb-04.jpg", "AAVARTAN 2017"));
        mockGalleryData = new GalleryData(true, "Success", images);
        return mockGalleryData;
    }
}