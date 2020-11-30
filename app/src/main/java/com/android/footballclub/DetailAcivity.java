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
    String cover;
    String country;
    String description;

    TextView tvNamaTeam;
    ImageView ivlogo;
    ImageView ivcover;
    TextView tvdesc;
    TextView tvNegara;

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_acivity);

        extras = getIntent().getExtras();
        tvNamaTeam = (TextView)findViewById(R.id.tvClubTittle);
        tvNegara = (TextView)findViewById(R.id.tvCountry);
        ivlogo = (ImageView) findViewById(R.id.ivPoster);
        ivcover = (ImageView) findViewById(R.id.ivCover);



        if (extras != null) {
            id = extras.getString("id");
            title = extras.getString("namaClub");
            logo = extras.getString("logoClub");
            country = extras.getString("negara");
            cover = extras.getString("stadiun");

            tvNamaTeam.setText(title);
            tvNegara.setText(country);
            Glide.with(DetailAcivity.this)
                    .load(logo)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivlogo);

            Glide.with(DetailAcivity.this)
                    .load(cover)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivcover);
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
                viewPagerAdapter.addFragment(MatchFragment.getInstance(), "Match");
                viewPagerAdapter.addFragment(PlayerFragment.getInstance(), "Player");

                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
            }
        });
    }
}
