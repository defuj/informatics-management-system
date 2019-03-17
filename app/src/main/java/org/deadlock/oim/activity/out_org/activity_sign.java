package org.deadlock.oim.activity.out_org;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import org.deadlock.oim.R;
import org.deadlock.oim.data.data_session;
import org.deadlock.oim.network.net;

import java.util.Objects;

import androidx.core.app.ActivityOptionsCompat;

public class activity_sign extends BaseApp {

    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 12345;
    private String url;
    private net network;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        network = new net();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestId()
                .build();
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();

        SignInButton signInButton = this.findViewById(R.id.sign_in_button);

        TextView textView= (TextView) signInButton.getChildAt(0);
        textView.setText("Sign In");

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginGoogleAccount();
            }
        });

    }

    public void LoginGoogleAccount() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("RESPON", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {

            GoogleSignInAccount acct = result.getSignInAccount();
            Log.e("SUKSES", "display name: " + Objects.requireNonNull(acct).getDisplayName());
            String nama = acct.getDisplayName();
            String email = acct.getEmail();
            if (acct.getPhotoUrl() !=null){
                url  = acct.getPhotoUrl().toString();
            }

            SharedPreferences sharedPreferences = activity_sign.this.getSharedPreferences(data_session.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(data_session.EMAIL, email);
            editor.putString(data_session.NAMA, nama);
            editor.putString(data_session.FOTO, url);
            editor.putString(data_session.LOGIN, "LOGEDIN");
            editor.putBoolean(data_session.LOGGEDIN_SHARED_PREF, true);
            editor.apply();

            startActivity(new Intent(activity_sign.this, activity_home_group.class),
                    ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
