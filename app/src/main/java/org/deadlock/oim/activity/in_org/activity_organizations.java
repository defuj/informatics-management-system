package org.deadlock.oim.activity.in_org;

import android.os.Bundle;

import org.deadlock.oim.R;

import androidx.appcompat.app.AppCompatActivity;

public class activity_organizations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizations);

        getWindow().setAllowEnterTransitionOverlap(true);
        getWindow().setAllowReturnTransitionOverlap(true);
    }
}
