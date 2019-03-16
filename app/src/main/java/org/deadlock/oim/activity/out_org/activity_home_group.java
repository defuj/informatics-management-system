package org.deadlock.oim.activity.out_org;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.deadlock.oim.R;
import org.deadlock.oim.adapter.out_org.adapter_home;
import org.deadlock.oim.data.data_session;
import org.deadlock.oim.helper.helper_snackbar;
import org.deadlock.oim.network.net;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

public class activity_home_group extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private adapter_home adapterHome;
    private ViewPager homeViewPager;
    private SwipeRefreshLayout homeSwipeRefresh;
    private boolean dropAccount = true;
    private Button BtndropAccount;
    private DrawerLayout drawer;
    private int current_position = 0;
    private net network;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_home_group);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        network = new net();

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                dropAccount = true;
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black_60_transparent));

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

        ImageButton add_org = findViewById(R.id.add_group);
        add_org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOrg();
            }
        });

        loadNavigationFunction();
        loadData();
        loadViewPager();
    }

    private void loadNavigationFunction() {
        NavigationView navSecond = findViewById(R.id.navSecond);
        navSecond.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_add_account){
                    startActivity(new Intent(Settings.ACTION_ADD_ACCOUNT)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtra(Settings.EXTRA_ACCOUNT_TYPES,new String[] {"com.google"}));
                    drawer.closeDrawer(GravityCompat.START);
                }else if (id == R.id.nav_manage_account){
                    startActivityForResult(new Intent(Settings.ACTION_SYNC_SETTINGS),0);
                    drawer.closeDrawer(GravityCompat.START);
                }

                return true;
            }
        });
    }


    private void loadData() {
        TextView textnama = findViewById(R.id.namatext);
        TextView textemail = findViewById(R.id.emailtext);
        SimpleDraweeView currentFoto = findViewById(R.id.currentAccountLogin);

        SharedPreferences sharedPreferences = getSharedPreferences(data_session.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        textnama.setText(sharedPreferences.getString(data_session.NAMA,"Not Available"));
        textemail.setText(sharedPreferences.getString(data_session.EMAIL,"Not Available"));
        currentFoto.setImageURI(Uri.parse(String.valueOf(sharedPreferences.getString(data_session.FOTO,"Not Available"))));
        //Data_account data = new Data_account(name,email);
        //binding.setData(data);
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

        homeViewPager.setCurrentItem(current_position, false);
        adapterHome.notifyDataSetChanged();
    }

    public void showSnackBar(){
        @SuppressLint("WrongConstant") Snackbar snackbar = Snackbar.make(findViewById(R.id.includecontent),"This function in development", Toast.LENGTH_SHORT);
        helper_snackbar.configSnackbar(this, snackbar);
        snackbar.show();
    }

    private void refresh_content() {
        new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                if(network.status()){
                    loadViewPager();
                    homeSwipeRefresh.setRefreshing(false);
                }else{
                    homeSwipeRefresh.setRefreshing(false);
                }
            }
        }.start();
    }

    private void addOrg(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        View view = View.inflate(this,R.layout.content_alert_dialog_add_org,null);

        dialog.setView(view);
        final AlertDialog alertDialog = dialog.create();
        Button cancel = view.findViewById(R.id.btnNextTime);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        Button ok = view.findViewById(R.id.btnCreate);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSnackBar();
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    @SuppressLint("SetTextI18n")
    private void joinOrg(){
        //View viewroot = View.inflate(activity_home_group.this,R.layout.content_alert_dialog_join_org,null);
        final EditText token = new EditText(this);
        token.findViewById(R.id.token_code);
        token.setText("Bismillah");

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

        Button ok = view.findViewById(R.id.btnJoin);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showSnackBar();
                alertDialog.dismiss();
                verifyToken();
            }
        });

        ImageButton ScanQR = view.findViewById(R.id.scanQRCode);
        ScanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showSnackBar();
                //startActivity(new Intent(activity_home_group.this, activity_scan_qr.class),
                        //ActivityOptionsCompat.makeSceneTransitionAnimation(activity_home_group.this).toBundle());
                new IntentIntegrator(activity_home_group.this).setCaptureActivity(activity_scan_qr_2.class).initiateScan();

                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void verifyToken(){
        final ProgressDialog dialog = new ProgressDialog(activity_home_group.this);
        dialog.setMessage("Verifying code");
        dialog.show();

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                new CountDownTimer(2000, 1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        dialog.setMessage("Preparing request");
                    }
                }.start();
            }

            @Override
            public void onFinish() {
                dialog.dismiss();
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
                alertDialog.hide();

                //uploading
                final ProgressDialog dialog = new ProgressDialog(activity_home_group.this);
                dialog.setMessage("Uploading...");
                dialog.show();

                new CountDownTimer(5000, 1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        alertDialog.dismiss();
                        dialog.dismiss();
                        request_join();
                    }
                }.start();
            }
        });

        Button skip = view.findViewById(R.id.btnSkip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                request_join();
            }
        });


        ImageButton add = view.findViewById(R.id.addAttachments);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity_home_group.this,"This feature is under development",Toast.LENGTH_SHORT).show();
            }
        });

        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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
            showSnackBar();
        }else if (id == R.id.action_join) {
            joinOrg();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            homeViewPager.setCurrentItem(0, false);
            adapterHome.notifyDataSetChanged();
            current_position = 0;
        } else if (id == R.id.nav_request) {
            homeViewPager.setCurrentItem(2, false);
            adapterHome.notifyDataSetChanged();
            current_position = 2;
        } else if (id == R.id.nav_notification) {
            homeViewPager.setCurrentItem(1, false);
            adapterHome.notifyDataSetChanged();
            current_position = 1;
        } else if (id == R.id.nav_todo) {
            showSnackBar();
        } else if (id == R.id.nav_setting) {
            startActivity(new Intent(activity_home_group.this, activity_setting.class),
                    ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
        } else if (id == R.id.nav_help) {
            showSnackBar();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //We will get scan results here
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        //check for null
        if (result != null) {
            if (result.getContents() != null) {
                //Toast.makeText(this, "Scan Cancelled", Toast.LENGTH_LONG).show();
                String konten = result.getContents();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Scan Result");
                builder.setMessage(konten);
                //AlertDialog alertDialog = builder.create();
                //alertDialog.show();

                final ProgressDialog dialog = new ProgressDialog(activity_home_group.this);
                dialog.setMessage("Verifying code");
                dialog.show();

                new CountDownTimer(5000, 1000) {
                    @Override
                    public void onTick(long l) {
                        new CountDownTimer(2000, 1000) {
                            @Override
                            public void onTick(long l) {

                            }

                            @Override
                            public void onFinish() {
                                dialog.setMessage("Sending request");
                            }
                        }.start();
                    }

                    @Override
                    public void onFinish() {
                        dialog.dismiss();
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

    private void request_join(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        View view = View.inflate(this,R.layout.content_alert_dialog_request_sended,null);

        dialog.setView(view);
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }
}
