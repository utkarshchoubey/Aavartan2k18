package com.technocracy.app.aavartan.gallery;

import com.technocracy.app.aavartan.gallery.Model.Data.GalleryData;

public interface GalleryCallback {

    public void onSuccess(GalleryData body);

    void onFailure();
}
