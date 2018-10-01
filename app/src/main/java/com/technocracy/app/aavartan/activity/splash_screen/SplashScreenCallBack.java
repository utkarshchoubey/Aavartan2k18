package com.technocracy.app.aavartan.activity.splash_screen;

import android.content.pm.PackageManager;

import com.technocracy.app.aavartan.activity.splash_screen.models.data.SplashScreenData;

public interface SplashScreenCallBack {

    void onSuccess(SplashScreenData splashScreenData) throws PackageManager.NameNotFoundException;

    void onFailure(String error);
}