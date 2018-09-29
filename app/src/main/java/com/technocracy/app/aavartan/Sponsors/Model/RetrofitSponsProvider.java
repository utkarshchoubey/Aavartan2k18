package com.technocracy.app.aavartan.Sponsors.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.technocracy.app.aavartan.Sponsors.Api.SponsorsApi;
import com.technocracy.app.aavartan.Sponsors.Model.Data.SponsData;
import com.technocracy.app.aavartan.Sponsors.SponsCallback;
import com.technocracy.app.aavartan.helper.App;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSponsProvider implements SponsProvider {
    private final Retrofit retrofit;
    private SponsorsApi api;

    public RetrofitSponsProvider() {
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
    public void getSpons(final SponsCallback callback) {
        api = retrofit.create(SponsorsApi.class);
        retrofit2.Call<SponsData> call = api.getSpons();
        call.enqueue(new retrofit2.Callback<SponsData>() {
            @Override
            public void onResponse(retrofit2.Call<SponsData> call, retrofit2.Response<SponsData> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(retrofit2.Call<SponsData> call, Throwable t) {
                callback.onFailure();
                t.printStackTrace();
            }
        });
    }
}
