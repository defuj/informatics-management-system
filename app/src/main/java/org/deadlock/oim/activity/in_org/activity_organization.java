package org.deadlock.oim.activity.in_org;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;

import org.deadlock.oim.R;

import java.util.Objects;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class activity_organization extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization);
        getWindow().setAllowEnterTransitionOverlap(true);
        getWindow().setAllowReturnTransitionOverlap(true);

        ActionBar actionBar = getSupportActionBar();
        int black = getResources().getColor(R.color.white);
        Spannable spannable = new SpannableString("Organization");
        spannable.setSpan(new ForegroundColorSpan(black),0,spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new TypefaceSpan("poppins.ttf"),0,spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        Objects.requireNonNull(actionBar).setTitle(spannable);

    }
}
