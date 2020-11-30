package com.android.footballclub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TableLayout;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getTabs();
    }

    public void getTabs(){
        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                viewPagerAdapter.addFragment(ClubFragment.getInstance(), "Club");
                viewPagerAdapter.addFragment(EventFragment.getInstance(), "Event");

                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);

                tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_airplanemode_active_24);
                tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_airplanemode_active_24);
            }
        });
    }
}