package com.technocracy.app.aavartan.gallery.View;


import java.util.List;

import com.technocracy.app.aavartan.gallery.Model.Data.Image;

public interface GalleryView  {
    void showProgressBar(boolean b);

    void showMessage(String message);

    void loadGallery(List<Image> imageList);
}
