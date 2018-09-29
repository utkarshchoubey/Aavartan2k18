package com.technocracy.app.aavartan.gallery.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.technocracy.app.aavartan.gallery.Api.GalleryApi;
import com.technocracy.app.aavartan.gallery.GalleryCallback;
import com.technocracy.app.aavartan.gallery.Model.Data.GalleryData;
import com.technocracy.app.aavartan.helper.App;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitGalleryProvider implements GalleryProvider {
    private final Retrofit retrofit;
    private GalleryApi galleryApi;

    public RetrofitGalleryProvider() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).
                readTimeout(60, TimeUnit.SECONDS).
                connectTimeout(60, TimeUnit.SECONDS).
                build();
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(App.Base_Url).
                addConverterFactory(GsonConverterFactory.create(gson)).client(client).build();

    }

    @Override
    public void getImages(final GalleryCallback callback) {
        galleryApi = retrofit.create(GalleryApi.class);
        Call<GalleryData> call = galleryApi.getImages();
        call.enqueue(new Callback<GalleryData>() {
            @Override
            public void onResponse(Call<GalleryData> call, Response<GalleryData> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<GalleryData> call, Throwable t) {
                callback.onFailure();
                t.printStackTrace();
            }
        });
    }
}