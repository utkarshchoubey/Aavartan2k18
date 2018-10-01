package com.technocracy.app.aavartan.activity.splash_screen.models;

import android.content.pm.PackageManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.technocracy.app.aavartan.activity.splash_screen.SplashScreenCallBack;
import com.technocracy.app.aavartan.activity.splash_screen.api.SplashScreenRequestApi;
import com.technocracy.app.aavartan.activity.splash_screen.models.data.SplashScreenData;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class RetrofitSplashScreenProvider implements  SplashScreenProvider{

    public static final String BASE_URL = "http://18.220.64.65:7999/";
    private static final String TAG = "RetrofitSplashScreen";
    private SplashScreenRequestApi splashScreenRequestApi;
    Call<SplashScreenData> call;

    public RetrofitSplashScreenProvider() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        this.splashScreenRequestApi = retrofit.create(SplashScreenRequestApi.class);
    }

    @Override
    public void requestSplash(final SplashScreenCallBack splashScreenCallback) {

        call = splashScreenRequestApi.requestSplash();
        call.enqueue(new Callback<SplashScreenData>() {
            @Override
            public void onResponse(Call<SplashScreenData> call, Response<SplashScreenData> response) {
                try {
                    splashScreenCallback.onSuccess(response.body());
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SplashScreenData> call, Throwable t) {
                t.printStackTrace();
                splashScreenCallback.onFailure("No Internet Connection");
            }
        });

    }
}
