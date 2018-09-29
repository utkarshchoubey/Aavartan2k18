package com.technocracy.app.aavartan.helper;

/**
 * Created by nsn on 10/13/2015.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;
    int check = 0;
    // Shared preferences file name
    private static final String PREF_NAME = "AavartanLogin";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_SPONSORS = "sponsorsData";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        // commit changes
        editor.commit();
        Log.d(TAG, "User login session modified!");
    }

    public void saveSponsorsData(String sponsor) {
        editor.putString(KEY_SPONSORS, sponsor);
        // commit changes
        editor.commit();
        Log.d(TAG, "Sponsors saved!");
    }

    public void deleteSponsorsData(){
        editor.remove(KEY_SPONSORS).commit();
    }

    public String getSponsorsData(){
        return pref.getString(KEY_SPONSORS,null);
    }

    public boolean isLoggedIn() {
        check = 1;
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public int check() {
        return check;
    }
}