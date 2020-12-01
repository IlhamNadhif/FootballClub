package com.android.footballclub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.tabs.TabLayout;

public class DetailAcivity extends AppCompatActivity {

    Bundle extras;
    String id;
    String title;
    String logo;
    String stadiumName;
    String stadiumLocation;
    String stadiumCapacity;

    TextView tvNamaTeam;
    ImageView ivlogo;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_acivity);

        extras = getIntent().getExtras();
        ivlogo = (ImageView) findViewById(R.id.ivPoster);
        tvNamaTeam = (TextView)findViewById(R.id.tvClubTittle);



        if (extras != null) {
            id = extras.getString("id");
            title = extras.getString("namaClub");
            logo = extras.getString("logoClub");

            tvNamaTeam.setText(title);
            Glide.with(DetailAcivity.this)
                    .load(logo)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivlogo);
            // and get whatever type user account id is
        }

        getTabs();

        tabLayout = findViewById(R.id.tabLayoutDetail);
        viewPager = findViewById(R.id.viewPagerDetail);
    }

    public void getTabs(){
        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                viewPagerAdapter.addFragment(DescriptionFragment.getInstance(), "Description");
                viewPagerAdapter.addFragment(PlayerFragment.getInstance(), "Stadium");
                viewPagerAdapter.addFragment(MatchFragment.getInstance(), "Match");

                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
            }
        });
    }
}
