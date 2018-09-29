package com.technocracy.app.aavartan.gallery.Api;


import com.technocracy.app.aavartan.gallery.Model.Data.GalleryData;
import com.technocracy.app.aavartan.helper.App;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GalleryApi {
    @GET(App.GALLERY)
    Call<GalleryData> getImages();
}
