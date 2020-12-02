package com.android.footballclub;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerFragment extends Fragment {

    public static PlayerFragment getInstance(){
        PlayerFragment playerFragment = new PlayerFragment();
        return playerFragment;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PlayerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayerFragment newInstance(String param1, String param2) {
        PlayerFragment fragment = new PlayerFragment();
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

    Bundle extras;
    TextView tvStadiunName, tvStadiunLocation, tvStadiunCapacity, tvStadiunDescription;
    ImageView ivStadium;

    String nama;
    String lokasi;
    String kapasitas;
    String deskripsi;
    String ivStadiumLogo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player, container, false);

        extras = getActivity().getIntent().getExtras();
        tvStadiunName = (TextView) view.findViewById(R.id.tvStadiunName);
        tvStadiunLocation = (TextView) view.findViewById(R.id.tvStadiunLocation);
        tvStadiunCapacity = (TextView) view.findViewById(R.id.tvStadiunCapacity);
        tvStadiunDescription = (TextView) view.findViewById(R.id.tvStadiunDescription);
        ivStadium = (ImageView) view.findViewById(R.id.ivStadium);

        if (extras != null) {

            nama = extras.getString("namaStadium");
            lokasi = extras.getString("lokasiStadium");
            kapasitas = extras.getString("kapasitasStadium");
            deskripsi = extras.getString("deskripsiStadium");
            ivStadiumLogo = extras.getString("LogoStadium");

            tvStadiunName.setText(nama);
            tvStadiunLocation.setText(lokasi);
            tvStadiunCapacity.setText(kapasitas);
            tvStadiunDescription.setText(deskripsi);
            Glide.with(getActivity())
                    .load(ivStadiumLogo)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivStadium);
        }

        // Inflate the layout for this fragment
        return view;
    }
}