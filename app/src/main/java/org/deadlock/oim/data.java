package org.deadlock.oim;

import android.content.Context;
import android.widget.Toast;

import org.deadlock.oim.activity.BaseApp;

public class data extends BaseApp {
    public static final String EMAIL = "email";
    public static final String NAMA = "nama";
    public static final String FOTO = "foto";
    public static final String LOGIN = "login";
    public static final String SHARED_PREF_NAME = "login";
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";

    public static String BASE_URL = "http://192.168.1.1/animelist/";

    public static void pesan (Context c, String msg){
        Toast.makeText(c,msg, Toast.LENGTH_LONG).show();
    }
}
