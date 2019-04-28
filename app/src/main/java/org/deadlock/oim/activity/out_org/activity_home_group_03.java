package org.deadlock.oim.activity.out_org;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.deadlock.oim.R;
import org.deadlock.oim.activity.out_org.account.activity_account;
import org.deadlock.oim.adapter.NonSwipeableViewPager;
import org.deadlock.oim.adapter.out_org.adapter_home;
import org.deadlock.oim.data.data_session;
import org.deadlock.oim.helper.helper_snackbar;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class activity_home_group_03 extends AppCompatActivity {
    private adapter_home adapterHome;
    private NonSwipeableViewPager homeViewPager;
    private SwipeRefreshLayout homeSwipeRefresh;
    private int current_position = 0;
    private ArrayList FILE_UPLOAD = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_home_group_03);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        homeSwipeRefresh = findViewById(R.id.homerefresh);
        homeSwipeRefresh.setColorSchemeResources(android.R.color.holo_blue_dark,
                android.R.color.holo_red_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_green_dark);
        homeSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh_content();
            }
        });

        buttonClicked();
        loadViewPager();
    }

    private void buttonClicked(){
        ImageButton add_org = findViewById(R.id.add_group);
        add_org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOrg();
            }
        });

        ImageButton see_notif = findViewById(R.id.see_notif);
        see_notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeViewTo(1);
            }
        });

        ImageButton account = findViewById(R.id.btn_account);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAccount();
            }
        });

        TextView txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeViewTo(0);
            }
        });

        ImageView logo = findViewById(R.id.img_logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeViewTo(0);
            }
        });

        FloatingActionButton floatJoin = findViewById(R.id.floatJoin);
        floatJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                joinOrg();
            }
        });
    }

    public void openAccount(){
        final ProgressDialog pd = new ProgressDialog(activity_home_group_03.this,R.style.alertDialogTheme);
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
                startActivity(new Intent(activity_home_group_03.this, activity_account.class),
                        ActivityOptionsCompat.makeSceneTransitionAnimation(activity_home_group_03.this).toBundle());
            }
        }.start();
    }

    private void addOrg(){
        final ProgressDialog pd = new ProgressDialog(activity_home_group_03.this,R.style.alertDialogTheme);
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
                startActivity(new Intent(activity_home_group_03.this, activity_create_group_org.class),
                        ActivityOptionsCompat.makeSceneTransitionAnimation(activity_home_group_03.this).toBundle());
            }
        }.start();
    }


    @SuppressLint("CutPasteId")
    private void loadViewPager() {
        homeViewPager = findViewById(R.id.HomePager);
        adapterHome = new adapter_home(getSupportFragmentManager());
        homeViewPager.setAdapter(adapterHome);
        homeViewPager.setOffscreenPageLimit(3);

        View touchView = findViewById(R.id.HomePager);
        touchView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        changeViewTo(current_position);
    }

    public void changeViewTo(int a){
        homeViewPager.setCurrentItem(a, true);
        adapterHome.notifyDataSetChanged();
        current_position = a;
    }

    public void showSnackBar(String content){
        @SuppressLint("WrongConstant") Snackbar snackbar = Snackbar.make(findViewById(R.id.includecontent),content, Toast.LENGTH_SHORT);
        helper_snackbar.configSnackbar(this, snackbar);
        snackbar.show();
    }

    public void showToast(String content){
        Toast.makeText(activity_home_group_03.this,content,Toast.LENGTH_SHORT).show();
    }

    private void refresh_content() {
        new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                loadViewPager();
                homeSwipeRefresh.setRefreshing(false);
            }
        }.start();
    }

    @SuppressLint("SetTextI18n")
    private void joinOrg(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        View view = View.inflate(this,R.layout.content_alert_dialog_join_org,null);
        dialog.setView(view);
        final AlertDialog alertDialog = dialog.create();
        Button cancel = view.findViewById(R.id.btnCancelJoin);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        final EditText token = view.findViewById(R.id.token_code);
        Button ok = view.findViewById(R.id.btnJoin);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tok = token.getText().toString();
                if(tok.isEmpty()){
                    Toast.makeText(activity_home_group_03.this,"Sorry, please input your token.",Toast.LENGTH_SHORT).show();
                }else{
                    alertDialog.dismiss();
                    verifyToken();
                }
            }
        });

        ImageButton ScanQR = view.findViewById(R.id.scanQRCode);
        ScanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IntentIntegrator(activity_home_group_03.this).setCaptureActivity(activity_scan_qr.class).initiateScan();

                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void verifyToken(){
        final ProgressDialog pd = new ProgressDialog(activity_home_group_03.this,R.style.alertDialogTheme);
        pd.setProgressStyle(R.style.Widget_AppCompat_ProgressBar);
        pd.setCancelable(false);
        pd.show();

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                //dialog.dismiss();
                pd.dismiss();
                addAttachment();
            }
        }.start();

    }

    private void addAttachment(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        View view = View.inflate(this,R.layout.content_alert_dialog_add_attachment,null);
        dialog.setView(view);

        final AlertDialog alertDialog = dialog.create();
        Button upload = view.findViewById(R.id.btnSetRequest);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(FILE_UPLOAD.isEmpty()){
                    showToast("You must choose a file to upload.");
                }else{
                    alertDialog.hide();

                    final ProgressDialog pd = new ProgressDialog(activity_home_group_03.this,R.style.alertDialogTheme);
                    pd.setProgressStyle(R.style.Widget_AppCompat_ProgressBar);
                    pd.setCancelable(false);
                    pd.show();

                    new CountDownTimer(5000, 1000) {
                        @Override
                        public void onTick(long l) {

                        }

                        @Override
                        public void onFinish() {
                            alertDialog.dismiss();
                            pd.dismiss();
                            showToast("Thanks for your request. See your request on My Request menu.");
                        }
                    }.start();
                }

            }
        });

        Button skip = view.findViewById(R.id.btnSkip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

                final ProgressDialog pd = new ProgressDialog(activity_home_group_03.this,R.style.alertDialogTheme);
                pd.setProgressStyle(R.style.Widget_AppCompat_ProgressBar);
                pd.setCancelable(false);
                pd.show();

                new CountDownTimer(5000, 1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        pd.dismiss();
                        showToast("Thanks for your request. See your request on My Request menu.");
                    }
                }.start();

            }
        });


        ImageButton add = view.findViewById(R.id.addAttachments);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity_home_group_03.this,"This feature is under development",Toast.LENGTH_SHORT).show();
            }
        });

        alertDialog.show();
    }

    @SuppressLint("SetTextI18n")
    public void logout(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        View view = View.inflate(this,R.layout.content_alert_dialog,null);
        TextView title = view.findViewById(R.id.txtAlertTitle);
        TextView msg = view.findViewById(R.id.txtAlertMsg);
        title.setText("Logout from this account?");
        msg.setText("Are you sure want to logout from this account? You'll go back to Sign in page.");

        dialog.setView(view);
        final AlertDialog alertDialog = dialog.create();
        Button cancel = view.findViewById(R.id.btnAlertNegative);
        cancel.setText("Cancel");
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        Button ok = view.findViewById(R.id.btnAlertPositive);
        ok.setText("Logout");
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                final ProgressDialog pd = new ProgressDialog(activity_home_group_03.this,R.style.alertDialogTheme);
                pd.setProgressStyle(R.style.Widget_AppCompat_ProgressBar);
                pd.setCancelable(false);
                pd.show();

                SharedPreferences sharedPreferences = activity_home_group_03.this.getSharedPreferences(data_session.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                final SharedPreferences.Editor editor = sharedPreferences.edit();

                new CountDownTimer(5000, 1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        editor.clear();
                        editor.apply();

                        startActivity(new Intent(activity_home_group_03.this, activity_sign.class),
                                ActivityOptionsCompat.makeSceneTransitionAnimation(activity_home_group_03.this).toBundle());
                    }
                }.start();

            }
        });

        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        if (current_position != 0) {
            changeViewTo(0);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_group, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            homeSwipeRefresh.setRefreshing(true);
            if(homeSwipeRefresh.isRefreshing()){
                refresh_content();
            }
        }else if (id == R.id.action_send) {
            showSnackBar("This function is under development.");
        }else if (id == R.id.action_join) {
            joinOrg();
        }else if (id == R.id.action_logout) {
            logout();
        }else if(id == R.id.action_requested){
            changeViewTo(2);
        }else if(id == R.id.action_notif){
            changeViewTo(1);
        }else if(id == R.id.action_account){
            openAccount();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //We will get scan results here
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        //check for null
        if (result != null) {
            if (result.getContents() != null) {
                String konten = result.getContents();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Scan Result");
                builder.setMessage(konten);
                final ProgressDialog pd = new ProgressDialog(activity_home_group_03.this,R.style.alertDialogTheme);
                pd.setProgressStyle(R.style.Widget_AppCompat_ProgressBar);
                pd.setCancelable(false);
                pd.show();

                new CountDownTimer(5000, 1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        pd.dismiss();
                        addAttachment();
                    }
                }.start();
            }

            //else {
                //show dialogue with result
                //showResultDialogue(result.getContents());
            //}
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
