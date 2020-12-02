package com.android.footballclub;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.tabs.TabLayout;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailAcivity extends AppCompatActivity {

    Bundle extras;
    int id;
    String title;
    String logo;
    String website;
    String tahun;
    String namaStadium;
    String lokasiStadium;
    String kapasitasStadium;
    String deskripsiStadium;
    String facebook;
    String twitter;
    String youtube;
    String namaAlternatif;
    String desc;
    String country;

    TextView tvNamaTeam;
    ImageView ivlogo;
    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView ivWebsite, ivFacebook, ivTwitter, ivYoutube;
    Button btnFav;

    Realm realm;
    RealmHelper realmHelper;
    Model clubModel;
    private String LogoStadium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_acivity);

        extras = getIntent().getExtras();
        ivlogo = (ImageView) findViewById(R.id.ivPoster);
        tvNamaTeam = (TextView) findViewById(R.id.tvClubTittle);
        ivWebsite = (ImageView) findViewById(R.id.website);
        ivFacebook = (ImageView) findViewById(R.id.facebook);
        ivTwitter = (ImageView) findViewById(R.id.twitter);
        ivYoutube = (ImageView) findViewById(R.id.youtube);
        btnFav = (Button) findViewById(R.id.btnFavourite);

        ivWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://" + website);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        ivFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://" + facebook);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        ivTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://" + twitter);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        ivYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://" + youtube);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        if (extras != null) {
            id = extras.getInt("id");
            title = extras.getString("namaClub");
            logo = extras.getString("logoClub");
            website = extras.getString("website");
            facebook = extras.getString("facebook");
            twitter = extras.getString("twitter");
            youtube = extras.getString("youtube");
            namaAlternatif = extras.getString("namaAlternateClub");
            desc = extras.getString("deskripsiClub");
            tahun = extras.getString("tahunClub");
            namaStadium = extras.getString("namaStadium");
            LogoStadium = extras.getString("LogoStadium");
            lokasiStadium = extras.getString("lokasiStadium");
            kapasitasStadium = extras.getString("kapasitasStadium");
            deskripsiStadium = extras.getString("deskripsiStadium");
            country = extras.getString("country");

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

        //Set up Realm
        Realm.init(DetailAcivity.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clubModel = new Model();
                clubModel.setIdTeam(id);
                clubModel.setIntFormedYear(tahun);
                clubModel.setIntStadiumCapacity(kapasitasStadium);
                clubModel.setStrAlternate(namaAlternatif);
                clubModel.setStrCountry(country);
                clubModel.setStrDescriptionEN(desc);
                clubModel.setStrStadium(namaStadium);
                clubModel.setStrStadiumDescription(deskripsiStadium);
                clubModel.setStrStadiumLocation(lokasiStadium);
                clubModel.setStrStadiumThumb(LogoStadium);
                clubModel.setStrTeam(title);
                clubModel.setStrTeamBadge(logo);
                clubModel.setStrTwitter(twitter);
                clubModel.setStrWebsite(website);
                clubModel.setStrYoutube(youtube);
                clubModel.setStrFacebook(facebook);
                realmHelper = new RealmHelper(realm);
                realmHelper.save(clubModel);
                Toast.makeText(DetailAcivity.this, "Berhasil ditambahkan", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void getTabs() {
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