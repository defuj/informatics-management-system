package org.deadlock.oim.activity.out_org;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.deadlock.oim.R;
import org.deadlock.oim.adapter.adapter_home;
import org.deadlock.oim.data.Data_account;
import org.deadlock.oim.data.data_session;
import org.deadlock.oim.helper.helper_snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.multidex.MultiDex;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

public class activity_home_group extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private adapter_home adapterHome;
    private ViewPager homeViewPager;
    private SwipeRefreshLayout homeSwipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MultiDex.install(this);
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        DataBindingUtil.setContentView(this,R.layout.activity_home_group);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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
                showSnackBar();
            }
        });

        loadData();
        loadViewPager();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(data_session.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String email = String.valueOf(sharedPreferences.getString(data_session.EMAIL,"Not Available"));
        String name = String.valueOf(sharedPreferences.getString(data_session.NAMA,"Not Available"));
        Data_account data = new Data_account(name,email);
    }

    @SuppressLint("CutPasteId")
    private void loadViewPager() {
        homeViewPager = findViewById(R.id.HomePager);
        adapterHome = new adapter_home(getSupportFragmentManager());
        homeViewPager.setAdapter(adapterHome);
        homeViewPager.setOffscreenPageLimit(2);

        View touchView = findViewById(R.id.HomePager);
        touchView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    public void showSnackBar(){
        @SuppressLint("WrongConstant") Snackbar snackbar = Snackbar.make(findViewById(R.id.includecontent),"This function in development", Toast.LENGTH_SHORT);
        helper_snackbar.configSnackbar(this, snackbar);
        snackbar.show();
    }

    private void refresh_content() {
        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                homeSwipeRefresh.setRefreshing(false);
            }
        }.start();
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
            showSnackBar();
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
        } else if (id == R.id.nav_calendar) {
            Intent intent = new Intent();
            ComponentName componentName = new ComponentName("com.google.android.calendar", "com.android.calendar.LaunchActivity");
            startActivity(intent.setComponent(componentName));
        } else if (id == R.id.nav_notification) {
            homeViewPager.setCurrentItem(1, false);
            adapterHome.notifyDataSetChanged();
        } else if (id == R.id.nav_todo) {
            showSnackBar();
        } else if (id == R.id.nav_setting) {
            showSnackBar();
        } else if (id == R.id.nav_help) {
            showSnackBar();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
