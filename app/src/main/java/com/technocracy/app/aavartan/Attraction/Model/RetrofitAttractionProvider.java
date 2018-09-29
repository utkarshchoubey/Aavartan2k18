package com.technocracy.app.aavartan.Attraction.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.technocracy.app.aavartan.Attraction.Api.AttractionApi;
import com.technocracy.app.aavartan.Attraction.AttractionCallback;
import com.technocracy.app.aavartan.Attraction.Model.Data.AttractionData;
import com.technocracy.app.aavartan.helper.App;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAttractionProvider implements AttractionProvider {

    private final Retrofit retrofit;
    private AttractionApi api;

    public RetrofitAttractionProvider() {
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
    public void getAttractions(final AttractionCallback callback) {
        api = retrofit.create(AttractionApi.class);
        retrofit2.Call<AttractionData> call = api.getAttraction();
        call.enqueue(new retrofit2.Callback<AttractionData>() {
            @Override
            public void onResponse(Call<AttractionData> call, Response<AttractionData> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<AttractionData> call, Throwable t) {
                callback.onFailure();
                t.printStackTrace();
            }
        });
    }
}
