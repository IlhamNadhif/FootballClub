package com.android.footballclub;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DatakuViewHolder>{
    private ArrayList<Model> dataList;
    private Callback callback;
    View viewku;
    int posku;
    public interface Callback {
        void onClick(int position);
        void test();
    }
    public DataAdapter(ArrayList<Model> dataList, Callback callback) {
        this.callback = callback;
        this.dataList = dataList;
        Log.d("team", "totalteam: "+dataList.size()+"");
    }
    @Override
    public DatakuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapterrv, parent, false);
        return new DatakuViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final DatakuViewHolder holder, final int position) {
        holder.tvName.setText(dataList.get(position).getStrTeam());
        holder.tvTahun.setText(dataList.get(position).getIntFormedYear());
        holder.tvCountry.setText(dataList.get(position).getStrCountry());
        holder.tvNamaLain.setText(dataList.get(position).getStrAlternate());
        Log.d("logo", "onBindViewHolder: "+dataList.get(position).getStrTeamBadge());
        //pakai glide karena untuk nampilkan data gambar dari URL / permission / graddle
        Glide.with(holder.itemView)
                .load(dataList.get(position).getStrTeamBadge())
                .override(Target.SIZE_ORIGINAL)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.ivprofile);
    }
    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }
    public class DatakuViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        private TextView tvName, tvTahun,tvCountry, tvNamaLain;
        CardView card;
        ImageView ivprofile;
        public DatakuViewHolder(View itemView) {
            super(itemView);
            viewku=itemView;
            card = (CardView) itemView.findViewById(R.id.cardku);
            ivprofile = (ImageView) itemView.findViewById(R.id.ivprofile);
            tvName = (TextView) itemView.findViewById(R.id.tvname);
            tvTahun = (TextView) itemView.findViewById(R.id.tvTahun);
            tvCountry = (TextView) itemView.findViewById(R.id.tvCountry);
            tvNamaLain = (TextView) itemView.findViewById(R.id.tvNamalain);
            itemView.setOnCreateContextMenuListener(this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClick(getAdapterPosition());
                }
            });
        }
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//            MenuItem Edit = menu.add(menu.NONE, 1, 1, "Edit");
//            MenuItem Delete = menu.add(menu.NONE, 2, 2, "Delete");
//            posku=getAdapterPosition();
//            Edit.setOnMenuItemClickListener(onEditMenu);
//            Delete.setOnMenuItemClickListener(onEditMenu);g
        }
    }
    private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case 1:
                    //Do stuff
                    Toast.makeText(viewku.getContext(), ""+posku, Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    //Do stuff
                    break;
            }
            return true;
        }
    };
}