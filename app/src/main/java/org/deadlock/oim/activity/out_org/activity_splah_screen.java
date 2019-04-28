package org.deadlock.oim.activity.out_org;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;

import org.deadlock.oim.R;
import org.deadlock.oim.data.data_session;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

public class activity_splah_screen extends AppCompatActivity {
    private boolean loggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences(data_session.SHARED_PREF_NAME, 0);
        loggedIn = sharedPreferences.getBoolean(data_session.LOGGEDIN_SHARED_PREF, false);

        if(loggedIn){
            startActivity(new Intent(activity_splah_screen.this, activity_home_group_03.class),
                    ActivityOptionsCompat.makeSceneTransitionAnimation(activity_splah_screen.this).toBundle());
        }else{
            setContentView(R.layout.activity_splah_screen);
            getWindow().setAllowEnterTransitionOverlap(true);
            getWindow().setAllowReturnTransitionOverlap(true);
            CekStatusLogin();
        }
    }

    private void CekStatusLogin() {
        new CountDownTimer(1000, 100) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                if(loggedIn){
                    startActivity(new Intent(activity_splah_screen.this, activity_home_group_03.class),
                            ActivityOptionsCompat.makeSceneTransitionAnimation(activity_splah_screen.this).toBundle());
                }else{
                    startActivity(new Intent(activity_splah_screen.this, activity_sign.class),
                            ActivityOptionsCompat.makeSceneTransitionAnimation(activity_splah_screen.this).toBundle());
                }
            }
        }.start();
    }
}
