package com.android.footballclub;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavouriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavouriteFragment extends Fragment {

    public static FavouriteFragment getInstance() {
        FavouriteFragment favouriteFragment = new FavouriteFragment();
        return favouriteFragment;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavouriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavouriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavouriteFragment newInstance(String param1, String param2) {
        FavouriteFragment fragment = new FavouriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Realm realm;
    RealmHelper realmHelper;
    TextView tvnodata;
    RecyclerView recyclerView;
    DataAdapterFavourite adapter;
    List<Model> DataArrayList; //kit add kan ke adapter

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);

        tvnodata = (TextView) view.findViewById(R.id.tvnodata);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewFavourite);
        DataArrayList = new ArrayList<>();
        // Setup Realm
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
        DataArrayList = realmHelper.getAllClub();
        if (DataArrayList.size() == 0){
            tvnodata.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else{
            tvnodata.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter = new DataAdapterFavourite(DataArrayList, new DataAdapterFavourite.Callback() {
                @Override
                public void onClick(int position) {
                    Model Club = DataArrayList.get(position);
                    Intent intent = new Intent(getActivity(), DetailAcivity.class);
                    intent.putExtra("id",Club.idTeam);
                    intent.putExtra("country",Club.strCountry);
                    intent.putExtra("namaClub",Club.strTeam);
                    intent.putExtra("namaAlternateClub",Club.strAlternate);
                    intent.putExtra("logoClub",Club.strTeamBadge);
                    intent.putExtra("deskripsiClub",Club.strDescriptionEN);
                    intent.putExtra("tahunClub",Club.intFormedYear);
                    intent.putExtra("namaStadium",Club.strStadium);
                    intent.putExtra("LogoStadium",Club.strStadiumThumb);
                    intent.putExtra("lokasiStadium",Club.strStadiumLocation);
                    intent.putExtra("kapasitasStadium",Club.intStadiumCapacity);
                    intent.putExtra("deskripsiStadium",Club.strStadiumDescription);
                    intent.putExtra("website",Club.strWebsite);
                    intent.putExtra("facebook",Club.strFacebook);
                    intent.putExtra("twitter",Club.strTwitter);
                    intent.putExtra("youtube",Club.strYoutube);
                    // di putextra yang lain
                    startActivity(intent);
                }

                @Override
                public void test() {

                }
            });
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }


        // Inflate the layout for this fragment
        return view;
    }
}