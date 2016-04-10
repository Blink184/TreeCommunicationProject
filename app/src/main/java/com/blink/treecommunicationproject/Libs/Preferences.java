package com.blink.treecommunicationproject.Libs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * COPY PASTED BY AYNUR 4/10/2016
 */
public class Preferences {

    public static String USER = "USER";
    public static String ISLOGGEDIN = "ISLOGGEDIN";
    public static String PROFILEPICTURE = "PROFILEPICTURE";

    public static String TRACKERENABLED = "TRACKERENABLED";

    public static String NOTIFICATIONENABLED = "NOTIFICATIONENABLED";
    public static String MAXNOTIFICATIONDISTANCE = "MAXNOTIFICATIONDISTANCE";
    public static String NOTIFICATIONTIMEINTERVAL  = "NOTIFICATIONTIMEINTERVAL";

    public static String COM = "com.ahmadhammoud.fragments";
    private SharedPreferences prefs;

    public Preferences(Context context){
        prefs = context.getSharedPreferences(COM, Context.MODE_PRIVATE);
    }

    public String getString(String key){
        return prefs.getString(COM + "." + key, "0");
    }
    public boolean getBoolean(String key){
        return prefs.getBoolean(COM + "." + key, false);
    }
    public void putString(String key, String value){
        prefs.edit().putString(COM + "." + key, value).apply();
    }
    public void putBoolean(String key, boolean value){
        prefs.edit().putBoolean(COM + "." + key, value).apply();
    }
}
