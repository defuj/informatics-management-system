package org.deadlock.oim.network;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.deadlock.oim.activity.out_org.BaseApp;

@SuppressLint("Registered")
public class net extends BaseApp {

    public boolean status(){
        boolean connect;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connect = true;
        }
        else {
            connect = false;
            Toast.makeText(this,"no connection", Toast.LENGTH_SHORT).show();
        }
        return connect;
    }
}
