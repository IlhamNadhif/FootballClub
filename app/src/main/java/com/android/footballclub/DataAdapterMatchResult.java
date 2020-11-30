package com.android.footballclub;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapterMatchResult extends RecyclerView.Adapter<DataAdapterMatchResult.DataMatchResultViewHolder> {

    private ArrayList<ModelMatchResult> dataMatchResult;
    private Callback callback;
    View viewku;
    int posku;
    interface Callback {
        void onClick(int position);
        void test();
    }
    public DataAdapterMatchResult(ArrayList<ModelMatchResult> dataMatchResult, Callback callback) {
        this.callback = callback;
        this.dataMatchResult = dataMatchResult;
        Log.d("makanan", "MahasiswaAdapter: "+dataMatchResult.size()+"");
    }
    @Override
    public DataMatchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_match_result, parent, false);
        return new DataMatchResultViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final DataMatchResultViewHolder holder, final int position) {
        holder.tvTeam1.setText(dataMatchResult.get(position).getStrHomeTeam());
        holder.tvTeam2.setText(dataMatchResult.get(position).getStrAwayTeam());
        holder.tvDate.setText(dataMatchResult.get(position).getDateEvent());
        holder.tvScoreTeam1.setText(dataMatchResult.get(position).getIntAwayScore());
        holder.tvScoreTeam2.setText(dataMatchResult.get(position).getIntHomeScore());
    }
    @Override
    public int getItemCount() {
        return (dataMatchResult != null) ? dataMatchResult.size() : 0;
    }
    public class DataMatchResultViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTeam1, tvTeam2, tvDate, tvScoreTeam1, tvScoreTeam2;
        CardView card;
        ImageView tvMatchTeam1, tvMatchTeam2;
        public DataMatchResultViewHolder(View itemView) {
            super(itemView);
            viewku=itemView;
            card = (CardView) itemView.findViewById(R.id.cardEvent);
            tvTeam1 = (TextView) itemView.findViewById(R.id.matchTeam1);
            tvTeam2 = (TextView) itemView.findViewById(R.id.matchTeam2);
            tvDate = (TextView) itemView.findViewById(R.id.eventDate);
            tvScoreTeam1 = (TextView) itemView.findViewById(R.id.matchScoreTeam1);
            tvScoreTeam2 = (TextView) itemView.findViewById(R.id.matchScoreTeam2);
            tvMatchTeam1 = (ImageView) itemView.findViewById(R.id.tvMatchTeam1);
            tvMatchTeam2 = (ImageView) itemView.findViewById(R.id.tvMatchTeam2);
        }

    }
}
