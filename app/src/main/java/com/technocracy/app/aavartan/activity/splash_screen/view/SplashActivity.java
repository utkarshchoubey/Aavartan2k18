package com.technocracy.app.aavartan.activity.splash_screen.view;

import android.content.Intent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.technocracy.app.aavartan.R;

import com.crashlytics.android.Crashlytics;
import com.technocracy.app.aavartan.activity.splash_screen.models.RetrofitSplashScreenProvider;
import com.technocracy.app.aavartan.activity.splash_screen.models.data.SplashScreenData;
import com.technocracy.app.aavartan.activity.splash_screen.presenter.SplashScreenPresenterInter;
import com.technocracy.app.aavartan.activity.splash_screen.presenter.SplashScreenPresenterImpl;
import com.technocracy.app.aavartan.activity.WelcomeActivity;

public class SplashActivity extends AppCompatActivity  implements SplashScreenView{

    private Context context;
    private SplashScreenPresenterInter splashScreenPresenter;
    private Handler handler;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        initialise();
        Thread mythread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        splashScreenPresenter.requestSplash();
        mythread.start();
    }


    private void initialise(){
        context=this;
        splashScreenPresenter = new SplashScreenPresenterImpl(this,
                new RetrofitSplashScreenProvider());

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
//            progressBar.setVisibility(View.VISIBLE);
        }
        else{
         //   progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onVersionReceived(SplashScreenData splashScreenData) throws PackageManager.NameNotFoundException {

        if (getPackageManager().getPackageInfo(getPackageName(), 0).versionCode <
                splashScreenData.getVersion() && !splashScreenData.isCompulsory_update())
        {
            final AlertDialog ad = new AlertDialog.Builder(this)
                    .create();
            ad.setCancelable(false);
            ad.setTitle("App Update Available");
            Log.d("SPLASH1---", "No");
            ad.setMessage("Please update the app for better experience");
            ad.setButton(DialogInterface.BUTTON_POSITIVE, "Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();
                    final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }

                    finish();
                }
            });
            ad.setButton(DialogInterface.BUTTON_NEGATIVE, "Not Now", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();
                    Intent intent=new Intent(getApplicationContext(),WelcomeActivity.class);
                    startActivity(intent);
                    finish();

                }
            });
            ad.show();


        }
        else if (getPackageManager().getPackageInfo(getPackageName(), 0).versionCode <
                splashScreenData.getVersion() && splashScreenData.isCompulsory_update()) {
            Log.d("SPLASH2---","No");

            final AlertDialog ad = new AlertDialog.Builder(this)

                    .create();
            ad.setCancelable(false);
            ad.setTitle("App Update Available");
            ad.setMessage("This is a compulsory Update . Please Update the app to enjoy the game");
            ad.setButton(DialogInterface.BUTTON_POSITIVE, "Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();
                    final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }

                    finish();
                }
            });
            ad.show();
        }
        else
        {
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(getApplicationContext(),WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 300);

        }
    }

    @Override
    public void onFailed() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(),WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, 300);

    }

    @Override
    public void showDialog(String message) {
        final AlertDialog ad = new AlertDialog.Builder(this)
                .create();
        ad.setCancelable(false);
        ad.setTitle("No Internet Connection");
        ad.setMessage("Please connect to internet to use our app");
        ad.setButton(DialogInterface.BUTTON_POSITIVE, "Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                splashScreenPresenter.requestSplash();

            }
        });
        ad.show();
    }
}

