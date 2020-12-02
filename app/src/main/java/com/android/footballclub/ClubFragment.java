package com.android.footballclub;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClubFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClubFragment extends Fragment {

    public static ClubFragment getInstance(){
        ClubFragment clubFragment = new ClubFragment();
        return clubFragment;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ClubFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClubFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClubFragment newInstance(String param1, String param2) {
        ClubFragment fragment = new ClubFragment();
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


    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private ArrayList<Model> DataArrayList; //kit add kan ke adapter
    private ImageView tambah_data;
    private ProgressBar PrgrsBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        PrgrsBar = (ProgressBar) view.findViewById(R.id.progresBarClub);

        addDataOnline();

        // Inflate the layout for this fragment
        return view;
    }

    void addDataOnline(){
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d("hasiljson", "onResponse: " + response.toString());
                        //jika sudah berhasil debugm lanjutkan code dibawah ini
                        DataArrayList = new ArrayList<>();
                        Model modelku;
                        try {
                            Log.d("hasiljson", "onResponse: " + response.toString());
                            JSONArray jsonArray = response.getJSONArray("teams");
                            Log.d("hasiljson2", "onResponse: " + jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                modelku = new Model();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                modelku.setIdTeam(jsonObject.getInt("idTeam"));
                                modelku.setStrTeam(jsonObject.getString("strTeam"));
                                modelku.setStrTeamBadge(jsonObject.getString("strTeamBadge"));
                                modelku.setStrCountry(jsonObject.getString("strCountry"));
                                modelku.setStrDescriptionEN(jsonObject.getString("strDescriptionEN"));
                                modelku.setStrStadiumThumb(jsonObject.getString("strStadiumThumb"));
                                modelku.setIntFormedYear(jsonObject.getString("intFormedYear"));
                                modelku.setStrStadiumLocation(jsonObject.getString("strStadiumLocation"));
                                modelku.setStrAlternate(jsonObject.getString("strAlternate"));
                                modelku.setStrStadium(jsonObject.getString("strStadium"));
                                modelku.setIntStadiumCapacity(jsonObject.getString("intStadiumCapacity"));
                                modelku.setStrStadiumDescription(jsonObject.getString("strStadiumDescription"));
                                modelku.setStrWebsite(jsonObject.getString("strWebsite"));
                                modelku.setStrFacebook(jsonObject.getString("strFacebook"));
                                modelku.setStrTwitter(jsonObject.getString("strTwitter"));
                                modelku.setStrYoutube(jsonObject.getString("strYoutube"));
                                DataArrayList.add(modelku);
                            }
                            //untuk handle click
                            adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
                                @Override
                                public void onClick(int position) {
                                    Model Club = DataArrayList.get(position);
                                    Intent intent = new Intent(getActivity(), DetailAcivity.class);
                                    intent.putExtra("id",Club.idTeam);
                                    intent.putExtra("namaClub",Club.strTeam);
                                    intent.putExtra("logoClub",Club.strTeamBadge);
                                    intent.putExtra("deskripsiClub",Club.strDescriptionEN);
                                    intent.putExtra("namaStadium",Club.strStadium);
                                    intent.putExtra("LogoStadium",Club.strStadiumThumb);
                                    intent.putExtra("lokasiStadium",Club.strStadiumLocation);
                                    intent.putExtra("kapasitasStadium",Club.intStadiumCapacity);
                                    intent.putExtra("deskripsiStadium",Club.strStadiumDescription);
                                    intent.putExtra("website",Club.strWebsite);
                                    intent.putExtra("facebook",Club.strFacebook);
                                    intent.putExtra("twitter",Club.strTwitter);
                                    intent.putExtra("youtube",Club.strYoutube);
                                    startActivity(intent);
                                }

                                @Override
                                public void test() {

                                }
                            });
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);

                            PrgrsBar.setVisibility(View.INVISIBLE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError error) {
                        PrgrsBar.setVisibility(View.INVISIBLE);

                        // handle error
                        Log.d("errorku", "onError errorCode : " + error.getErrorCode());
                        Log.d("errorku", "onError errorBody : " + error.getErrorBody());
                        Log.d("errorku", "onError errorDetail : " + error.getErrorDetail());

                    }
                });
    }
}