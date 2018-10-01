package com.technocracy.app.aavartan.helper;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ProgressBar;

import com.technocracy.app.aavartan.R;

public class App {
    //EndPoints
    public static final String REGISTER_FCM_GENERAL_TOKEN_URL = "http://aavartan.org:8000/appApi_2Ksixteen/saveGeneralFCMToken.php";
    public static final String REGISTER_FCM_TOKEN_URL = "http://aavartan.org:8000/appApi_2Ksixteen/registerFCMToken.php";

    public static final String REGISTER_USER_URL = "http://aavartan.org:8000/appApi_2Ksixteen/register.php";
    public static final String LOGIN_URL = "http://aavartan.org:8000/appApi_2Ksixteen/login.php";
    public static final String FORGOT_PASSWORD_URL = "http://aavartan.org:8000/appApi_2Ksixteen/forgetPassword.php";
    public static final String ATTRACTIONS_URL = "app-android-attractions";

    //Vigyaan
    public static final String ArchiPDF = "http://aavartan.org/pages/vigyaan/archi.pdf";
    public static final String BioMedPDF = "https://aavartan.org/pages/vigyaan/biomed.pdf";
    public static final String BioTechPDF = "https://aavartan.org/pages/vigyaan/biotech.pdf";
    public static final String ChemPDF = "https://aavartan.org/pages/vigyaan/chemical.pdf";
    public static final String CivilPDF = "https://aavartan.org/pages/vigyaan/civil.pdf";
    public static final String CSEPDF = "https://aavartan.org/pages/vigyaan/cse.pdf";
    public static final String ElecPDF = "https://aavartan.org/pages/vigyaan/electrical.pdf";
    public static final String ElexPDF = "https://aavartan.org/pages/vigyaan/electronic.pdf";
    public static final String ITPDF = "https://aavartan.org/pages/vigyaan/it.pdf";
    public static final String MechPDF = "https://aavartan.org/pages/vigyaan/mech.pdf";
    public static final String MetaPDF = "https://aavartan.org/pages/vigyaan/meta.pdf";
    public static final String MiningPDF = "https://aavartan.org/pages/vigyaan/mining.pdf";
    public static final String MCAPDF = "https://aavartan.org/pages/vigyaan/mca.pdf";
    public static final String GoGreenPDF = "https://aavartan.org/pages/vigyaan/GoGreen.pdf";


    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;
    public static final int SWIPE_REFRESH_COLORS[] = {R.color.colorPrimary, R.color.colorAccent};
    public static final String GALLERY = "app-android-gallery";
    public static final String SCHEDULE1 = "app-android-schedule/7";
    public static final String CONTACT = "app-android-contacts";
    public static final String SCHEDULE2 = "app-android-schedule/8";
    public static final String FUN_EVENT = "app-android-events/1";
    public static final String MANAGERIAL_EVENT = "app-android-events/2";
    public static final String TECHNICAL_EVENT = "app-android-events/3";
    public static final String ROBOTICS_EVENT = "app-android-events/4";
    public static final String SPONSORS_URL = "app-android-sponsors";
    public static final String EVENT_REGISTER = "app-android-event-register";
    public static final String CONTACT_APP = "app-android-android-team";
    public static final String REGISTERED_EVENTS_URL = "http://aavartan.org:8000/app-android-registered-events/";
    public static final String FORGOT_PASSWORD_URL_1 = "http://aavartan.org:8000/app-android-forgot";
    public static final String NOTIFICATION_URL = "http://aavartan.org:8000/app-android-notifications";
    public static String LinkPDF;
    public static String Base_Url = "http://aavartan.org:8000/";

    public static float getScreenWidth(Context context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }

    public static void showProgressBar(ProgressBar progressBar) {
        if (progressBar.getVisibility() != View.VISIBLE)
            progressBar.setVisibility(View.VISIBLE);
    }

    public static void hideProgressBar(ProgressBar progressBar) {
        if (progressBar.getVisibility() != View.GONE
                || progressBar.getVisibility() != View.INVISIBLE)
            progressBar.setVisibility(View.GONE);
    }
}