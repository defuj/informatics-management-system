package org.deadlock.oim.activity.out_org;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import com.androidquery.AQuery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("Registered")
public class BaseApp extends AppCompatActivity {
    public Context context;
    public AQuery aQuery;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        aQuery = new AQuery(context);
    }
}
