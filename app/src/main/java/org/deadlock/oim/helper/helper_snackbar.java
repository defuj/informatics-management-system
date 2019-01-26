package org.deadlock.oim.helper;

import android.content.Context;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import org.deadlock.oim.R;

import androidx.core.view.ViewCompat;

public class helper_snackbar {
    public static void configSnackbar(Context context, Snackbar snack){
        addMargins(snack);
        setRoundBordersBg(context,snack);
        ViewCompat.setElevation(snack.getView(),6f);
    }

    private static void addMargins(Snackbar snack){
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)snack.getView().getLayoutParams();
        params.setMargins(12,12,12,24);
        snack.getView().setLayoutParams(params);
    }

    private static void setRoundBordersBg(Context context, Snackbar snackbar){
        snackbar.getView().setBackground(context.getDrawable(R.drawable.snackbar));
    }
}
