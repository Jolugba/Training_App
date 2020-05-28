package com.tinuade.nougat_home_teach.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharePref {

    public static Context context;
    private static SharePref INSTANCE;
    private static SharedPreferences sharedPreferences;

    private SharePref(SharedPreferences sharedPreferences) {
        SharePref.sharedPreferences = sharedPreferences;

    }

    public SharePref(Context context) {
        SharePref.context = context;
    }

    public static synchronized SharePref getINSTANCE(Context context) {
        if (INSTANCE == null) {

            INSTANCE = new SharePref(PreferenceManager.getDefaultSharedPreferences(context));
        }
        return INSTANCE;
    }


    public void setBooleanData(String key, boolean data) {
        sharedPreferences.edit().putBoolean(key, data).apply();
    }

    public void setStringData(String key, String data) {
        sharedPreferences.edit().putString(key, data).apply();
    }

    public String getStringData(String key) {
        return sharedPreferences.getString(key, "");
    }

    public boolean getBooleanData(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public SharePref getSharePref() {
        return INSTANCE;
    }


}
