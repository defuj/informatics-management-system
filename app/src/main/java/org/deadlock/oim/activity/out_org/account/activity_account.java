package org.deadlock.oim.activity.out_org.account;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import org.deadlock.oim.R;
import org.deadlock.oim.data.data_session;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class activity_account extends AppCompatActivity {

    TextView txtNama,txtBio,txtEmail,txtVerify;
    Button btnVerify;
    SimpleDraweeView imgAccount;
    ImageButton btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        getWindow().setAllowEnterTransitionOverlap(true);
        getWindow().setAllowReturnTransitionOverlap(true);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loadInformations();
    }

    @SuppressLint("SetTextI18n")
    public void loadInformations(){
        imgAccount = findViewById(R.id.img_account);
        btnVerify = findViewById(R.id.btnVerify);
        btnEdit = findViewById(R.id.btn_edit);

        txtNama = findViewById(R.id.txt_name);
        txtBio = findViewById(R.id.txt_bio);
        txtEmail = findViewById(R.id.txt_email);
        txtVerify = findViewById(R.id.txt_verify_status);

        SharedPreferences sharedPreferences = getSharedPreferences(data_session.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        txtNama.setText(sharedPreferences.getString(data_session.NAMA,"Not Available"));
        txtEmail.setText(sharedPreferences.getString(data_session.EMAIL,"Not Available"));
        imgAccount.setImageURI(Uri.parse(String.valueOf(sharedPreferences.getString(data_session.FOTO,"Not Available"))));
        txtVerify.setText("Account is not yet verified");

        imgAccount.setVisibility(View.VISIBLE);
        txtNama.setVisibility(View.VISIBLE);
        txtBio.setVisibility(View.VISIBLE);
        txtEmail.setVisibility(View.VISIBLE);
        txtVerify.setVisibility(View.VISIBLE);
        btnVerify.setVisibility(View.VISIBLE);

        buttonClicked();
    }

    public void buttonClicked(){
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog pd = new ProgressDialog(activity_account.this,R.style.alertDialogTheme);
                pd.setProgressStyle(R.style.Widget_AppCompat_ProgressBar);
                pd.setCancelable(false);
                pd.show();

                new CountDownTimer(2000, 1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        pd.dismiss();
                        btnVerify.setVisibility(View.GONE);
                        txtVerify.setVisibility(View.GONE);
                    }
                }.start();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog pd = new ProgressDialog(activity_account.this,R.style.alertDialogTheme);
                pd.setProgressStyle(R.style.Widget_AppCompat_ProgressBar);
                pd.setCancelable(false);
                pd.show();

                new CountDownTimer(2000, 1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        pd.dismiss();
                        Toast.makeText(activity_account.this, "This function is under development.", Toast.LENGTH_SHORT).show();
                    }
                }.start();
            }
        });
    }
}
