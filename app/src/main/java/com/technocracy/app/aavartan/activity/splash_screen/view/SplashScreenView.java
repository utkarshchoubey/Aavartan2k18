package com.technocracy.app.aavartan.activity.splash_screen.view;

import android.content.pm.PackageManager;

import com.technocracy.app.aavartan.activity.splash_screen.models.data.SplashScreenData;



public interface SplashScreenView {

    void showMessage(String message);

    void showProgressBar(boolean show);

    void onVersionReceived(SplashScreenData splashScreenData) throws PackageManager.NameNotFoundException;

    void onFailed();

    void showDialog(String message);
}
