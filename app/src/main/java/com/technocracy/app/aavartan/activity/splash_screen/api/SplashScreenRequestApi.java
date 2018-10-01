package com.technocracy.app.aavartan.activity.splash_screen.api;

import com.technocracy.app.aavartan.activity.splash_screen.models.data.SplashScreenData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface SplashScreenRequestApi {
    @GET("splash_screen/")
    Call<SplashScreenData> requestSplash();

}

