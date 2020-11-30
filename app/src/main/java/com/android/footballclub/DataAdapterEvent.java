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

public class DataAdapterEvent extends RecyclerView.Adapter<DataAdapterEvent.DataEventViewHolder> {
    private ArrayList<ModelEvent> dataEvent;
    private Callback callback;
    View viewku;
    int posku;
    interface Callback {
        void onClick(int position);
        void test();
    }
    public DataAdapterEvent(ArrayList<ModelEvent> dataEvent, Callback callback) {
        this.callback = callback;
        this.dataEvent = dataEvent;
        Log.d("makanan", "MahasiswaAdapter: "+dataEvent.size()+"");
    }
    @Override
    public DataEventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_event, parent, false);
        return new DataEventViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final DataEventViewHolder holder, final int position) {
        holder.tvTeam1.setText(dataEvent.get(position).getStrHomeTeam());
        holder.tvTeam2.setText(dataEvent.get(position).getStrAwayTeam());
        holder.tvDate.setText(dataEvent.get(position).getDateEvent());
        holder.tvTime.setText(dataEvent.get(position).getStrTime());
    }
    @Override
    public int getItemCount() {
        return (dataEvent != null) ? dataEvent.size() : 0;
    }
    public class DataEventViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        private TextView tvTeam1, tvTeam2, tvDate, tvTime;
        CardView card;
        ImageView ivprofile;
        public DataEventViewHolder(View itemView) {
            super(itemView);
            viewku=itemView;
            card = (CardView) itemView.findViewById(R.id.cardEvent);
            tvTeam1 = (TextView) itemView.findViewById(R.id.eventTeam1);
            tvTeam2 = (TextView) itemView.findViewById(R.id.eventTeam2);
            tvDate = (TextView) itemView.findViewById(R.id.eventDate);
            tvTime = (TextView) itemView.findViewById(R.id.eventTime);
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
//            MenuItem Edit = menu.add(Menu.NONE, 1, 1, "Edit");
//            MenuItem Delete = menu.add(Menu.NONE, 2, 2, "Delete");
//            posku=getAdapterPosition();
//            Edit.setOnMenuItemClickListener(onEditMenu);
//            Delete.setOnMenuItemClickListener(onEditMenu);
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
