package com.technocracy.app.aavartan.activity.splash_screen.presenter;

import android.content.pm.PackageManager;

import com.technocracy.app.aavartan.activity.splash_screen.SplashScreenCallBack;
import com.technocracy.app.aavartan.activity.splash_screen.models.SplashScreenProvider;
import com.technocracy.app.aavartan.activity.splash_screen.models.data.SplashScreenData;
import com.technocracy.app.aavartan.activity.splash_screen.view.SplashScreenView;


public class SplashScreenPresenterImpl implements SplashScreenPresenter {

    private static final String LOG_TAG = "SplashScreenPresenter";
    private SplashScreenView splashScreenView;
    private SplashScreenProvider splashScreenProvider;

    public SplashScreenPresenterImpl(SplashScreenView splashScreenView, SplashScreenProvider splashScreenProvider) {
        this.splashScreenView = splashScreenView;
        this.splashScreenProvider = splashScreenProvider;
    }

    @Override
    public void requestSplash() {
        splashScreenView.showProgressBar(true);

        splashScreenProvider.requestSplash(new SplashScreenCallBack() {
            @Override
            public void onSuccess(SplashScreenData splashScreenData) throws PackageManager.NameNotFoundException {
                if (splashScreenData.isSuccess()) {
                    splashScreenView.onVersionReceived(splashScreenData);
                    splashScreenView.showProgressBar(false);
                } else {
                    splashScreenView.showProgressBar(false);
                    splashScreenView.showMessage(splashScreenData.getMessage());
                    splashScreenView.onFailed();
                }
            }

            @Override
            public void onFailure(String error) {
                splashScreenView.showProgressBar(false);
//                splashScreenView.showDialog(error);
                splashScreenView.onFailed();
            }
        });

    }
}
