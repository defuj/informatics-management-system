package org.deadlock.oim.activity.out_org;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.util.Log;

import com.google.zxing.Result;

import org.deadlock.oim.R;

import java.util.Objects;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class activity_scan_qr extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_scan_qr);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        getWindow().setAllowEnterTransitionOverlap(true);
        getWindow().setAllowReturnTransitionOverlap(true);

        /*getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Scan QR Code");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));*/

        ActionBar actionBar = getSupportActionBar();
        int black = getResources().getColor(R.color.black_80_transparent);
        Spannable spannable = new SpannableString("Scan QR Code");
        spannable.setSpan(new ForegroundColorSpan(black),0,spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new TypefaceSpan("poppins.ttf"),0,spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        Objects.requireNonNull(actionBar).setTitle(spannable);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
    }

    @Override
    public void handleResult(Result result) {
        Log.v("TAG",result.getText());
        Log.v("TAG",result.getBarcodeFormat().toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        mScannerView.resumeCameraPreview(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
