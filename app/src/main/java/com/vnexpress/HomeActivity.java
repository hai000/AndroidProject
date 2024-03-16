package com.vnexpress;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vnexpress.API.News;
import com.vnexpress.fragment.DetailNewsFragment;

public class HomeActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    //    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }


        setContentView(R.layout.activity_main);
//        navigationView = findViewById(R.id.nav_view);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

//        drawerLayout = findViewById(R.id.drawerLayout);
//        Toolbar toolbar = findViewById(R.id.toolbar);

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
//            navigationView.setCheckedItem(R.id.nav_home);
        }
        bottomNavigationView.setBackground(null);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.home) {
                Log.d("HomeActivity", "onCreate: home");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            }
            if (item.getItemId() == R.id.tools) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UtilityFragment()).commit();
            }
            if (item.getItemId() == R.id.podcast) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PodcastFragment()).commit();
            }
            if (item.getItemId() == R.id.menu) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MenuFragment()).commit();
            }


            return true;
        });
    }

    public void gotoDetailNewsFragment(News news) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        DetailNewsFragment detailNewsFragment = new DetailNewsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("news", news);
        detailNewsFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.fragment_container, detailNewsFragment);
        fragmentTransaction.addToBackStack(DetailNewsFragment.TAG);
        fragmentTransaction.commit();

    }


}